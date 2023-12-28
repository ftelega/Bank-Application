# Spring Bank Application  
This repository contains the source code for a Spring Bank Application. The application provides RESTful endpoints for user management and transactions.  

# Controllers  
User Controller  
Base Path: /api/v1/user  
Endpoints: 

Create User  
Method: POST  
Path: /create  
Description: Creates a new user.  
Request Body: UserDTO  

Login  
Method: GET  
Path: /login  
Description: Initiates user login. 

Deposit  
Method: POST  
Path: /deposit  
Description: Deposits an amount to the user's account.  
Request Parameter: amount (BigDecimal)  

# Transaction Controller
Base Path: /api/v1/transaction  
Endpoints:  

Transfer  
Method: POST  
Path: /transfer  
Description: Initiates a transfer to another user.  
Request Parameters: receiver (Long), amount (BigDecimal)  

# DTOs
UserDTO:  
String username,  
String email,  
String password,  
AddressDTO address  

AddressDTO  
String city,  
String street,  
String streetNumber  

# Docker Deployment
To run the application using Docker Compose, follow these steps:  

Ensure Docker is installed on your machine.  

1. Clone this repository: git clone <repository_url>
2. Navigate to the project directory: cd spring-bank-application
3. Build the JAR file using Maven Wrapper: ./mvnw clean package
4. Start the application using Docker Compose: docker-compose up

# Access the application using the specified endpoints:

User Controller: http://localhost:8080/api/v1/user  
Transaction Controller: http://localhost:8080/api/v1/transaction  