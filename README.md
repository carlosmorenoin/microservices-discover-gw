# Microservices example with Spring Cloud and Netflix OSS 
This project shows an example of a microservice example developed with spring boot with the following characteristics:
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

# The example
This project includes a gateway service (Netflix Zuul) that listens to the port 8080 and redirect the requests with the prefix /v1/registry 
to the user registry microservice (micro-users). The micro-service module is a spring boot application that provides an exampel API with 
two endpoints:
* `/v1/registry/ping`
* `/v1/registry/users`

Check the class UserAPI to see the options.

Test the application with: 
* http://localhost:8080/v1/registry/ping: to see a ping message
* http://localhost:8080/v1/registry/users.json: to see the list of users
