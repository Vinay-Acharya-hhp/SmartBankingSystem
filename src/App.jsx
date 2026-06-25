import React, { useEffect, useMemo, useState } from "react";

const API_BASE = import.meta.env.VITE_API_BASE_URL || "http://localhost:8080";

const initialRegister = {
  name: "",
  email: "",
  phone: "",
  type: "USER",
  password: ""
};

const initialLogin = {
  email: "",
  password: ""
};

const initialAccount = {
  accountType: "SAVINGS",
  email: "",
  initialBalance: "0"
};

function decodeJwt(token) {
  try {
    const payload = token.split(".")[1];
    if (!payload) return {};
    const json = atob(payload.replace(/-/g, "+").replace(/_/g, "/"));
    return JSON.parse(decodeURIComponent(escape(json)));
  } catch {
    return {};
  }
}

function valueList(value) {
  if (!value) return [];
  return Array.isArray(value) ? value : [value];
}

function formatMoney(value) {
  const number = Number(value || 0);
  return new Intl.NumberFormat("en-IN", {
    style: "currency",
    currency: "INR",
    maximumFractionDigits: 2
  }).format(Number.isFinite(number) ? number : 0);
}

function formatDate(value) {
  if (!value) return "No date";
  const date = new Date(value);
  return Number.isNaN(date.getTime()) ? value : date.toLocaleString();
}

