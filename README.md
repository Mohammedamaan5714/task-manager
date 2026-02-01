Perfect 👍
Below is a **professional, production-ready `README.md`** you can directly copy-paste into your GitHub repo.
It includes **project overview, tech stack, setup, API docs, auth flow, frontend usage, screenshots section, and future improvements**.

---

### 📄 **README.md**

```md
# 🗂️ Task Manager Application

A full-stack **Task Manager / Journal Application** built using **Spring Boot**, **MongoDB**, and **Vanilla HTML/CSS/JS** with **Spring Security (Basic Auth)**.

Users can:
- Register & login securely
- Create, view, update, and delete tasks
- Manage tasks per authenticated user
- Access protected APIs with authentication

---

## 🚀 Tech Stack

### Backend
- **Java 17**
- **Spring Boot 3**
- **Spring Security**
- **MongoDB Atlas**
- **Spring Data MongoDB**
- **BCrypt Password Encoder**

### Frontend
- **HTML5**
- **CSS3**
- **Vanilla JavaScript**
- **Fetch API**

### Tools
- Git & GitHub
- IntelliJ IDEA
- Postman

---

## 📁 Project Structure

```

task-manager/
│
├── src/main/java/net/shelly/taskManager
│   ├── config        # Spring Security config
│   ├── controller    # REST controllers
│   ├── entity        # MongoDB entities
│   ├── repository    # Mongo repositories
│   ├── service       # Business logic
│   └── TaskManagerApplication.java
│
├── src/main/resources
│   ├── static        # HTML, CSS, JS files
│   ├── application.yml
│
└── README.md

````

---

## 🔐 Authentication Flow

- Uses **HTTP Basic Authentication**
- Passwords are stored using **BCrypt hashing**
- Authentication handled via `UserDetailsService`
- Every task is linked to the authenticated user

---

## 🧑‍💻 API Endpoints

### 🔑 Authentication
| Method | Endpoint | Description |
|------|---------|------------|
| POST | `/user/register` | Register new user |

> Authentication is done via **Basic Auth Header**  
> `Authorization: Basic base64(username:password)`

---

### 📌 Task APIs (Authenticated)

| Method | Endpoint | Description |
|------|---------|------------|
| GET | `/entry` | Get all tasks of logged-in user |
| POST | `/entry` | Create a new task |
| GET | `/entry/id/{id}` | Get task by ID |
| PUT | `/entry/id/{id}` | Update task |
| DELETE | `/entry/id/{id}` | Delete task |

---

## 📦 Sample Request (POST Task)

```json
{
  "title": "My Task",
  "content": "This is a task description"
}
````

---

## 🌐 Frontend Pages

| Page             | Description           |
| ---------------- | --------------------- |
| `login.html`     | User login            |
| `register.html`  | New user registration |
| `dashboard.html` | Task management UI    |

### Features

* Stores auth token in `localStorage`
* Automatically attaches auth header
* Redirects unauthenticated users
* Clean & minimal UI

---

## ⚙️ Application Configuration

### application.yml

```yaml
server:
  port: 8081
  servlet:
    context-path: /task

spring:
  data:
    mongodb:
      uri: YOUR_MONGODB_URI
```

---

## ▶️ Running the Project

### 1️⃣ Clone Repository

```bash
git clone https://github.com/Mohammedamaan5714/task-manager.git
cd task-manager
```

### 2️⃣ Configure MongoDB

* Create a MongoDB Atlas cluster
* Update `application.yml` with your URI

### 3️⃣ Run Application

```bash
mvn spring-boot:run
```

### 4️⃣ Open Browser

```
http://localhost:8081/task/login.html
```

---

## 🧪 Testing APIs

Use **Postman** or browser fetch:

* Add **Authorization → Basic Auth**
* Username & password from registered user


## 🛡️ Security Notes

* CSRF disabled (stateless REST APIs)
* Session management is stateless
* Role-based structure ready for expansion



## 👤 Author

**Mohammed Amaan**
📧 GitHub: [@Mohammedamaan5714](https://github.com/Mohammedamaan5714)

