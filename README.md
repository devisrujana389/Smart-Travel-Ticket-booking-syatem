# Smart Travel Ticket Booking System

## 📌 Project Overview

Smart Travel Ticket Booking System is a Java-based backend application developed using Spring Boot and Microservices Architecture. It is designed to manage different travel-related operations such as bus, train, hotel, customer, and ticket booking services.

The application uses independent microservices that communicate with each other to provide a scalable and maintainable backend system.

## 🛠️ Technologies Used

- Java
- Spring Boot
- Spring Boot Microservices
- Spring Cloud Netflix Eureka
- Spring Cloud OpenFeign
- Spring Cloud API Gateway
- Spring Security
- JWT Authentication
- BCrypt Password Encryption
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- Postman
- Spring Tool Suite (STS)

## 🏗️ Project Architecture

The project follows a Microservices Architecture.

```text
                  Client
                    |
                    v
               API Gateway
                    |
          +---------+---------+
          |         |         |
          v         v         v
      Customer    Bus       Train
      Service   Service    Service
          |         |         |
          +---------+---------+
                    |
                    v
               Hotel Service
                    |
                    v
              Booking Service

             Eureka Server
          (Service Discovery)
```

## 📂 Microservices

| Microservice | Description |
|---|---|
| Customer Service | Manages customer details and authentication |
| Bus Service | Handles bus-related operations |
| Train Service | Handles train-related operations |
| Hotel Service | Manages hotel-related operations |
| Booking Service | Handles ticket booking operations |
| API Gateway | Provides a centralized entry point for API requests |
| Eureka Server | Manages service registration and discovery |

## ✨ Key Features

- Microservices-based application architecture.
- RESTful API development using Spring Boot.
- CRUD operations for different services.
- Service discovery using Netflix Eureka.
- Inter-service communication using OpenFeign.
- Centralized routing using API Gateway.
- Customer authentication using JWT.
- Password encryption using BCrypt.
- Database integration using MySQL.
- API testing using Postman.

## ⚙️ Installation and Setup

### Prerequisites

- Java JDK
- Spring Tool Suite (STS) / Eclipse
- MySQL Server
- Maven
- Postman

### Steps to Run the Project

**1. Clone the repository**

```bash
git clone https://github.com/devisrujana389/Smart-Travel-Ticket-booking-syatem.git
```

**2. Navigate to the project directory**

```bash
cd Smart-Travel-Ticket-booking-syatem
```

**3. Import the projects into Spring Tool Suite**

- Open Spring Tool Suite.
- Select File → Import.
- Choose Existing Maven Projects.
- Select the project folders.
- Import the services.

**4. Configure MySQL**

- Start your MySQL server.
- Create the required databases.
- Configure database connection details in the local application properties of each service.

**5. Run the services**

Start the Eureka Server first, followed by the individual microservices and API Gateway.

**6. Test the APIs**

Use Postman to test the REST API endpoints and verify the application functionality.

## 🔐 Security

The Customer Service implements authentication and security using:

- Spring Security
- JSON Web Token (JWT)
- BCrypt Password Encryption

## 🧪 API Testing

Postman is used to test REST APIs, including:

- Create operations
- Read operations
- Update operations
- Delete operations
- Customer authentication

## 🎯 Project Objectives

- To develop a travel booking application using Java and Spring Boot.
- To understand and implement Microservices Architecture.
- To implement communication between independent microservices.
- To integrate MySQL using Spring Data JPA.
- To implement secure customer authentication.
- To develop scalable and maintainable backend services.

## 🚀 Future Enhancements

- Payment Gateway Integration.
- Cloud Deployment.
- API Documentation using Swagger.
- Centralized Logging and Monitoring.
- Improved Booking Management.

## 👩‍💻 Developer

**Devisrujana**

Java Backend Developer | Spring Boot | Microservices

---

⭐ If you find this project interesting, feel free to explore the repository!