function App() {
  const [token, setToken] = useState(() => localStorage.getItem("bankToken") || "");
  const [profile, setProfile] = useState(() => {
    const stored = localStorage.getItem("bankProfile");
    return stored ? JSON.parse(stored) : { email: "", role: "USER" };
  });
  const [activeTab, setActiveTab] = useState("dashboard");
  const [loginForm, setLoginForm] = useState(initialLogin);
  const [registerForm, setRegisterForm] = useState(initialRegister);
  const [accountForm, setAccountForm] = useState(initialAccount);
  const [lookupEmail, setLookupEmail] = useState(profile.email || "");
  const [lookupAccount, setLookupAccount] = useState("");
  const [transactionAccount, setTransactionAccount] = useState("");
  const [moneyForm, setMoneyForm] = useState({ accountNumber: "", amount: "" });
  const [transferForm, setTransferForm] = useState({ sender: "", reciever: "", amount: "" });
  const [accounts, setAccounts] = useState([]);
  const [selectedAccount, setSelectedAccount] = useState(null);
  const [transactions, setTransactions] = useState([]);
  const [adminData, setAdminData] = useState({ users: [], accounts: [], transactions: [] });
  const [status, setStatus] = useState({ type: "info", text: "Ready to connect to the banking API." });
  const [loading, setLoading] = useState(false);

  const isAuthed = Boolean(token);
  const isAdmin = profile.role === "ADMIN";

  const headers = useMemo(() => {
    const base = { "Content-Type": "application/json" };
    return token ? { ...base, Authorization: `Bearer ${token}` } : base;
  }, [token]);

  useEffect(() => {
    if (token) localStorage.setItem("bankToken", token);
    else localStorage.removeItem("bankToken");
  }, [token]);

  useEffect(() => {
    localStorage.setItem("bankProfile", JSON.stringify(profile));
  }, [profile]);

  async function api(path, options = {}) {
    setLoading(true);
    try {
      const response = await fetch(`${API_BASE}${path}`, {
        ...options,
        headers: { ...headers, ...(options.headers || {}) }
      });
      const text = await response.text();
      const body = text ? JSON.parse(text) : null;
      if (!response.ok || body?.success === false) {
        throw new Error(body?.message || `Request failed with ${response.status}`);
      }
      setStatus({ type: "success", text: body?.message || "Request completed." });
      return body?.data ?? body;
    } catch (error) {
      setStatus({ type: "error", text: error.message });
      throw error;
    } finally {
      setLoading(false);
    }
  }

  function updateForm(setter, key, value) {
    setter((current) => ({ ...current, [key]: value }));
  }

  async function handleLogin(event) {
    event.preventDefault();
    const jwt = await api("/user/login", {
      method: "POST",
      body: JSON.stringify(loginForm)
    });
    const decoded = decodeJwt(jwt);
    const role = String(decoded.role || decoded.type || decoded.authorities?.[0]?.authority || "USER")
      .replace("ROLE_", "")
      .toUpperCase();
    setToken(jwt);
    setProfile({ email: loginForm.email, role: role === "ADMIN" ? "ADMIN" : "USER" });
    setLookupEmail(loginForm.email);
    setAccountForm((current) => ({ ...current, email: loginForm.email }));
    setLoginForm(initialLogin);
    setActiveTab("dashboard");
  }

  async function handleRegister(event) {
    event.preventDefault();
    await api("/user/register", {
      method: "POST",
      body: JSON.stringify(registerForm)
    });
    setLoginForm({ email: registerForm.email, password: "" });
    setRegisterForm(initialRegister);
  }

  async function createAccount(event) {
    event.preventDefault();
    const created = await api("/account/create", {
      method: "POST",
      body: JSON.stringify({
        ...accountForm,
        initialBalance: Number(accountForm.initialBalance)
      })
    });
    setSelectedAccount(created);
    setAccounts((current) => [created, ...current.filter((item) => item.accountNumber !== created.accountNumber)]);
  }

  async function loadAccounts(event) {
    event?.preventDefault();
    const data = await api(`/account/get_e/${encodeURIComponent(lookupEmail)}`);
    setAccounts(valueList(data));
  }

  async function findAccount(event) {
    event.preventDefault();
    const data = await api(`/account/get_ac/${encodeURIComponent(lookupAccount)}`);
    setSelectedAccount(data);
  }

  async function postMoney(kind, event) {
    event.preventDefault();
    const params = new URLSearchParams({
      accountNumber: moneyForm.accountNumber,
      amount: moneyForm.amount
    });
    const data = await api(`/transaction/${kind}?${params}`, { method: "POST" });
    setTransactions((current) => [data, ...current]);
  }

  async function transfer(event) {
    event.preventDefault();
    const params = new URLSearchParams(transferForm);
    const data = await api(`/transaction/transfer?${params}`, { method: "POST" });
    setTransactions((current) => [data, ...current]);
  }

  async function loadTransactions(event) {
    event.preventDefault();
    const params = new URLSearchParams({ accountnumber: transactionAccount });
    const data = await api(`/transaction/transactions?${params}`);
    setTransactions(valueList(data));
  }

  async function loadAdmin() {
    const [users, adminAccounts, allTransactions] = await Promise.all([
      api("/admin/users"),
      api("/admin/account"),
      api("/admin/transaction")
    ]);
    setAdminData({
      users: valueList(users),
      accounts: valueList(adminAccounts),
      transactions: valueList(allTransactions)
    });
  }

  function logout() {
    setToken("");
    setProfile({ email: "", role: "USER" });
    setAccounts([]);
    setSelectedAccount(null);
    setTransactions([]);
    setActiveTab("dashboard");
    setStatus({ type: "info", text: "Logged out." });
  }

  return (
    <main className="app-shell">
      <section className="hero-band">
        <div>
          <p className="eyebrow">Smart Banking</p>
          <h1>Banking operations dashboard</h1>
          <p className="hero-copy">
            Login, create customer accounts, move funds, review transaction activity, and inspect admin data from the Spring Boot API.
          </p>
        </div>
        <div className="connection-panel">
          <span>API</span>
          <strong>{API_BASE}</strong>
          <small className={isAuthed ? "good" : "muted"}>{isAuthed ? `${profile.role} session active` : "Not signed in"}</small>
        </div>
      </section>

      <nav className="tabs" aria-label="Main sections">
        {["dashboard", "accounts", "transactions", "admin"].map((tab) => (
          <button
            key={tab}
            className={activeTab === tab ? "active" : ""}
            disabled={tab === "admin" && !isAdmin}
            onClick={() => setActiveTab(tab)}
          >
            {tab}
          </button>
        ))}
      </nav>

      <div className={`status ${status.type}`} role="status">
        <span>{loading ? "Working..." : status.text}</span>
        {isAuthed && <button className="text-button" onClick={logout}>Logout</button>}
      </div>

      {!isAuthed && (
        <section className="auth-grid">
          <form className="panel" onSubmit={handleLogin}>
            <h2>Login</h2>
            <label>Email<input type="email" value={loginForm.email} onChange={(e) => updateForm(setLoginForm, "email", e.target.value)} required /></label>
            <label>Password<input type="password" value={loginForm.password} onChange={(e) => updateForm(setLoginForm, "password", e.target.value)} required /></label>
            <button className="primary" disabled={loading}>Login</button>
          </form>

          <form className="panel" onSubmit={handleRegister}>
            <h2>Register</h2>
            <div className="two-col">
              <label>Name<input value={registerForm.name} onChange={(e) => updateForm(setRegisterForm, "name", e.target.value)} required /></label>
              <label>Phone<input value={registerForm.phone} onChange={(e) => updateForm(setRegisterForm, "phone", e.target.value)} required /></label>
            </div>
            <label>Email<input type="email" value={registerForm.email} onChange={(e) => updateForm(setRegisterForm, "email", e.target.value)} required /></label>
            <div className="two-col">
              <label>Role<select value={registerForm.type} onChange={(e) => updateForm(setRegisterForm, "type", e.target.value)}><option>USER</option><option>ADMIN</option></select></label>
              <label>Password<input type="password" value={registerForm.password} onChange={(e) => updateForm(setRegisterForm, "password", e.target.value)} required /></label>
            </div>
            <button className="secondary" disabled={loading}>Create user</button>
          </form>
        </section>
      )}

      {isAuthed && activeTab === "dashboard" && (
        <section className="dashboard-grid">
          <article className="metric">
            <span>Loaded accounts</span>
            <strong>{accounts.length}</strong>
          </article>
          <article className="metric">
            <span>Transactions shown</span>
            <strong>{transactions.length}</strong>
          </article>
          <article className="metric">
            <span>Signed in as</span>
            <strong>{profile.email || "Unknown"}</strong>
          </article>
        </section>
      )}

      {isAuthed && activeTab === "accounts" && (
        <section className="content-grid">
          <form className="panel" onSubmit={createAccount}>
            <h2>Create Account</h2>
            <label>Email<input type="email" value={accountForm.email} onChange={(e) => updateForm(setAccountForm, "email", e.target.value)} required /></label>
            <div className="two-col">
              <label>Type<select value={accountForm.accountType} onChange={(e) => updateForm(setAccountForm, "accountType", e.target.value)}><option>SAVINGS</option><option>CURRENT</option></select></label>
              <label>Initial Balance<input type="number" min="0" step="0.01" value={accountForm.initialBalance} onChange={(e) => updateForm(setAccountForm, "initialBalance", e.target.value)} required /></label>
            </div>
            <button className="primary" disabled={loading}>Create account</button>
          </form>

          <div className="panel">
            <h2>Find Accounts</h2>
            <form className="inline-form" onSubmit={loadAccounts}>
              <input type="email" placeholder="customer@email.com" value={lookupEmail} onChange={(e) => setLookupEmail(e.target.value)} required />
              <button disabled={loading}>By email</button>
            </form>
            <form className="inline-form" onSubmit={findAccount}>
              <input placeholder="Account number" value={lookupAccount} onChange={(e) => setLookupAccount(e.target.value)} required />
              <button disabled={loading}>By account</button>
            </form>
          </div>

          <DataPanel title="Accounts" data={accounts} empty="No accounts loaded yet." render={(account) => (
            <AccountCard key={account.accountNumber} account={account} />
          )} />

          {selectedAccount && (
            <div className="panel highlight">
              <h2>Selected Account</h2>
              <AccountCard account={selectedAccount} />
            </div>
          )}
        </section>
      )}

      {isAuthed && activeTab === "transactions" && (
        <section className="content-grid">
          <form className="panel" onSubmit={(event) => postMoney("deposit", event)}>
            <h2>Deposit</h2>
            <MoneyFields form={moneyForm} setForm={setMoneyForm} />
            <button className="primary" disabled={loading}>Deposit</button>
          </form>

          <form className="panel" onSubmit={(event) => postMoney("withdraw", event)}>
            <h2>Withdraw</h2>
            <MoneyFields form={moneyForm} setForm={setMoneyForm} />
            <button className="danger" disabled={loading}>Withdraw</button>
          </form>

          <form className="panel" onSubmit={transfer}>
            <h2>Transfer</h2>
            <div className="two-col">
              <label>Sender<input value={transferForm.sender} onChange={(e) => updateForm(setTransferForm, "sender", e.target.value)} required /></label>
              <label>Receiver<input value={transferForm.reciever} onChange={(e) => updateForm(setTransferForm, "reciever", e.target.value)} required /></label>
            </div>
            <label>Amount<input type="number" min="0.01" step="0.01" value={transferForm.amount} onChange={(e) => updateForm(setTransferForm, "amount", e.target.value)} required /></label>
            <button className="secondary" disabled={loading}>Transfer</button>
          </form>

          <div className="panel">
            <h2>History</h2>
            <form className="inline-form" onSubmit={loadTransactions}>
              <input placeholder="Account number" value={transactionAccount} onChange={(e) => setTransactionAccount(e.target.value)} required />
              <button disabled={loading}>Load</button>
            </form>
          </div>

          <DataPanel title="Transactions" data={transactions} empty="No transactions loaded yet." render={(tx) => (
            <TransactionRow key={tx.transactionId || `${tx.account}-${tx.timestamp}-${tx.amount}`} tx={tx} />
          )} />
        </section>
      )}

      {isAuthed && activeTab === "admin" && isAdmin && (
        <section className="content-grid">
          <div className="panel wide">
            <div className="panel-head">
              <h2>Admin Overview</h2>
              <button className="primary" onClick={loadAdmin} disabled={loading}>Refresh admin data</button>
            </div>
            <div className="admin-columns">
              <MiniTable title="Users" rows={adminData.users} />
              <MiniTable title="Accounts" rows={adminData.accounts} />
              <MiniTable title="Transactions" rows={adminData.transactions} />
            </div>
          </div>
        </section>
      )}
    </main>
  );
}

