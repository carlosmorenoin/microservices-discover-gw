# Microservices with spring boot and Netflix services
This project shows an example of a microservice project developed with spring boot with the following characteristics:
* Maven multimodule project. Each submodule represent a microservice.
* Netflix Eureka as discovery service. 
* Netflix Zuul as gateway.   
* Deployed on docker containers with docker compose.
* Mongo DB.


# Run the example
To run the example first build the project using:

`mvn clean install`

Then start the microservices containers with

`docker-compose up -d`

# Test

To test the application go to http://localhost:8080/users/ping
