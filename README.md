# CCCinema

The task is to build a simple web interface that allows a user to purchase cinema tickets for selected movies.

The following are the criteria for the task:
  * The intended cinema has many theatres
  * Each theatre has many seats, configured in a rectangular fashion, i.e. each row has equal number of seats
  * Each theatre has a number of movies that are shown every day

Your project should include the following:
  * Java should be used as the main server side programming language
  * An appropriate domain model (this model does not need to be persisted, but needs to be seeded from appropriate configuration files or a data fixture provider)
  * A web interface that provides the following:
    - Allows a user to view the upcoming movies
    - A way for the user to book a seat of their choice for a selected movie.
  * The system is built in a manner that it can be accessed from multiple devices (only web based access is required for the purpose of this project).
  * Data consistency should be guaranteed.
  * The use of Spring, Spring MVC will be considered an asset. 

## Prerequisites

1. Java 11 (or higher)
2. Maven

### Building and Running

```bash
mvn clean spring-boot:run
```

## Testing

The tests have been split into two categories:
- unit tests (fast, tests only a component mocks every dependency)
- integration tests (slow, tests all components in integrations)

This approach:
- allows you to keep all tests in the same source directory `src/test/java`
- is based on JUnit categories(v4)/tags(v5)

Implementation can be done in two ways:

##### without `maven-surefire-plugin` 

In order to run test separately you can use the following command:
```bash
mvn clean test -Dgroups="unit-test"
```

We can also exclude some tests which can be done with: 
```bash
mvn test -DexcludedGroups="integration-test"
```

##### with `maven-surefire-plugin`

Alternatively we can use `maven-surefire-plugin` and configure it to suit our needs. In this project it is configured to run all tests. In order to run them separately we can use the following commands:
```bash
mvn clean test -P unit-tests
```
or run them all with 
```bash
mvn clean verify
```

### Dockerizing app

```bash
mvn clean package
docker build -t cccinema:latest .
```

running docker image

```bash
docker run -it -p 8080:8080 cccinema
```

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
