# Smart Banking React Frontend

React frontend for `bank.jar`.

## Run

```bash
npm install
npm run dev
```

The app runs at `http://127.0.0.1:5173`. The backend CORS config allows `http://localhost:5173`, so use that URL in the browser if your backend rejects `127.0.0.1`.

By default the frontend calls `http://localhost:8080`. To change it, create `.env`:

```bash
VITE_API_BASE_URL=http://localhost:8080
```

## Backend Endpoints Used

- `POST /user/register`
- `POST /user/login`
- `POST /account/create`
- `GET /account/get_e/{email}`
- `GET /account/get_ac/{accountNumber}`
- `POST /transaction/deposit?accountNumber=&amount=`
- `POST /transaction/withdraw?accountNumber=&amount=`
- `POST /transaction/transfer?sender=&reciever=&amount=`
- `GET /transaction/transactions?accountnumber=`
- `GET /admin/users`
- `GET /admin/account`
- `GET /admin/transaction`