function MoneyFields({ form, setForm }) {
  return (
    <div className="two-col">
      <label>Account<input value={form.accountNumber} onChange={(e) => setForm((current) => ({ ...current, accountNumber: e.target.value }))} required /></label>
      <label>Amount<input type="number" min="0.01" step="0.01" value={form.amount} onChange={(e) => setForm((current) => ({ ...current, amount: e.target.value }))} required /></label>
    </div>
  );
}

function DataPanel({ title, data, empty, render }) {
  return (
    <div className="panel wide">
      <h2>{title}</h2>
      <div className="list-area">
        {data.length ? data.map(render) : <p className="empty">{empty}</p>}
      </div>
    </div>
  );
}

function AccountCard({ account }) {
  return (
    <article className="account-card">
      <div>
        <span>{account.accountType || "ACCOUNT"}</span>
        <strong>{account.accountNumber}</strong>
      </div>
      <div>
        <span>{account.name || account.email}</span>
        <strong>{formatMoney(account.balance)}</strong>
      </div>
    </article>
  );
}

function TransactionRow({ tx }) {
  return (
    <article className="transaction-row">
      <div>
        <strong>{tx.type || "TRANSACTION"}</strong>
        <span>{tx.transactionId || tx.id || tx.account}</span>
      </div>
      <div>
        <strong>{formatMoney(tx.amount)}</strong>
        <span>{formatDate(tx.timestamp)}</span>
      </div>
      <small>Balance {formatMoney(tx.balance)}</small>
    </article>
  );
}

function MiniTable({ title, rows }) {
  const sampleKeys = Array.from(new Set(rows.flatMap((row) => Object.keys(row || {})))).slice(0, 4);
  return (
    <div className="mini-table">
      <h3>{title} <span>{rows.length}</span></h3>
      {rows.length === 0 ? (
        <p className="empty">No data.</p>
      ) : (
        <div className="table-scroll">
          <table>
            <thead>
              <tr>{sampleKeys.map((key) => <th key={key}>{key}</th>)}</tr>
            </thead>
            <tbody>
              {rows.slice(0, 8).map((row, index) => (
                <tr key={row.id || row.accountNumber || row.transactionId || index}>
                  {sampleKeys.map((key) => <td key={key}>{String(row?.[key] ?? "")}</td>)}
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}
    </div>
  );
}

export default App;
