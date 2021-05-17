# Quarkus Application Example

This Jakarta Enterprise Edition example shows how to deploy
an application to to the Kubernetes Bare Metal [LeoCloud](https://cloud.htl-leonding.ac.at) by
continuous delivery.

It is made up of the following components:
- an application server implemented with [quarkus](https://quarkus.io)
- a mysql Database 
- a REST Browser Client implemented in Javascript using webpack. 

The Application is currently [deployed on the LeoCloud](https://student.cloud.htl-leonding.ac.at/c.aberger/)

When the source code is pushed the docker images are built automatically with 
github actions.

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
This is the application server
See the [appsrv](./appsrv/README.md) subfolder for how to use REST/JPA/CDI in Quarkus

www
===
see the [www](./www/readme.md) subfolder for the javascript client

JavaFX
===
See the application subfolder. This application is out of date, contributions welcome!

Cucumber Feature Tests and Code Coverage
===
see appsrc/src/test/resources for a feature test. To see the coverage report run the following:
~~~bash
cd ./appsrv
mvn clean compile test jacoco:report
~~~

Then open appsrv/target/site/jacoco/index.html.
As you see only Person has been tested. Contributions are welcome.

Deploy into the Cloud
===
You have to get your credentials from the cloud, adjust your namespace
and baseUrl, then you can deploy the application with:

```bash
kubectl deploy -f k8s/app.yaml
```

Example
===
https://student.cloud.htl-leonding.ac.at/c.aberger/
