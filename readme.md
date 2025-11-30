# 🏆 AthleteIQ — Backend API (FastAPI + PostgreSQL)

AthleteIQ is a backend service for tracking amateur and intramural soccer performance.
Teams can log matches and player stats (goals, assists, minutes, shots, clean sheets, etc.) and later generate analytics like per-90 metrics, form trends, and lineup insights.

This repository contains the **backend-only** implementation built with **Python, FastAPI, and PostgreSQL**.

---

## 🚀 Features

* Team + match + player stat tracking
* Log goals, assists, minutes played, positions, shots, clean sheets, and more
* Store match metadata (date, league, opponent, score, format: 11s/7s/5s)
* FastAPI-based REST API with Pydantic validation
* PostgreSQL relational modeling
* Alembic migrations
* Clean modular structure ready for analytics expansion
* Future-ready for AI/ML insights or Go microservices

---

## 📁 Project Structure

```
athleteiq-backend/
│
├── app/
│   ├── main.py               # FastAPI entrypoint
│   ├── config.py             # env vars + settings
│   ├── database.py           # PostgreSQL connection
│   ├── models/               # SQLAlchemy models (User, Team, Match, PlayerStats)
│   ├── schemas/              # Pydantic request/response models
│   ├── routers/              # API routes (auth, teams, matches, stats)
│   ├── services/             # analytics + business logic
│   ├── utils/                # JWT, hashing, helpers
│   └── migrations/           # Alembic migrations
│
├── tests/                    # pytest tests
├── alembic.ini
├── requirements.txt
├── docker-compose.yml
└── README.md
```

---

## 🛠️ Tech Stack

* **Python 3.11+**
* **FastAPI**
* **PostgreSQL**
* **SQLAlchemy + Alembic**
* **Pydantic**
* **Docker Compose**
* **pytest**

---

## 📦 Installation

### 1. Clone the repo

```bash
git clone https://github.com/your-username/athleteiq-backend
cd athleteiq-backend
```

### 2. Create virtual environment

```bash
python3 -m venv venv
source venv/bin/activate
```

### 3. Install dependencies

```bash
pip install -r requirements.txt
```

### 4. Start PostgreSQL (Docker)

```bash
docker-compose up -d
```

### 5. Run the API

```bash
uvicorn app.main:app --reload
```

API docs will be available at:

👉 [http://localhost:8000/docs](http://localhost:8000/docs)
👉 [http://localhost:8000/redoc](http://localhost:8000/redoc)

---

## 📝 Example Data Fields (v1)

### Match-level

* date
* opponent_name
* final_score_team
* final_score_opponent
* league_name
* league_format (e.g., "11s", "7s", "5s")

### Player stats (per match)

* minutes_played
* position_played
* goals
* assists
* shots
* shots_on_target
* clean_sheet (bool)

---

## 🧪 Running Tests

```bash
pytest
```

---

## 🗺️ Roadmap (Upcoming)

* Player analytics (goals/90, form, consistency)
* Team analytics (strong/weak positions, scoring patterns)
* AI-generated training suggestions
* Go microservice for high-performance analytics
* JWT authentication
* Role-based access (coach vs player)

---

## 👫 Contributing

PRs and issues are welcome!
This project is actively being expanded as a real-world portfolio + product piece.

---