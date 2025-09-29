# Laboratorio de Programación (C1660) 
### [Escuela de Sistemas e Informática](#)
Unidad Académica Rio Turbio - Universidad Nacional de la Patagonia Austral

*Author: [Diana Cruz](https://github.com/daincz)*

**Description**: Getting started with a REST API microservice for basic user management.

**Technologies**: Spring Boot for Java Development, Maven for dependency management, and MySQL

**Dependencies**:

- MySQL Driver for Java
- Spring Data JPA
- Spring Web
- Validation JPA API
- Lombok

**Prerequisites**:
Create the database `demo` with a table called `usuario`:

> ```sql
> CREATE DATABASE demo;
>
>CREATE TABLE IF NOT EXISTS demo.usuario (
>  coduser BIGINT NOT NULL AUTO_INCREMENT,
>  username VARCHAR(45) NOT NULL,
>  email VARCHAR(255) NOT NULL,
>  PRIMARY KEY (coduser),
>  UNIQUE INDEX usename_UNIQUE (username ASC) VISIBLE,
>  UNIQUE INDEX email_UNIQUE (email ASC) VISIBLE)
>ENGINE = InnoDB
>AUTO_INCREMENT = 1
>DEFAULT CHARACTER SET = utf8mb4
>COLLATE = utf8mb4_0900_ai_ci;

Modify the properties in the file `src/main/java/application`.properties using the data from your test environment:
>spring.datasource.url=jdbc:mysql://localhost:3306/dbname
>spring.datasource.username=username
>spring.datasource.password=pass