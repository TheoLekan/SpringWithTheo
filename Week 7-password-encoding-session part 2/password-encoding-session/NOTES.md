# Week 7 Notes: Session Management & Stateless Auth

## Stateful Authentication

- User logs in with username & password → server provides a **session token (cookie)**.
- Server stores **session info** in a session registry mapping session IDs to users.
- Avoids repeated expensive password checks (no DB lookup for each request).
- On each request, server checks the **cookie** to identify the user & privileges.
- Server **maintains session state** (user data & privileges).
- **Scales poorly** as user count grows (server must track all active sessions).
- Large apps require a **centralized session store** (e.g., Redis) for multi-server setups.
- Centralized session stores add **complexity, latency, and cost**.

## Stateless Authentication

- After initial login, server sends a **token** (e.g., JWT) to the client.
- Client includes this **token** with every request.
- Token contains all **user info & privileges** — **no server lookup** needed.
- Token is **digitally signed** by the server (symmetric or asymmetric keys).
- Signature verifies authenticity and prevents tampering.
- Server does **not store session state**; the **client holds the token**.
- **Scales easily** (no server-side session tracking).
- Tokens can be invalidated by changing the signing key or using short expiration times.

## Analogy: Stateful vs Stateless

- **Stateful:**  
  You go to an amusement park, they give you a wristband with an **ID**.  
  Every ride checks your ID **against the park’s system** to confirm you’ve paid.  
  (Your wristband is just a **reference** to the park’s records.)

- **Stateless:**  
  You go to an amusement park, they give you a **special wristband** that **contains all your info**.  
  Every ride can check the wristband itself to confirm you’ve paid, **no central system lookup** needed.