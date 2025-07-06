# 🧠 Multilevel Queue Scheduler (Java + Spring Boot + HTML/CSS)

A simple operating system simulator that demonstrates the **Multilevel Queue Scheduling algorithm** using Java (Spring Boot) for the backend and a clean HTML/CSS (Tailwind) interface for the frontend.

---

## 📌 Features

- Add multiple processes with:
  - PID
  - Arrival Time
  - Burst Time
  - Queue Type (`System`, `Interactive`, `Batch`)
- Executes **Multilevel Queue Scheduling**
- Displays:
  - Execution Order
  - Waiting Times
  - Turnaround Times
- Modern OS-style web interface using Tailwind CSS

---

## 🛠 Tech Stack

- **Backend**: Java, Spring Boot, Maven
- **Frontend**: HTML, Tailwind CSS, Vanilla JS
- **Build Tool**: Maven

---

## 🚀 How to Run

### 1. Clone the repository

```bash
git clone https://github.com/your-username/mlq-scheduler.git
cd mlq-scheduler
2. Run the Spring Boot app
bash
Copy
Edit
./mvnw spring-boot:run
If on Windows, use:

bash
Copy
Edit
mvn spring-boot:run
Spring Boot will start on port 8080 (http://localhost:8080).

🌐 How to Use
Open http://localhost:8080 in your browser.

Fill in process details.

Add multiple processes.

Click Run Scheduler.

View:

Execution Order

Waiting Time per process

Turnaround Time per process

📁 Project Structure
bash
Copy
Edit
mlq-scheduler/
├── src/
│   └── main/
│       ├── java/com/os/mlq_scheduler/
│       │   ├── model/            # All classes (Controller, Service, DTO) here
│       └── resources/
│           └── static/index.html # Frontend UI
├── pom.xml
└── README.md
📷 Screenshot
(You can add a screenshot here if you want, by uploading the image in your repo and linking it)

🧠 Concepts Covered
OS Process Scheduling

Multilevel Queue Algorithm

REST API design

Form handling via JavaScript

JSON exchange between frontend and backend

👨‍💻 Author
Samarth Firangi
📍 Bengaluru
🔗 LinkedIn
🔗 GitHub

