import { useState } from "react";
import axios from "axios";

function CreateAccount() {

  const [name, setName] = useState("");
  const [mobile, setMobile] = useState("");
  const [balance, setBalance] = useState("");

  const createAccount = async (e) => {
    e.preventDefault();

    const token = localStorage.getItem("token");

    try {
      await axios.post(
        "http://localhost:8080/account/create",
        {
          name,
          mobile,
          balance,
        },
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );

      alert("Account Created");
    } catch (error) {
      console.log(error);
      alert("Failed");
    }
  };

  return (
    <div>
      <h1>Create Account</h1>

      <form onSubmit={createAccount}>
        <input
          type="text"
          placeholder="Customer Name"
          value={name}
          onChange={(e) => setName(e.target.value)}
        />

        <br /><br />

        <input
          type="text"
          placeholder="Mobile Number"
          value={mobile}
          onChange={(e) => setMobile(e.target.value)}
        />

        <br /><br />

        <input
          type="number"
          placeholder="Opening Balance"
          value={balance}
          onChange={(e) => setBalance(e.target.value)}
        />

        <br /><br />

        <button type="submit">
          Create Account
        </button>
      </form>
    </div>
  );
}

export default CreateAccount;