# 📌 MyJobTracker Backend

A **Spring Boot** backend for the **MyJobTracker** application, providing RESTful APIs for tracking job applications. This backend handles data persistence using **PostgreSQL** and follows standard **CRUD operations**.

---

## ℹ️ About This Project
This backend is part of my first full-stack project. As I am currently looking for entry-level positions, I wanted to create a **job application tracker** that helps me stay organized while also building hands-on experience with **Spring Boot, REST APIs, and PostgreSQL**.

It may not be perfect, but it’s my first public backend project outside of university. I’m constantly learning and improving, so feedback is always welcome!

---

## Features
- RESTful API for managing job applications.
- CRUD operations (Create, Read, Update, Delete) for job applications.
- Uses **Spring Boot**, **Spring Data JPA**, and **PostgreSQL**.
- CORS support for frontend communication.
- Environment-based API configuration using `.env`.
- **Docker-based PostgreSQL database** ensuring data persists across restarts.

---

## 🛠 Setup & Installation

### **1️⃣ Clone the Repo**
```sh
git clone https://github.com/JCollado02/MyJobTracker-BE.git
cd MyJobTracker-BE
```

### **2️⃣ Start PostgreSQL with Docker**
Before running the backend, **ensure Docker is running** and start the PostgreSQL container (if needed):
```sh
docker-compose up -d
```

### **3️⃣ Configure Environment Variables**
Create a `.env` file in the project root and define database credentials:

```sh
POSTGRES_USER=username
POSTGRES_PASSWORD=password
```

### **4️⃣ Running the Application**
#### **Using IntelliJ IDEA (For Development)**
1. **Start Docker** (if not already running).
2. Open the project in IntelliJ.
3. Click the **Run** button on `MyJobTrackerApplication.java` (the main Spring Boot file).
4. The backend will start and be available at **`http://localhost:8080`**.

---

## 📡 API Endpoints

| Method  | Endpoint                        | Description                 |
|---------|--------------------------------|-----------------------------|
| `GET`   | `/api/v1/job-applications`      | Fetch all job applications  |
| `POST`  | `/api/v1/job-applications`      | Add a new job application   |
| `PUT`   | `/api/v1/job-applications/{id}` | Update a job application    |
| `DELETE`| `/api/v1/job-applications/{id}` | Delete a job application    |

---

## 🛠 Technologies Used
- **Spring Boot** (Backend Framework)
- **Spring Data JPA** (Database ORM)
- **PostgreSQL** (Database - Running in Docker)
- **Docker** (For database containerization)

---

## 📝 License
This project is **open-source** and serves as a learning experience for backend development. Feel free to fork and contribute!

---

## Author
Created by **Jim Collado** with help from Amigoscode on YT – always open to feedback and learning opportunities!

📧 Email: jimcollado25@gmail.com  
🔗 GitHub: [JCollado02](https://github.com/JCollado02)

