# 📦 Grails 3.3.9 + PostgreSQL Starter

This project is a starter template for building RESTful Grails applications using PostgreSQL with Docker. It includes user CRUD, JWT authentication, and structured API endpoints.

---

## 📚 Contents

- [Features](#features)
- [Requirements](#requirements)
- [Installation](#installation)
- [Docker Database Setup](#docker-database-setup)
- [API Endpoints](#api-endpoints)
- [Running Tests](#running-tests)
- [Versions](#versions)
- [License](#license)

---

## 🚀 Features

- 🧾 CRUD operations for User
- 🔐 JWT Authentication
- 🐘 PostgreSQL integration
- ✅ Validation & Error handling
- 📦 RESTful API structure

---

## 🛠 Requirements

| Version | Description                        | Download |
|----------------------------|---|----------|
| SDKMAN                     | JDK, SDK manager  | [Download](https://sdkman.io/) |
| Java 8                     | SDKMAN ашиглан татна  |  |
| Grails 3.3.9               |   | [Download](https://grails.apache.org/download.html) |
| Gradle 3.5                 |   |  |
| Groovy SDK 2.5.23          |   | [Download](https://groovy.apache.org/download.html) |
| Docker (optional, for DB)  |   | [Download](https://www.docker.com/products/docker-desktop/) |
| PostgreSQL 13              | Docker ашиглан татаж болно  |  |
| IntelliJ IDEA (2020.3.4)   |   | [Download](https://www.jetbrains.com/idea/download/other.html) |

---

## ⚙️ Installation

1. Clone the repository:

```bash
git clone https://github.com/H21ook/test-with-postgres.git
cd test-with-postgres
```

2. Configure your database in `grails-app/conf/application.yml`:

```yaml
dataSource:
  url: jdbc:postgresql://localhost:5432/task_db
  username: postgres
  password: postgres
```

3. Run the application:

```bash
./gradlew clean bootRun
```

---

## 🐳 Docker Database Setup

### Start PostgreSQL using Docker

```bash
docker run --name my-postgres \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=postgres \
  -e POSTGRES_DB=task_db \
  -p 5432:5432 \
  -d postgres:13
```

- Database: `task_db`
- Username: `postgres`
- Password: `postgres`
- Accessible at: `localhost:5432`

---

## 📤 API Endpoints

| Method | Endpoint     | Description             |
|--------|--------------|-------------------------|
| POST   | `/users`     | Create new user         |
| GET    | `/users`     | List all users          |
| POST   | `/login`     | Authenticate user       |

> JSON requests and responses are used throughout.

---

## 📄 License

This project is licensed under the MIT License. See the `LICENSE` file for details.