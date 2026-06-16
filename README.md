# NewsFlow AI 📰

A **production-ready full stack Android news application** built with modern Android development practices and a cloud-deployed Spring Boot backend.

## 🔗 Links

- **Android App:** [github.com/thakursanju/NewsFlow-AI](https://github.com/thakursanju/NewsFlow-AI)
- **Backend API:** [github.com/thakursanju/NewsFlow-Backend](https://github.com/thakursanju/NewsFlow-Backend)
- **Live API:** [newsflow-backend-rg27.onrender.com](https://newsflow-backend-rg27.onrender.com)

---

## 🏗️ Full Stack Architecture

```
┌─────────────────┐        ┌──────────────────┐        ┌─────────────────┐
│   Android App   │ ──────▶│  Spring Boot API  │ ──────▶│   PostgreSQL    │
│ Jetpack Compose │  REST  │   Render.com      │  JPA   │   Neon.tech     │
└─────────────────┘        └──────────────────┘        └─────────────────┘
         │                          │
         ▼                          ▼
┌─────────────────┐        ┌──────────────────┐
│    NewsAPI      │        │    Firebase       │
│  (News Feed)    │        │ Authentication    │
└─────────────────┘        └──────────────────┘
```

---

## 📱 Screenshots

> Add your screenshots here after taking them from the emulator

## 📱 Screenshots

## 📱 Screenshots

<p align="center">
  <img src="https://github.com/user-attachments/assets/50e0391e-1af1-4ba8-9a77-7fbcac2be5a8" width="45%" alt="Headlines"/>
  <img src="https://github.com/user-attachments/assets/599155fb-f00b-4324-a626-9e87ad2b34d5" width="45%" alt="Search"/>
</p>
<p align="center">
  <img src="https://github.com/user-attachments/assets/bc7be74f-f4a5-4773-a681-d55249808744" width="45%" alt="Favorites"/>
  <img src="https://github.com/user-attachments/assets/b28b2604-c871-41ec-b6a2-6cc03caca758" width="45%" alt="Settings"/>
</p>

## ✨ Features

- 📰 Browse top headlines from 50+ countries
- 🔍 Search articles by keyword
- ❤️ Save favorites — synced to cloud database
- 🔐 Google Sign-In + Email/Password authentication
- 🌙 Dark mode support
- 🔔 Background news notifications
- 📶 Offline reading with local cache

---

## 🛠️ Tech Stack

### Android (Frontend)
| Technology | Purpose |
|------------|---------|
| Kotlin | Primary language |
| Jetpack Compose | Modern declarative UI |
| MVVM + Clean Architecture | App structure |
| Retrofit + OkHttp | REST API calls |
| Room Database | Local offline storage |
| Paging 3 | Infinite scroll pagination |
| Firebase Auth | User authentication |
| DataStore | User preferences |
| WorkManager | Background notifications |
| Coil | Image loading |
| Kotlin Coroutines + Flow | Async operations |

### Backend
| Technology | Purpose |
|------------|---------|
| Kotlin | Primary language |
| Spring Boot 4.1 | REST API framework |
| Spring Data JPA | Database ORM |
| PostgreSQL | Cloud database |
| Hibernate | Database migrations |
| Docker | Containerization |

### Infrastructure
| Service | Purpose |
|---------|---------|
| Firebase | Authentication |
| NewsAPI | News data source |
| Neon.tech | PostgreSQL cloud database |
| Render.com | Backend hosting |
| GitHub | Version control |

---

## 🚀 API Endpoints

```
GET  /api/v1/users/favorites/{uid}     → Get user favorites
POST /api/v1/users/favorites           → Save favorite article
DELETE /api/v1/users/favorites/{uid}   → Remove favorite
GET  /api/v1/users/preferences/{uid}   → Get user preferences
POST /api/v1/users/preferences         → Save user preferences
```

---

## ⚙️ Setup & Installation

### Android App

1. Clone the repo:
```bash
git clone https://github.com/thakursanju/NewsFlow-AI.git
```

2. Open in Android Studio

3. Create `.env` file in root:
```
NEWS_API_KEY=your_newsapi_key_here
```
Get your free key at [newsapi.org](https://newsapi.org/register)

4. Add `google-services.json` from Firebase Console to `app/` folder

5. Run on emulator or real device

### Backend

1. Clone the repo:
```bash
git clone https://github.com/thakursanju/NewsFlow-Backend.git
```

2. Update `application.properties` with your PostgreSQL URL

3. Run locally:
```bash
./gradlew bootRun
```

---

## 📁 Project Structure

```
NewsFlow-AI/
├── app/src/main/kotlin/com/example/
│   ├── data/
│   │   ├── local/          # Room Database
│   │   ├── remote/         # Retrofit API
│   │   ├── model/          # Data models
│   │   └── repository/     # Data layer
│   ├── domain/
│   │   ├── model/          # Domain models
│   │   └── repository/     # Repository interfaces
│   ├── presentation/
│   │   ├── screens/        # Compose UI screens
│   │   └── viewmodel/      # ViewModels
│   ├── di/                 # Dependency injection
│   └── ui/theme/           # Material Design theme
```

---

## 👨‍💻 Author

**Khushvinder Thakur**
- GitHub: [@thakursanju](https://github.com/thakursanju)

---

## 📄 License

MIT License — feel free to use this project for learning!
