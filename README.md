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
    url: jdbc:postgresql://localhost:5432/booking_db
    username: your_user
    password: your_password

  data:
    mongodb:
      uri: mongodb://localhost:27017/booking_db
```
3. **Создание базы данных в PostgreSQL и MongoDB (если она ещё не создана).**


4. **Сборка проекта:**

```sh
  ./gradlew build
```

5. **Запуск приложения:**

```sh
  ./gradlew bootRun
```
---
## API и пользовательский интерфейс

После запуска приложение доступно по адресу: http://localhost:8080

### Основной функционал:
* Просмотр списка отелей и их информации
* Бронирование с выбором дат 
* Просмотр списка бронирований 
* Отправка и получение Kafka-сообщений 
* Хранение дополнительной информации в MongoDB

### Примеры API:

* GET /api/hotels — список отелей
* GET /api/hotels/{id} — подробности по отелю
* POST /api/hotels/{id}/book — создание бронирования
* GET /api/users/{id}/bookings — бронирования пользователя