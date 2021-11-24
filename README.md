# Commerce Services - Technical Interview

## Problem:

We are an online book store. We receive book orders from customers and process them.

### Features

- **Create a new Order**:
  - The application receives orders in a JSON format through an HTTP API endpoint (POST).
  - Orders contain a list of books and the quantity.
  - Before registering the order, the system should check if there's enough stock to fulfill the order.
  - This JSON file contains stock availability ([stock.json](stock.json))
  - If one of the books in the order does not have enough stock we will reject the entire order.
  - After stock validation, the order is marked as a success, and it would return a Unique Order Identifier to the caller of the HTTP API endpoint.
  - If the order was processed we need to update available stock, taking into consideration:
  - Updating stock should not be a blocker for replying to the customer.
  - If the process of updating stock fails, should not cause an error in order processing.

- **Retrieve Orders**:
  - The application has an endpoint to extract a list of existing orders.
  - The endpoint has a request parameter to indicate the format of requested data. It can be JSON or CSV.

## Required:

- Resolution needs to be fully in English
- We expect you to create a SpringBoot project [this template](https://start.spring.io/#!type=maven-project&language=java&platformVersion=2.5.2.RELEASE&packaging=jar&jvmVersion=11&groupId=com.adobe&artifactId=bookstore&name=bookstore&description=Book%20Store&packageName=com.adobe.bookstore&dependencies=web,data-jpa,h2), create a public repository with a solution. Once the code is complete, reply to your hiring person of contact.
- We expect you to implement tests for the requested functionalities. You decide the scope.

## Solution

### Prerequisites

- Java 11
- Gradle 7.2
- Spring Boot 2.5.6

### Approach

- Hexagonal architecture with unit testing and integration testing at the web layer

### How to execute the application

- Go to the application folder and execute ```gradle bootRun```

### How to access to the H2 database

- http://localhost:8080/h2-console

![image](https://user-images.githubusercontent.com/79670932/143311734-de992639-4d3c-4038-bc3a-8a2b719398d5.png)

- Configuration

![image](https://user-images.githubusercontent.com/79670932/143311830-6eee2529-4bc7-4110-94e8-5e60d07985a1.png)

### Endpoints

- Create a new Order (POST) -> http://localhost:8080/orders
- Retrieve Orders in JSON format (GET) -> http://localhost:8080/orders?format=JSON
- Retrieve Orders in CSV format (GET) -> http://localhost:8080/orders?format=CSV

Example of create

![image](https://user-images.githubusercontent.com/79670932/143312533-72c25788-83ae-4e89-8a4b-77d19fceee31.png)

Example of retrieve

![image](https://user-images.githubusercontent.com/79670932/143312594-99bf93b2-c5db-446f-ba1d-c3b54aee17a3.png)
