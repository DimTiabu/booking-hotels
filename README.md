# Booking Hotels

## Описание проекта

Приложение **Booking Hotels** — это система бронирования отелей.  
Пользователи могут просматривать доступные отели, узнавать детали, выбирать даты, оформлять бронирование и видеть историю своих бронирований.  
Сервис реализован на базе Spring Boot и поддерживает работу с PostgreSQL, MongoDB и Apache Kafka.

## Стек используемых технологий
![Static Badge](https://img.shields.io/badge/Java-17-blue)
![Static Badge](https://img.shields.io/badge/Spring_Boot-3.1.2-green)
![Static Badge](https://img.shields.io/badge/PostgreSQL-grey)
![Static Badge](https://img.shields.io/badge/MongoDB-grey)
![Static Badge](https://img.shields.io/badge/Kafka-grey)
![Static Badge](https://img.shields.io/badge/Spring_Security-grey)
![Static Badge](https://img.shields.io/badge/MapStruct-grey)
![Static Badge](https://img.shields.io/badge/Lombok-grey)
![Static Badge](https://img.shields.io/badge/Gradle-grey)

## Инструкция по локальному запуску проекта

### Предварительные требования:
- Установленный JDK (рекомендуется **17**)
- Установленный **Gradle**
- **PostgreSQL** и **MongoDB**
- **Kafka** (для работы событий)
- Git

### Шаги для запуска:

1. **Клонирование репозитория**:
```sh
   git clone https://github.com/DimTiabu/booking-hotels.git
```
2. **Настройка подключения к базам данных в файле [application.yml](src/main/resources/application.yml)**
```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/hotels_db
    username: your_user
    password: your_password

  data:
    mongodb:
      uri: mongodb://localhost:27017/hotels_db
```
3. **Настройка баз данных в PostgreSQL и MongoDB в файле [docker-compose.yml](/docker/docker-compose.yml).**
```yaml
services:
  postgres:
    environment:
      - POSTGRES_USER=your_user
      - POSTGRES_PASSWORD=your_password
      - POSTGRES_DB=hotels_db
  mongodb:
    environment:
      MONGO_INITDB_ROOT_USERNAME: root
      MONGO_INITDB_ROOT_PASSWORD: root
      MONGO_INITDB_DATABASE: booking_statistics
```
4. *Переход в директорию расположения файла docker-compose:*

```sh
    cd docker
```

5. *Запуск docker-compose:*

```sh
    docker-compose up -d
```
⏳ Проверка окончания запуска всех контейнеров: MongoDB, PostgreSQL, Kafka
и Zookeeper:

```sh
    docker ps
```
**Переходить к следующему пункту можно,
когда все контейнеры будут в статусе Up.**

6. **Сборка проекта:**

```sh
  ./gradlew build
```

7. **Запуск приложения:**

```sh
  ./gradlew bootRun
```
---
## API

После запуска приложение доступно по адресу: http://localhost:8080.

Так как пользовательский интерфейс отсутствует, 
рекомендуется пользоваться приложением Postman.

## API эндпоинты

### 1. **HotelController** — `/api/hotel`

| Метод  | Endpoint                           | Описание                                     | Авторизация     |
| ------ | ---------------------------------- | -------------------------------------------- | --------------- |
| GET    | `/api/hotel`                       | Получить все отели                           | `USER`, `ADMIN` |
| GET    | `/api/hotel/filter?name=Marriott`  | Получить отели по фильтру (по имени, городу) | `USER`, `ADMIN` |
| GET    | `/api/hotel/{id}`                  | Получить отель по ID                         | `USER`, `ADMIN` |
| POST   | `/api/hotel`                       | Создание нового отеля                        | `ADMIN`         |
| PUT    | `/api/hotel/{id}`                  | Обновление отеля                             | `ADMIN`         |
| DELETE | `/api/hotel/{id}`                  | Удаление отеля                               | `ADMIN`         |
| POST   | `/api/hotel/rate/{id}?newMark=4.8` | Обновление рейтинга отеля                    | `USER`, `ADMIN` |

**Пример запроса (POST /api/hotel):**

```json
{
  "name": "Hotel Astoria",
  "city": "Paris",
  "address": "Champs-Élysées",
  "rating": 4.5
}
```

---

### 2. **RoomController** — `/api/room`

| Метод  | Endpoint                            | Описание                            | Авторизация     |
| ------ | ----------------------------------- | ----------------------------------- | --------------- |
| GET    | `/api/room?priceTo=10000&hotelId=1` | Получить список номеров с фильтрами | `USER`, `ADMIN` |
| GET    | `/api/room/{id}`                    | Получить номер по ID                | `USER`, `ADMIN` |
| POST   | `/api/room`                         | Создание нового номера              | `ADMIN`         |
| PUT    | `/api/room/{id}`                    | Обновление номера                   | `ADMIN`         |
| DELETE | `/api/room/{id}`                    | Удаление номера                     | `ADMIN`         |

**Пример запроса (POST /api/room):**

```json
{
  "hotelId": 1,
  "number": "101",
  "type": "STANDARD",
  "price": 9500.0
}
```

---

### 3. **BookingController** — `/api/booking`

| Метод | Endpoint       | Описание                          | Авторизация     |
| ----- | -------------- | --------------------------------- | --------------- |
| GET   | `/api/booking` | Получить список всех бронирований | `ADMIN`         |
| POST  | `/api/booking` | Создать бронирование              | `USER`, `ADMIN` |

**Пример запроса (POST /api/booking):**

```json
{
  "roomId": 5,
  "checkInDate": "2025-07-01",
  "checkOutDate": "2025-07-05"
}
```

---

### 4. **UserController** — `/api/user`

| Метод  | Endpoint         | Описание                       | Авторизация     |
| ------ | ---------------- | ------------------------------ | --------------- |
| GET    | `/api/user`      | Получить всех пользователей    | `USER`, `ADMIN` |
| GET    | `/api/user/{id}` | Получить пользователя по ID    | `USER`, `ADMIN` |
| POST   | `/api/user`      | Регистрация пользователя       | —               |
| PUT    | `/api/user/{id}` | Обновление данных пользователя | `USER`, `ADMIN` |
| DELETE | `/api/user/{id}` | Удаление пользователя          | `USER`, `ADMIN` |

**Пример запроса (POST /api/user):**

```json
{
  "username": "newuser",
  "password": "securePass",
  "email": "newuser@example.com"
}
```

---

### 5. **StatisticsController** — `/api/statistics`

| Метод | Endpoint                 | Описание                                      | Авторизация |
| ----- | ------------------------ | --------------------------------------------- | ----------- |
| GET   | `/api/statistics/export` | Экспорт статистики бронирований в формате CSV | `ADMIN`     |

**Ответ:** Файл `statistics.csv` будет загружен автоматически.

---
