# NewsFlow AI 📰

> A **production-ready full stack news application** — Android frontend powered by Jetpack Compose + Kotlin, cloud backend built with Spring Boot in Kotlin, PostgreSQL database, and Firebase Authentication.

<p align="center">
  <img src="https://img.shields.io/badge/Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white"/>
  <img src="https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white"/>
  <img src="https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white"/>
  <img src="https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white"/>
  <img src="https://img.shields.io/badge/Firebase-FFCA28?style=for-the-badge&logo=firebase&logoColor=black"/>
  <img src="https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white"/>
</p>

---

## 🔗 Links

| | Link |
|---|---|
| 📱 Android App | [github.com/thakursanju/NewsFlow-AI](https://github.com/thakursanju/NewsFlow-AI) |
| ⚙️ Spring Boot Backend | [github.com/thakursanju/NewsFlow-Backend](https://github.com/thakursanju/NewsFlow-Backend) |
| 🌐 Live API | [newsflow-backend-rg27.onrender.com](https://newsflow-backend-rg27.onrender.com) |

---

## 🏗️ Full Stack Architecture

```
┌──────────────────────────────────────────────────────────────────┐
│                        ANDROID APP (Kotlin)                      │
│         Jetpack Compose · MVVM · Clean Architecture              │
└────────────────────────┬─────────────────────────────────────────┘
                         │ REST API (Retrofit)
          ┌──────────────▼──────────────────┐
          │   SPRING BOOT BACKEND (Kotlin)   │
          │   Render.com · Docker · Port 8080│
          │   5 REST Endpoints · Spring JPA  │
          └──────┬──────────────┬────────────┘
                 │              │
    ┌────────────▼───┐   ┌──────▼──────────┐
    │  PostgreSQL DB  │   │    Firebase     │
    │  Neon.tech      │   │  Authentication │
    │  Cloud Hosted   │   │  JWT Tokens     │
    └────────────────┘   └─────────────────┘
                 │
    ┌────────────▼───┐
    │    NewsAPI     │
    │  1000+ Sources │
    │  50+ Countries │
    └────────────────┘
```

---

## 📱 Screenshots

<p align="center">
  <img src="https://github.com/user-attachments/assets/50e0391e-1af1-4ba8-9a77-7fbcac2be5a8" width="45%" alt="Headlines"/>
  <img src="https://github.com/user-attachments/assets/599155fb-f00b-4324-a626-9e87ad2b34d5" width="45%" alt="Search"/>
</p>
<p align="center">
  <img src="https://github.com/user-attachments/assets/bc7be74f-f4a5-4773-a681-d55249808744" width="45%" alt="Favorites"/>
  <img src="https://github.com/user-attachments/assets/b28b2604-c871-41ec-b6a2-6cc03caca758" width="45%" alt="Settings"/>
</p>

---

## ✨ Features

| Feature | Description |
|---------|-------------|
| 📰 Top Headlines | Browse news from 50+ countries in real time |
| 🔍 Smart Search | Search 1000+ articles by keyword |
| ❤️ Cloud Favorites | Save articles — synced to PostgreSQL via REST API |
| 🔐 Auth | Google Sign-In + Email/Password via Firebase |
| 🌙 Dark Mode | System-aware dark/light theme |
| 🔔 Notifications | Background news alerts via WorkManager |
| 📶 Offline Mode | Read saved articles without internet via Room DB |

---

## 🛠️ Tech Stack

### 📱 Android Frontend — Kotlin

| Technology | Version | Purpose |
|------------|---------|---------|
| **Kotlin** | 2.0 | Primary language |
| **Jetpack Compose** | Latest | Declarative UI framework |
| **MVVM + Clean Architecture** | — | Separation of concerns |
| **Retrofit2 + OkHttp** | 2.9 | HTTP client for REST APIs |
| **Room Database** | 2.6 | Local SQLite storage |
| **Paging 3** | 3.2 | Infinite scroll pagination |
| **Firebase Auth** | 34.x | User authentication + JWT |
| **DataStore Preferences** | 1.0 | User settings storage |
| **WorkManager** | 2.9 | Background tasks |
| **Coil** | 2.6 | Async image loading |
| **Kotlin Coroutines + Flow** | 1.8 | Async programming |
| **Navigation Compose** | 2.7 | Screen navigation |

### ⚙️ Backend — Kotlin + Spring Boot

| Technology | Version | Purpose |
|------------|---------|---------|
| **Kotlin** | 1.9 | Primary language |
| **Spring Boot** | 4.1.0 | REST API framework |
| **Spring Data JPA** | — | Database ORM layer |
| **Hibernate** | 7.4 | Auto table creation + migrations |
| **PostgreSQL Driver** | — | Database connector |
| **Spring Security** | — | API security layer |
| **Docker** | — | Containerized deployment |

### ☁️ Infrastructure

| Service | Purpose |
|---------|---------|
| **Firebase** | Auth + JWT token management |
| **NewsAPI** | Real-time news data source |
| **Neon.tech** | Serverless PostgreSQL (cloud) |
| **Render.com** | Backend hosting (Docker) |
| **GitHub** | Version control (2 repos) |

---

## 🚀 Live API Endpoints

Base URL: `https://newsflow-backend-rg27.onrender.com`

```http
GET    /api/v1/users/favorites/{uid}    → Get all saved articles
POST   /api/v1/users/favorites          → Save a favorite article
DELETE /api/v1/users/favorites/{uid}    → Remove a favorite
GET    /api/v1/users/preferences/{uid}  → Get user preferences
POST   /api/v1/users/preferences        → Update user preferences
```

---

## ⚙️ Setup & Installation

### Android App

```bash
# 1. Clone
git clone https://github.com/thakursanju/NewsFlow-AI.git

# 2. Open in Android Studio

# 3. Create .env file
echo "NEWS_API_KEY=your_key_here" > .env
# Get free key at newsapi.org/register

# 4. Add google-services.json from Firebase Console to app/ folder

# 5. Run on emulator or device
```

### Backend

```bash
# 1. Clone
git clone https://github.com/thakursanju/NewsFlow-Backend.git

# 2. Add your PostgreSQL URL to application.properties

# 3. Run locally
./gradlew bootRun

# 4. Or run with Docker
docker build -t newsflow-backend .
docker run -p 8080:8080 newsflow-backend
```

---

## 📁 Project Structure

```
NewsFlow-AI/
├── app/src/main/kotlin/com/example/
│   ├── data/
│   │   ├── local/          # Room Database + DAOs
│   │   ├── remote/         # Retrofit + API Service
│   │   ├── model/          # Data models
│   │   └── repository/     # Repository implementations
│   ├── domain/
│   │   ├── model/          # Domain models
│   │   └── repository/     # Repository interfaces
│   ├── presentation/
│   │   ├── screens/        # Compose UI screens (6 screens)
│   │   └── viewmodel/      # ViewModels + StateFlow
│   ├── di/                 # Dependency injection
│   └── ui/theme/           # Material Design 3 theme

NewsFlow-Backend/
├── src/main/kotlin/com/newsflow/backend/
│   ├── controller/         # REST API controllers
│   ├── model/              # JPA entities (User, Article)
│   ├── repository/         # Spring Data JPA repositories
│   └── SecurityConfig.kt   # Spring Security config
```

---

## 👨‍💻 Author

**Khushvinder Thakur**
📄 License

MIT License — feel free to use this project for learning!
---

## 📄 License

MIT License — feel free to use this project for learning!
