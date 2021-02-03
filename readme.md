# JPA/CDI Quarkus Application Example

This example shows how to use Context and Dependency Injection in a [quarkus](https://quarkus.io) application server and also in JavaFX-Application.

Database
===

To start the database open a terminal in the docker subfolder and run the following: 
```bash
docker-compose up --build
```

To reset the project and clean the database run
```bash
make clean
```

appsrv
===
This is the application server
See the [appsrv](./appsrv/README.md) subfolder for how to use CDI in Quarkus

www
===
see the www Subfolder for the javascript client

JavaFX
===
See the appliation subfolder. This application is out of date, contributions welcome!

Cucumber Feature Tests and Code Coverage
===
see appsrc/src/test/resources for a feature test. To see the coverage report run the following:
~~~bash
cd ./appsrv
mvn clean compile test jacoco:report
~~~

Then open appsrv/target/site/jacoco/index.html.
As you see only Person has been tested. Contributions are welcome.