# Requirements Traceability

This document maps the business requirements of the vehicle rental system to the corresponding service methods and automated tests.

## 1. Start Rental

| Requirement                                                               | Code                          | Test                                    | Status            |
| ------------------------------------------------------------------------- | ----------------------------- | --------------------------------------- | ----------------- |
| The car and customer must exist in the system.                            | `RentalService.startRental()` | Not directly tested                     | Not Covered       |
| The car must have an active daily rental price.                           | `RentalService.startRental()` | `RentalServiceTests.testStartRental2()` | Covered           |
| The car must be available for rental.                                     | `RentalService.startRental()` | Not directly tested                     | Not Covered       |
| The customer's driver's license must be compatible with the vehicle type. | `RentalService.startRental()` | `RentalServiceTests.testStartRental3()` | Covered           |
| A rental record must be created when the rental starts successfully.      | `RentalService.startRental()` | `RentalServiceTests.testStartRental()`  | Covered           |
| The starting kilometer must be recorded.                                  | `RentalService.startRental()` | `RentalServiceTests.testStartRental()`  | Covered           |
| The car must become unavailable when the rental starts.                   | `RentalService.startRental()` | `RentalServiceTests.testStartRental()`  | Covered           |
| The rental start date must be recorded.                                   | `RentalService.startRental()` | `RentalServiceTests.testStartRental()`  | Partially Covered |

## 2. End Rental

| Requirement                                                         | Code                        | Test                                  | Status            |
| ------------------------------------------------------------------- | --------------------------- | ------------------------------------- | ----------------- |
| The rental record must exist in the system.                         | `RentalService.endRental()` | `RentalServiceTests.testEndRental3()` | Covered           |
| The ending kilometer must not be lower than the starting kilometer. | `RentalService.endRental()` | `RentalServiceTests.testEndRental2()` | Covered           |
| The ending kilometer must be recorded in the rental record.         | `RentalService.endRental()` | `RentalServiceTests.testEndRental()`  | Partially Covered |
| The vehicle's kilometer must be updated.                            | `RentalService.endRental()` | `RentalServiceTests.testEndRental()`  | Partially Covered |
| The vehicle must become available when the rental is completed.     | `RentalService.endRental()` | `RentalServiceTests.testEndRental()`  | Covered           |
| The rental end date must be recorded.                               | `RentalService.endRental()` | `RentalServiceTests.testEndRental()`  | Partially Covered |
| The total rental price must be calculated.                          | `RentalService.endRental()` | Not directly tested                   | Not Covered       |

## 3. Add Customer

| Requirement                                              | Code                            | Test                                                            | Status            |
| -------------------------------------------------------- | ------------------------------- | --------------------------------------------------------------- | ----------------- |
| The customer must be at least 18 years old.              | Customer validation             | `CustomerServiceTests.testAddCustomer()`                        | Partially Covered |
| The national card number must contain exactly 11 digits. | Customer validation             | `CustomerServiceTests.testAddCustomer()`                        | Partially Covered |
| The national card number must be unique.                 | `CustomerService.addCustomer()` | `CustomerServiceTests.testAddCustomerWithNationalCardNoError()` | Covered           |
| A valid customer must be created successfully.           | `CustomerService.addCustomer()` | `CustomerServiceTests.testAddCustomer()`                        | Covered           |

> Note: Age and national card number format validations are performed at the DTO validation level. Therefore, the current service tests do not directly verify invalid age or invalid card-number format scenarios.

## 4. Add Car

| Requirement                                   | Code                  | Test                           | Status            |
| --------------------------------------------- | --------------------- | ------------------------------ | ----------------- |
| The vehicle model year must be 1990 or later. | `CarService.addCar()` | `CarServiceTests.testAddCar()` | Partially Covered |
| A valid vehicle must be created successfully. | `CarService.addCar()` | `CarServiceTests.testAddCar()` | Covered           |

## 5. Additional Service Tests

In addition to the main use cases, the following service behaviors are also verified by automated tests.

| Functionality                                                         | Code                                          | Test                                                  | Status  |
| --------------------------------------------------------------------- | --------------------------------------------- | ----------------------------------------------------- | ------- |
| An error is returned when no rental records are found for a customer. | `RentalService.getRentalRecordByCustomerId()` | `RentalServiceTests.testGetRentalRecordByCustomerd()` | Covered |
| An error is returned when no rental records exist.                    | `RentalService.getAllRecords()`               | `RentalServiceTests.testGetAllRecords()`              | Covered |
| Vehicles are retrieved and mapped to DTOs.                            | `CarService.getAllCars()`                     | `CarServiceTests.testGetAllCars()`                    | Covered |

## 6. Status Definitions

* **Covered:** The requirement is directly verified by an automated test.
* **Partially Covered:** Some aspects of the requirement are verified, but not all behaviors are directly tested.
* **Not Covered:** The requirement is implemented in the code but is not directly verified by the current tests.
