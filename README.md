# A Quarkus version of Vaadin CRM tutorial app

This example is a rewrite of the Vaadin CRM tutorial with Quarkus.

Quarkus 2.0+ requires Java 11.

## Running the Application

Import the project to the IDE of your choosing as a Maven project.

Run application using from CLI using `mvn`.

Open [http://localhost:8080/](http://localhost:8080/) in browser.

If you want to run your app locally in production mode, call `mvn package -Pproduction`.
and then
```
java -jar target/quarkus-app/quarkus-run.jar
```
