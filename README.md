
# README.md

This project demonstrates my learning journey with **Spring Boot**, **Spring Security**, **PostgreSQL**, **JPA**, **JWT**, **GraphQL**, and other related frameworks. Below is a summary of the key technologies and frameworks used.

---

## Technologies and Frameworks Covered

### 1. **Spring Boot**
Spring Boot is the foundation of this project. It simplifies the setup and configuration of a Spring-based application and integrates various libraries to reduce boilerplate code. The `spring-boot-starter-web` dependency enables me to create RESTful APIs, while `spring-boot-starter-data-jpa` helps me integrate JPA for database operations seamlessly.

### 2. **Spring Security**
Security is a crucial aspect of any application, and I integrated **Spring Security** for authentication and authorization purposes. With `spring-boot-starter-security`, I initially set up in-memory authentication and later implemented **DAO-based authentication** by using a custom `UserDetailsService` to load user data from the PostgreSQL database. This provides a robust and flexible authentication solution for both development and production environments.

- **JWT Authentication:** I added **JWT** (JSON Web Token) to secure endpoints. The security filter (`JwtFilter`) is used to intercept requests and validate the token, ensuring that only authenticated users can access protected resources.
- **SecurityConfig:** Custom configuration is used to manage access controls, permitting specific public endpoints (like login) and securing others with JWT-based authentication.

### 3. **PostgreSQL**
For database management, I used **PostgreSQL**, an open-source relational database management system, to store user data, roles, and related entities. The `org.postgresql:postgresql` dependency enables seamless integration between Spring Boot and PostgreSQL. JPA (Java Persistence API) manages the mapping of Java entities to database tables, which significantly simplifies database interaction.

### 4. **Spring Data JPA**
**Spring Data JPA** is a key part of the persistence layer in this project. With `spring-boot-starter-data-jpa`, I was able to integrate JPA for data persistence, using Hibernate as the underlying ORM framework. This allowed me to efficiently map Java objects to relational database tables and perform CRUD operations without writing boilerplate code.

### 5. **Spring Boot Validation**
For input validation, I leveraged **Spring Boot Validation** via the `spring-boot-starter-validation` dependency. This allowed me to use annotations like `@NotNull`, `@Size`, `@Min`, `@Max`, and `@Pattern` for validating user input, ensuring that only valid data is stored in the database.

### 6. **Maven Plugin for Spring Boot**
The `spring-boot-maven-plugin` is responsible for packaging and running the application. It simplifies the process of building an executable JAR file that can be run with a single command (`mvn spring-boot:run`). This plugin is essential for deploying the application locally or to a production environment.

### 7. **JWT Authentication**
I implemented **JWT Authentication** to protect the application from unauthorized access:

- **JwtFilter**: A filter that intercepts HTTP requests and checks for the presence and validity of a JWT token in the Authorization header.
- **JwtUtil**: A utility class for creating, validating, and parsing JWT tokens. The token contains user information and is sent back after successful login.
- **SecurityConfig**: The custom security configuration is responsible for defining which endpoints are public and which are secured. It integrates the JWT filter into the Spring Security filter chain.

### 8. **GraphQL**
In this project, I integrated **GraphQL** for flexible querying and mutation of data:

- **GraphQL Queries and Mutations:** I defined GraphQL queries and mutations to interact with data. Queries retrieve data, while mutations allow for creating, updating, or deleting entities.

- **Passing Objects as Arguments in Mutations:** Instead of passing individual arguments for a mutation, I leveraged an object-based approach. The `CityRequest` object encapsulates all necessary data (like `name`, `code`, and `countryId`), and is passed as a single argument to mutations. This approach makes the code cleaner and easier to extend.

Example Mutation:
```graphql
mutation {
  createCity(cityRequest: { name: "Zurich", code: "ZH", countryId: 1 }) {
    id
    name
    code
    countryId
    country
  }
}
```

### 9. **GraphQL Integration with Spring Boot**
- **GraphQLController**: I created a `GraphQLController` with methods to handle GraphQL queries and mutations. The methods use `@QueryMapping` and `@MutationMapping` annotations to define the available queries and mutations.

Example of a GraphQL mutation to create a city:
```java
@MutationMapping
public CityResponse createCity(@Argument("cityRequest") CityRequest cityRequest) {
    System.out.println("Received CityRequest: " + cityRequest);
    CityResponse res = cityService.saveCity(cityRequest);
    return res;
}
```

### 10. **Request and Response Handling with GraphQL**
- **CityRequest:** The input for the mutation is now encapsulated in a `CityRequest` object. This allows cleaner, more maintainable code by grouping related fields into one object, rather than passing each field separately.
- **CityResponse:** The `CityResponse` class represents the response returned from the mutation and is used to send back the data after a city is created.

---

## Summary

This project covers core concepts and techniques in Spring Boot, Spring Security, JPA, PostgreSQL, JWT Authentication, and GraphQL. These technologies are essential for building full-stack Java applications with secure authentication, database integration, and flexible query/mutation capabilities. By following these tutorials, I learned how to:

- Set up Spring Boot and Spring Security for authentication and authorization.
- Integrate PostgreSQL as a relational database.
- Use JPA and Spring Data JPA to interact with the database.
- Implement JWT-based authentication and authorization in Spring Boot.
- Create GraphQL queries and mutations, including passing complex objects as arguments.

The goal of this project was to demonstrate my understanding of these technologies and showcase my ability to apply them to real-world scenarios. You can explore the code to see how each concept was implemented.

---

This `README.md` file provides a concise summary of the technologies used in your project and outlines what you have learned and implemented. It can be shared with others to showcase your skills and understanding of these technologies.

---

