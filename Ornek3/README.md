# Car Rental System — Spring Boot

A backend vehicle rental system developed using Java and Spring Boot.

## Technologies

* Java 21
* Spring Boot
* Spring Data JPA
* Hibernate
* Microsoft SQL Server
* Maven
* Mockito
* JUnit
* MockMvc

## Project Overview

This project implements a vehicle rental backend with functionality for managing customers, vehicles, daily rental prices, and rental records.

The system includes business rules and validation for vehicle rentals, customer management, vehicle availability, driver's license compatibility, rental pricing, and rental completion.

## Main Features

* Customer management
* Vehicle management
* Vehicle rental management
* Daily rental price management
* Rental start and completion
* Business rule validation
* Exception handling
* DTO and Mapper architecture
* CSV import/export
* Automated testing

## Requirements Analysis

The project includes a requirements analysis and traceability documentation process covering the main vehicle rental operations.

The documented process includes:

* User Stories
* Acceptance Criteria
* Given/When/Then Scenarios
* Use Case Specifications
* Requirements Traceability

The documented use cases include:

* Start Rental
* End Rental
* Add Customer
* Add Car

Detailed documentation is available in the [`docs/requirements-analysis/`](./docs/requirements-analysis/) directory.

The requirements traceability documentation maps business requirements to their corresponding service methods and automated tests, and identifies requirements that are fully, partially, or not currently covered by tests.

## Project Structure

```text
src/
├── main/
│   └── java/
│       └── com/numan/Ornek3/
└── test/

docs/
└── requirements-analysis/
    ├── user-stories.md
    ├── acceptance-criteria.md
    ├── given-when-then.md
    ├── use-cases.md
    └── requirements-traceability.md
```
