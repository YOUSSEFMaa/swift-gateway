# Swift Gateway

Swift Gateway is a Spring Boot application that processes SWIFT payment messages. It uses IBM MQ for messaging and H2 as an in-memory database for testing purposes.

## Prerequisites

- Java 17
- Maven
- IBM MQ
- H2 Database

## Getting Started

### Clone the Repository

Build the Project:
mvn clean install

## Run the Application
using Docker : 
  -build image: docker build -t swift-gateway 
  -run the image : docker-compose up

## Access the H2 Database Console
      spring.h2.console.enabled=true
      spring.h2.console.path=/h2-console
      Open a web browser and navigate to http://localhost:8080/h2-console.  
      Use the following settings to log in:  
      JDBC URL: jdbc:h2:mem:testdb
      User Name: sa
      Password: password

      
## IBM MQ Configuration
Configure the IBM MQ connection in the application.properties file:

ibm.mq.queueManager=QM1
ibm.mq.host=localhost
ibm.mq.port=1414
ibm.mq.channel=DEV.APP.SVRCONN
ibm.mq.queueName=PAYMENT.MESSAGES.QUEUE

## Running Tests
To run the unit tests, use the following command:

mvn test
