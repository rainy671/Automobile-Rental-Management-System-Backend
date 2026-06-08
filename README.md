# Automobile Rental Management System — Backend

A full-stack web application built with **Java** and **Spring Boot** that manages vehicle rentals, bookings, and user roles for an automobile rental business.

---

## Features

- **Vehicle Management** — CRUD operations for vehicle listings (add, update, delete, view)
- **Rental Management** — Booking creation, approval workflows, and rental tracking
- **Role-Based Access Control** — Separate modules for Admin and Customer with different permissions
- **Booking Approval Workflow** — Admin can approve or reject rental requests
- **REST API Integration** — Angular frontend communicates with backend via RESTful services

---

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Java |
| Framework | Spring Boot, Spring MVC |
| ORM | Spring Data JPA, Hibernate |
| Database | Oracle |
| API | RESTful Web Services |
| Build Tool | Maven |
| Version Control | Git |

---

## Project Structure

```
automobile-rental/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/automobilerental/
│   │   │       ├── controller/
│   │   │       ├── service/
│   │   │       ├── repository/
│   │   │       └── model/
│   │   └── resources/
│   │       └── application.properties
└── pom.xml
```

---

## Getting Started

### Prerequisites

- Java 11 or higher
- Maven 3.6+
- Oracle Database

### Setup

1. Clone the repository
   ```bash
   git clone https://github.com/rainy671/Automobile-Rental-Management-System-Backend.git
   cd Automobile-Rental-Management-System-Backend
   ```

2. Configure the database in `src/main/resources/application.properties`
   ```properties
   spring.datasource.url=jdbc:oracle:thin:@localhost:1521:xe
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   spring.jpa.hibernate.ddl-auto=update
   ```

3. Build and run
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

4. API will be available at `http://localhost:8080`

---

## API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/vehicles` | Get all vehicles |
| POST | `/api/vehicles` | Add a new vehicle |
| PUT | `/api/vehicles/{id}` | Update vehicle details |
| DELETE | `/api/vehicles/{id}` | Remove a vehicle |
| GET | `/api/bookings` | Get all bookings |
| POST | `/api/bookings` | Create a new booking |
| PUT | `/api/bookings/{id}/approve` | Approve a booking (Admin) |
| PUT | `/api/bookings/{id}/reject` | Reject a booking (Admin) |

---

## Author

**Rainy Tripathi**  
[LinkedIn](https://www.linkedin.com/in/rainy-tripathi) | [GitHub](https://github.com/rainy671)
