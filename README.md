# Food Order API

This is a RESTful API backend project for a food ordering system. The API allows users to create and manage food orders, view menus, and make payments.

## Technologies Used
      *  Spring Boot
      *  Java
      *  Maven
      *  Swagger
      *  Hibernate
      *  JPA
      *  MySQL
      *  IntelliJ

## Features
  *  Admin can perform CRUD operations for food
  *  Users can order food
  *  Validation for user inputs
  *  Getting Started

## To get started with the project, follow these steps:

  *  Clone the repository "https://github.com/ViswajithMuraleedharan/springbootMct/tree/master/foodOrderApi"
   * Import the project in IntelliJ as a Maven project
   * Configure the MySQL database credentials in application.properties file
   * Build and run the project
You can access the API documentation using Swagger at http://localhost:8080/swagger-ui.html.

## Endpoints
### Foods
* GET (/getAllfood): Retrieve all foods
* POST (/createfood): Create a new food
* PUT (/updatefood/{foodId}): Update a food by ID
* DELETE (/deletefood): Delete a food by ID
### orders
* GET (/getOrder): Retrieve all orders & Retrieve a specific order by ID
* POST (/createOrder): Create a new order
### users
* GET (/getUser):  Retrieve all user
* POST (/createUser): Create a user

Validation
User inputs are validated using Hibernate Validator for username and password.
If validation fails, appropriate error messages are returned in the response
