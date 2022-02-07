# AgrarAktionen

This Quarkus Application includes part from the AgrarAktionen Project.
  -We are getting data From an 

It is made up of the following components:
- a mysql Database 
- quarkus backend 
- a tensorflow pretrained imagerecognition model

About the project.
===
1. You are abled to send Images via a POST Request to the Database. 
2. The application server can analyse the image by running the picture identification algorithm.
3. After proceccing the image, a set of similar products is created and you can reach dem by calling the API rest endpoint.


Database for local development
===

To start the database for local development open a terminal in the docker subfolder and run the following: 
```bash
cd ./docker/mysql
docker-compose up --build
```

To reset the project and clean the database you can run
```bash
make clean
```

Building on the local machine
===
to build the application on the local machine run the following:
```bash
mvn -Dquarkus.profile=dev clean package
java -jar appsrv/target/application-server-0.5.0-runner.jar
```

MicroProfile OpenAPI
===
 You can explore the SwaggerUI API with using
 http://localhost:8080/q/swagger-ui/

 You can download the openapi specification file with  http://localhost:8080/q/openapi and use the [openapi code generator](https://openapi-generator.tech/) to automatically generate 
 REST clients in Java, Typescript, Javascript etc. etc.

appsrv
===
This is the application server, with business logics
