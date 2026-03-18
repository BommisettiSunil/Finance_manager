💰 Finance Manager

A web-based Loan Management System built using Java Servlets, JSP, JDBC, and MySQL.

---

🚀 Features

- 👤 Admin Authentication (Login / Signup)
- 🧑‍🤝‍🧑 Manage Customers
- 💵 Give Loans
- 📊 EMI Calculation (Normal + Monthly Flat)
- 💳 Record EMI Payments
- 📅 Auto Installment Generation
- 🔔 EMI Reminder System (Email)
- ⚠️ Penalty System for Late Payments
- 📈 Dashboard Analytics

---

🛠️ Technologies Used

- Java (Servlets, JSP)
- JDBC
- MySQL
- HTML, CSS, Bootstrap

---

⚙️ Setup Instructions

1. Clone the repository

2. Create MySQL database:
   
   CREATE DATABASE finance_manager;

3. Create tables: customers, loans, payments, users

4. Create "db.properties" file:
   
   db.url=jdbc:mysql://localhost:3306/finance_manager
db.username=your_username
db.password=your_password

5. Run on Apache Tomcat

---

🔐 Security Note

Sensitive data like DB credentials are not included. Use "db.properties.example".

---

👨‍💻 Author

Sunil
