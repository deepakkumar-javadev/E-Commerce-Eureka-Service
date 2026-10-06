# E-Commerce Eureka Service

## 📌 Overview

E-Commerce Eureka Service is a **service discovery server** used in the e-commerce microservices application.

It provides a central registry where all microservices can **register themselves** and discover other services by their service name instead of using hardcoded hostnames and ports.

---

## 🚀 Features

* Service registration and discovery
* Centralized microservices registry
* Dynamic service lookup
* Supports load-balanced communication
* Integration with Spring Cloud Eureka
* Eureka Dashboard for monitoring registered services
* Helps microservices communicate using service names

---

## 🛠️ Technologies Used

* Java 17
* Spring Boot
* Spring Cloud Netflix Eureka Server
* Maven

---

## 🏗️ Architecture

```text
                         ┌─────────────────────────┐
                         │     Eureka Server       │
                         │        Port: 8761       │
                         └────────────┬────────────┘
                                      │
              ┌───────────────────────┼────────────────────────┐
              │                       │                        │
              ▼                       ▼                        ▼
      ┌───────────────┐       ┌───────────────┐       ┌─────────────────┐
      │ User Service  │       │Product Service│       │Inventory Service│
      │     8082      │       │     8083      │       │      8084       │
      └───────────────┘       └───────────────┘       └─────────────────┘
              │                       │                        │
              └───────────────────────┼────────────────────────┘
                                      │
              ┌───────────────────────┼────────────────────────┐
              │                       │                        │
              ▼                       ▼                        ▼
      ┌───────────────┐       ┌───────────────┐       ┌─────────────────┐
      │ Cart Service  │       │ Order Service  │       │ Payment Service │
      │     8085      │       │     8086       │       │      8087       │
      └───────────────┘       └───────────────┘       └─────────────────┘
                                      │
                                      ▼
                              ┌────────────────────┐
                              │Notification Service│
                              │        8088        │
                              └────────────────────┘
```

### Registered Services

| Service              | Port |
| -------------------- | ---: |
| User Service         | 8082 |
| Product Service      | 8083 |
| Inventory Service    | 8084 |
| Cart Service         | 8085 |
| Order Service        | 8086 |
| Payment Service      | 8087 |
| Notification Service | 8088 |

**Eureka Server:** Port `8761`

---

## 🔄 How It Works

### 1. Service Registration

When a microservice starts, it registers itself with the Eureka Server.

```text
Product Service
      │
      │ Register
      ▼
Eureka Server
   Port 8761
```

The service provides its application name and instance information to Eureka.

---

### 2. Service Discovery

When one microservice needs to communicate with another service, it can discover the target service using its registered service name.

```text
Order Service
      │
      │ Find ECOM-INVENTORY-SERVICE
      ▼
Eureka Server
      │
      │ Returns available instance
      ▼
Inventory Service
```

This avoids hardcoding service hostnames and ports.

Instead of directly depending on:

```text
http://localhost:8084
```

the application can use the registered service name:

```text
ECOM-INVENTORY-SERVICE
```

---

## 📊 Eureka Dashboard

After starting the Eureka Server, the dashboard can be accessed at:

```text
http://localhost:8761
```

The dashboard displays the microservices currently registered with Eureka.

Example:

```text
ECOM-USER-SERVICE
ECOM-PRODUCT-SERVICE
ECOM-INVENTORY-SERVICE
ECOM-CART-SERVICE
ECOM-ORDER-SERVICE
ECOM-PAYMENT-SERVICE
ECOM-NOTIFICATION-SERVICE
```

---

## ⚙️ Configuration

Example `application.yml`:

```yaml
spring:
  application:
    name: ECOM-EUREKA-SERVICE

server:
  port: 8761

eureka:
  client:
    register-with-eureka: false
    fetch-registry: false
```

### Configuration Explanation

```yaml
register-with-eureka: false
```

The Eureka Server does not need to register itself with another Eureka Server.

```yaml
fetch-registry: false
```

The Eureka Server does not need to fetch the registry from another Eureka Server.

---

## 🔗 Service Discovery in the Project

The Eureka Server acts as the **central service registry** for the e-commerce microservices.

```text
                         Eureka Server
                            :8761
                               │
          ┌────────────────────┼────────────────────┐
          │                    │                    │
          ▼                    ▼                    ▼
     User Service        Product Service      Inventory Service
        :8082                 :8083                 :8084
          │                    │                    │
          └────────────────────┼────────────────────┘
                               │
          ┌────────────────────┼────────────────────┐
          │                    │                    │
          ▼                    ▼                    ▼
     Cart Service         Order Service        Payment Service
        :8085                 :8086                 :8087
                               │
                               ▼
                      Notification Service
                             :8088
```

---

## 🎯 Role in the E-Commerce Application

Eureka provides **service registration and discovery** for the complete microservices architecture.

For example, when the Order Service needs to communicate with the Inventory Service:

```text
Order Service
      │
      │ Request Inventory Service
      ▼
Eureka Server
      │
      │ Discover
      ▼
ECOM-INVENTORY-SERVICE
      │
      ▼
Inventory Service
```

This allows services to communicate using logical service names instead of hardcoded IP addresses and ports.

---

## 🔌 Integration with OpenFeign

In this project, microservices can use **OpenFeign** for synchronous communication.

For example:

```java
@FeignClient(name = "ECOM-INVENTORY-SERVICE")
public interface InventoryClient {

}
```

Here:

```text
ECOM-INVENTORY-SERVICE
```

is the service name registered with Eureka.

The application uses Eureka to discover the corresponding Inventory Service instance.

---

## 📁 Project Structure

```text
E-Commerce-Eureka-Service
│
├── src
│   ├── main
│   │   ├── java
│   │   │   └── ...
│   │   │
│   │   └── resources
│   │       └── application.yml
│   │
├── pom.xml
└── README.md
```

---

## ▶️ Running the Application

### 1. Clone the Repository

```bash
git clone <repository-url>
```

### 2. Navigate to the Project

```bash
cd E-Commerce-Eureka-Service
```

### 3. Run the Application

Using Maven:

```bash
mvn spring-boot:run
```

Or run the main Spring Boot application class from your IDE.

---

## 🌐 Eureka Server URL

Once the application is running:

```text
http://localhost:8761
```

Open this URL in a browser to view the Eureka Dashboard.

---

## 🔐 Security

The Eureka Server in this project is used as an internal infrastructure component for service discovery.

It does not require database credentials or payment credentials for the basic Eureka Server configuration.

---

## 🔮 Future Enhancements

* Eureka Server high availability
* Multiple Eureka Server instances
* Production-ready service discovery configuration
* Improved health monitoring
* Service failure and recovery monitoring
* Cloud-based deployment

---

## 📌 Tech Stack

**Java | Spring Boot | Spring Cloud Netflix Eureka | Maven**

---

## 👨‍💻 Project Role

Eureka Service is a core infrastructure component of the **E-Commerce Microservices Application**, providing centralized service registration and discovery for backend microservices.
