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

- We expect you to create a SpringBoot project [this template](https://start.spring.io/#!type=maven-project&language=java&platformVersion=2.5.2.RELEASE&packaging=jar&jvmVersion=11&groupId=com.adobe&artifactId=bookstore&name=bookstore&description=Book%20Store&packageName=com.adobe.bookstore&dependencies=web,data-jpa,h2), create a public repository with a solution. Once the code is complete, reply to your hiring person of contact.
- We expect you to implement tests for the requested functionalities. You decide the scope.
