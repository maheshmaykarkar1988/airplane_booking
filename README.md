# airplane_booking
Online airplane_booking

# Swagger & POSTMAN Collection
**Booking Application.postman_collection** (attached)

**http://localhost:8080/service/swagger-ui.html#/**

# API Security
API's are secured. Use **user** as username and password with role **USER**

# Technology Stack
  - Java 1.7/8
  - Maven
  - Spring Boot
  - Spring JPA
  - REST
  - GIT
  - MySQL

# How to run the application locally 

- **Pre-requisites to run application**
   - Java 
   - Maven 
   - GIT
   - MySQL

- **Steps to Build and run locally**
   - Create a directory with whatever name you want
   - Clone the project using git command **git clone https://github.com/maheshmaykarkar1988/airplane_booking.git**
   - Intall MySQL on your machine if its not there.
   - Create database
   - Update information like database name, username and password in **application.properties** file(Currently database name is set to booking_management with username is root and there is no password)
   - **mvn -Plocal clean install** Run this command through command prompt. It will executes the clean build life cycle and the install build phase in the default build life cycle
   - Go to target folder => cd target  and Run following command to start the server on port 8080=> java -jar booking-0.0.1-SNAPSHOT.jar
        - OR
   - Go to CheckoutApplication.java file and run it. It will initilise all the necessary things required to run the project.
   - Run queries(mock_data_setup.txt) attached to create mock data in order to test API's
   - Use POSTMAN collection OR Swagger to invoke API's.
