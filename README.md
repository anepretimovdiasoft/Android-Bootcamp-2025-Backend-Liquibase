# Android Bootcamp 2025 — Backend (Liquibase)

REST API сервис на Spring Boot с аутентификацией, миграциями Liquibase и поддержкой Docker.

## Технологии

- **Java 17**
- **Spring Boot 3.5.5**
- **Spring Security** (Basic Authentication)
- **Spring Data JPA**
- **Liquibase** (миграции БД)
- **PostgreSQL** (production) / **H2** (development)
- **Swagger/OpenAPI** (документация API)
- **Docker & Docker Compose**

---

## Быстрый старт

### Запуск через Docker (рекомендуется)

**Требования:** Docker и Docker Compose

```bash
# Клонировать репозиторий
git clone <repository-url>
cd Android-Bootcamp-2025-Backend-Liquibase

# Собрать и запустить (PostgreSQL + приложение)
docker-compose up --build

# Или в фоновом режиме
docker-compose up --build -d
```

Приложение будет доступно по адресу: http://localhost:8080

**Остановка:**
```bash
# Остановить контейнеры
docker-compose down

# Остановить и удалить данные БД
docker-compose down -v
```

---

### Локальный запуск (без Docker)

**Требования:** Java 17, Maven 3.6+

```bash
# Клонировать репозиторий
git clone <repository-url>
cd Android-Bootcamp-2025-Backend-Liquibase

# Собрать проект
mvn clean package -DskipTests

# Запустить (использует H2 in-memory БД)
mvn spring-boot:run
```

Или запустить JAR напрямую:
```bash
java -jar target/Android-Bootcamp-2025-Backend-Liquibase-1.0-SNAPSHOT.jar
```

Приложение будет доступно по адресу: http://localhost:8080

---

## Доступные URL

| URL | Описание |
|-----|----------|
| http://localhost:8080/swagger-ui/index.html | Swagger UI (документация API) |
| http://localhost:8080/h2-console | H2 Console (только локально) |

---

## Конфигурация

### Профили Spring

| Профиль | База данных | Описание |
|---------|-------------|----------|
| `local` (default) | H2 in-memory | Для локальной разработки |
| `docker` | PostgreSQL | Для Docker окружения |

Переключение профиля:
```bash
# Локально с PostgreSQL
java -jar app.jar --spring.profiles.active=docker

# Или через переменную окружения
SPRING_PROFILES_ACTIVE=docker java -jar app.jar
```

### Переменные окружения (Docker)

| Переменная | Значение по умолчанию | Описание |
|------------|----------------------|----------|
| `SPRING_DATASOURCE_URL` | jdbc:postgresql://postgres:5432/bootcamp_db | URL подключения к БД |
| `SPRING_DATASOURCE_USERNAME` | bootcamp | Пользователь БД |
| `SPRING_DATASOURCE_PASSWORD` | bootcamp123 | Пароль БД |

---

## Аутентификация

Приложение использует **Basic Authentication**.

Тестовые пользователи создаются через Liquibase миграции.

Пример запроса с авторизацией:
```bash
curl -u username:password http://localhost:8080/api/person
```

---

## API Endpoints

### Person

| Метод | URL | Доступ | Описание |
|-------|-----|--------|----------|
| POST | `/api/person/register` | Public | Регистрация |
| GET | `/api/person/{id}` | USER, ADMIN | Получить по ID |
| GET | `/api/person/username/{username}` | Public | Получить по username |
| GET | `/api/person/paginated` | Public | Список с пагинацией |
| PUT | `/api/person/{id}` | USER, ADMIN | Обновить |
| DELETE | `/api/person/{id}` | USER, ADMIN | Удалить |

### Authority

| Метод | URL | Доступ | Описание |
|-------|-----|--------|----------|
| GET | `/api/authority` | ADMIN | Список ролей |
| POST | `/api/authority` | ADMIN | Создать роль |

---

## Структура проекта

```
├── src/main/java/com/example/edu/
│   ├── config/          # Конфигурация (Security, etc.)
│   ├── controller/      # REST контроллеры
│   ├── dto/             # Data Transfer Objects
│   ├── entity/          # JPA сущности
│   ├── exception/       # Обработка ошибок
│   ├── repository/      # Spring Data репозитории
│   ├── service/         # Бизнес-логика
│   └── utils/           # Утилиты (маппинг)
├── src/main/resources/
│   ├── db.changelog/    # Liquibase миграции
│   └── application.yml  # Конфигурация
├── Dockerfile           # Сборка Docker образа
├── docker-compose.yml   # Оркестрация контейнеров
└── pom.xml              # Maven зависимости
```

---

## Разработка

### Сборка JAR

```bash
mvn clean package -DskipTests
```

### Запуск тестов

```bash
mvn test
```

### Пересборка Docker образа

```bash
docker-compose build --no-cache
docker-compose up
```
