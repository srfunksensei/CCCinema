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

1. Java
2. Maven

## How to run application


## How to run tests

The tests have been split into two categories:
- unit tests (fast, tests only a component mocks every dependency)
- integration tests (slow, tests all components in integrations)

This approach:
- allows you to keep all tests in the same source directory `src/test/java`
- is based on JUnit categories(4)/tags(5)

In order to run test separately you can use the following command:

We can also exclude some tests which can be done with: 

    mvn test -DexcludedGroups="integration-test"


## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
