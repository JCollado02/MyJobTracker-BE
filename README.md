# 📌 MyJobTracker Backend

A **Spring Boot** backend for the **MyJobTracker** application, providing RESTful APIs for tracking job applications. This backend handles data persistence using **PostgreSQL** and follows standard **CRUD operations**.

---

## About This Project
This backend is part of my first full-stack project. As I am currently looking for entry-level positions, I wanted to create a **job application tracker** that helps me stay organized while also building hands-on experience with **Spring Boot, REST APIs, and PostgreSQL**.

A live version is available, but you can also host your own instance. See below for setup instructions.

---
## Live Demo & Test Credentials  
**Live Demo:** [Try it here!](https://my-job-tracker-fe.vercel.app/)  

🔑 **Test Login Credentials:**  
- **Email:** `test@example.com`  
- **Password:** `password`

Feel free to **log in and test the app!** Try to break it even, this entire project is really 
meant to serve as a learning experience!

---

## 📸 Screenshots  
### **Login Screen**  
![Login Screen](https://raw.githubusercontent.com/JCollado02/MyJobTracker-BE/main/screenshots/login.png)  

### **Dashboard**  
![Dashboard](https://raw.githubusercontent.com/JCollado02/MyJobTracker-BE/main/screenshots/dashboard.png)  

---

## 🛠 Features
- **Vite + React** for a fast development experience.
- **CRUD operations** (Create, Read, Update, Delete) for job applications.
- **TailwindCSS** for a modern and responsive UI.
- Uses **Spring Boot**, **Spring Data JPA**, and **PostgreSQL**.
- **Authentication via JWT cookies**.  
- **Environment-based API configuration** using `.env`.
- **Deployed on Vercel** (Frontend) + Render (Backend).

---

## 🛠 Setup & Installation

### **1️⃣ Clone the Repo**
```sh
git clone https://github.com/JCollado02/MyJobTracker-BE.git
cd MyJobTracker-BE
```

### **2️⃣ Start PostgreSQL with Docker**
Before running the backend, **ensure Docker is running** and start the PostgreSQL container:
```sh
docker-compose up -d
```

Or manually configure a PostgreSQL database if not using Docker. For this test version we link it to a DB hosted on Render!

### **3️⃣ Configure Environment Variables**
Create a `.env` file in the project root and define database credentials:
```sh
DATABASE_URL=jdbc:postgresql://localhost:5432/myjobtracker # or link to your hosted database
DATABASE_USER=your_db_user
DATABASE_PASSWORD=your_db_password
API_KEY=your_custom_api_key

JWT_SECRET=your_generated_secret
```

### **4️⃣ Running the Application**
#### **Using IntelliJ IDEA or Terminal**
If hosting locally, open the project in **IntelliJ** and run `MyJobTrackerApplication.java`.
Otherwise upload to a hosting platform of your chosing, or use Render like the demo does!
The backend will be available at **`http://localhost:8080`**.

---

## 📡 API Endpoints

| Method  | Endpoint                        | Description                 | Auth Required |
|---------|--------------------------------|-----------------------------|--------------|
| `POST`  | `/api/v1/login`                | Login (sets JWT cookie)     | ❌ |
| `POST`  | `/api/v1/logout`               | Logout (clears JWT cookie)  | ✅ |
| `GET`   | `/api/v1/auth-check`           | Check if user is logged in  | ✅ |
| `GET`   | `/api/v1/job-applications`      | Fetch all job applications  | ✅ (`X-API-KEY`) |
| `POST`  | `/api/v1/job-applications`      | Add a new job application   | ✅ (`X-API-KEY`) |
| `PUT`   | `/api/v1/job-applications/{id}` | Update a job application    | ✅ (`X-API-KEY`) |
| `DELETE`| `/api/v1/job-applications/{id}` | Delete a job application    | ✅ (`X-API-KEY`) |

> **Note:** API requests require an `X-API-KEY` for security.  
> The backend checks this key before processing requests to prevent unauthorized use.  
> If you're hosting your own version, generate a secure API key and set it in both the **backend** and **frontend** `.env` files.


---

## Deployment Notes
### **Hosting on Render**
You of course can host this backend! For the live demo I chose Render following these instructions:
1. Create a new **Spring Boot** web service on **[Render](https://render.com/)**.
2. Set **Environment Variables** (`DATABASE_URL`, `JWT_SECRET`, `API_KEY`, etc.).
3. Deploy!

### **Frontend CORS Configuration**
If hosting your own frontend, update the **CORS Config**. Currently it defaults to the public site's link hosted on **[Vercel](https://vercel.com)** and localhost:
```java
config.setAllowedOrigins(List.of("https://your-frontend-url.com")); # can also add additional links
```

---

## Contributing
This is an open-source project. Feel free to fork, modify, and improve it.
If you host your own version, customize it however you like! And even try to
break the test site! This is a simple project meant for introducing me to
full-stack development so if you know how to do something better, tell me! I'd
love to learn!

📧 **Contact**: [jimcollado25@gmail.com](mailto:jimcollado25@gmail.com)  
🔗 **GitHub**: [JCollado02](https://github.com/JCollado02)

