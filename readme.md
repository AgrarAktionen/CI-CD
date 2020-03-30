# JPA/CDI Command Line Application Example

This example shows how to use Context and Dependency Injection in a JavaFX-Application and in Quarkus.

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

Running
===

You must start the application with the Main class at.ac.htl.util.ApplicationLauncher to get dependency Injection working
