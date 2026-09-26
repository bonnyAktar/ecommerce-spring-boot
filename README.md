# Ecommerce Spring Boot Backend

A RESTful Ecommerce Backend API developed using Java and Spring Boot. This project provides APIs for managing products, categories, users, carts, orders, and order items.

## Features

* User registration and login
* JWT-based authentication
* Password encryption using BCrypt
* Product CRUD operations
* Category CRUD operations
* Cart and Cart Item management
* Order and Order Item management
* Order total price calculation
* Input validation
* Exception/Error response handling
* MySQL database integration
* REST API testing with Postman

## Technologies Used

* Java
* Spring Boot
* Spring Data JPA
* Spring Security
* JWT
* MySQL
* Maven
* Postman
* IntelliJ IDEA

## Main Modules

* User Management
* Product Management
* Category Management
* Cart Management
* Order Management
* Order Item Management
* Authentication & Authorization

## API Endpoints

### Users

* `POST /users` - Create a new user
* `POST /users/login` - User login
* `GET /users` - Get all users
* `GET /users/{id}` - Get user by ID
* `PUT /users/{id}` - Update user
* `DELETE /users/{id}` - Delete user

### Products

* `GET /products` - Get products
* `POST /products` - Create product
* `GET /products/{id}` - Get product by ID
* `PUT /products/{id}` - Update product
* `DELETE /products/{id}` - Delete product

### Categories

* `GET /categories` - Get all categories
* `POST /categories` - Create category
* `GET /categories/{id}` - Get category by ID
* `PUT /categories/{id}` - Update category
* `DELETE /categories/{id}` - Delete category

### Cart Items

* `GET /cart-items` - Get all cart items
* `POST /cart-items` - Add item to cart
* `GET /cart-items/{id}` - Get cart item by ID
* `PUT /cart-items/{id}` - Update cart item
* `DELETE /cart-items/{id}` - Delete cart item

### Orders

* `GET /orders` - Get all orders
* `POST /orders` - Create order
* `GET /orders/{id}` - Get order by ID
* `PUT /orders/{id}` - Update order status
* `DELETE /orders/{id}` - Delete order

### Order Items

* `GET /order-items` - Get all order items
* `POST /order-items` - Create order item
* `GET /order-items/{id}` - Get order item by ID
* `PUT /order-items/{id}` - Update order item
* `DELETE /order-items/{id}` - Delete order item

## Database

The application uses **MySQL** as the database and **Spring Data JPA** for database operations.

## Authentication

The application uses **JWT (JSON Web Token)** for authentication.

* User registration and login are publicly accessible.
* Protected APIs require a valid JWT token.
* Passwords are stored using BCrypt encryption.

## How to Run

1. Clone the repository.
2. Create a MySQL database.
3. Configure the database username and password in `application.properties`.
4. Open the project in IntelliJ IDEA.
5. Run the Spring Boot application.
6. Use Postman to test the APIs.

## Project Structure

```text
src
 └── main
     └── java
         └── com.example.ecommerce
             ├── controller
             ├── dto
             ├── model
             ├── repository
             ├── security
             └── service
```

## Author
Bonny Aktar

