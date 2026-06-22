# food_ordering_system

## 1. What is Spring Boot?
> *Spring Boot is an extension of the traditional Spring framework designed to get an application up and running as quickly as possible. Instead of making you configure dozens of complex XML or Java configuration files from scratch, it handles the heavy lifting behind the scenes using sensible defaults and automatic configurations.*
## 2. What is Maven?
> *Maven is a build automation and project management tool for Java projects. It acts like a supervisor for your code, managing how the project is compiled, tested, packaged and delivered.*
## 3. What is the purpose of pom.xml?
> *The pom.xml (Project Object Model) file is the heart of any Maven project. Think of it as a manifest or a recipe sheet; it lists the project details, configuration settings and every single external library (dependency) your application needs to download and run.*
## 4. What is the purpose of application.properties?
> *This file handles your environment-specific configurations. It is where you define settings that shouldn't be hardcoded into your actual Java classes, such as what port your server should listen on, your database URLs, usernames and passwords.*
## 5. What does @SpringBootApplication do?
> *This is a shortcut annotation placed on your main application class. It combines three essential tasks into one: it tells Spring that this is a configuration source, enables auto-configuration based on the libraries you have installed, and triggers a component scan so Spring can automatically find and manage your components, controllers and services.*
## 6. Why do developers use dependency management tools such as Maven?
> *In modern software, projects rely on hundreds of external libraries. Trying to download these files manually, verify their security hashes and ensure they don't conflict with each other's versions would be an absolute nightmare. Tools like Maven automate this entire process, ensuring every developer on a team is working with the exact same files and versions.*
## 7. What is a REST API?
> *A REST API is an architectural approach that lets different applications talk to one another over the web. It relies on standard HTTP methods (like GET, POST, PUT and DELETE) to allow a frontend application or client to request or modify data securely on a backend server.*
## 8. What is JSON?
> *JSON (JavaScript Object Notation) is a text-based format used to structure and transmit data. It is lightweight, uses a simple key-value pair system, and is easily read by both humans and computers, making it the universal standard for sending data across the web.*
## 9. What is Dependency Injection
> *Dependency Injection is a design pattern focused on keeping code loose and modular. Instead of a class creating the objects it needs to do its job internally, those objects (dependencies) are supplied to it from the outside. In Spring Boot, the framework creates these components and passes them where they are needed, making the application much easier to test and maintain.*

# PURPOSE OF EACH PACKAGE

> ## Controller - >
> The entry point of the API. It handles incoming HTTP network requests, routes them to the right place and returns responses to the client.
>  ## Service -> 
> The brain of the application. This layer contains the core business logic, validation rules and handles data transformations.
>  ## Repository ->
> The data access layer. It talks directly to the MySQL database and handles operations like saving, updating or fetching records.
>  ## Entity ->
> Represents the database blueprint. Classes inside this package directly map to individual tables within your SQL database.
>  ## dto ->
> Data Transfer Objects. These are lightweight container classes used to move data across the network safely without exposing the raw database entities directly to the client.
>  ## config ->
> Holds your custom configuration setups, custom bean definitions and system-wide adjustment classes.
>  ## Exception ->
> Dedicated to error handling. It stores custom error templates to ensure that when something goes wrong, the API sends back clean, readable error logs.

## Endpoints

| Method | URL                | Body         |
|--------|--------------------|--------------|
| POST   | /api/category      | { "name" }   |
| GET    | /api/category      | -            |
| GET    | /api/category/{id} | -            |
| PUT    | /api/category/{id} | { "name" }   |
| DELETE | /api/category/{id} | -            |