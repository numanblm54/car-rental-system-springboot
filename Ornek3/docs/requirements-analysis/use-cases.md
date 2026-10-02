# Use Case Specifications

## UC-01 — Start Rental

**Actor:** System User

**Purpose:**
Start a rental process for a selected customer and vehicle.

### Preconditions

* The selected vehicle exists in the system.
* The selected customer exists in the system.
* The vehicle has an active daily rental price.
* The vehicle is available for rental.
* The customer's driver's license is compatible with the vehicle type.

### Main Flow

1. The System User sends a rental start request containing the vehicle ID and customer ID.
2. The system retrieves the selected vehicle.
3. The system retrieves the selected customer.
4. The system retrieves the active daily rental price for the vehicle.
5. The system checks whether the vehicle is available.
6. The system checks the compatibility between the customer's driver's license and the vehicle type.
7. The system creates a new rental record.
8. The system records the vehicle's current kilometer as the starting kilometer.
9. The system records the rental start date.
10. The system changes the vehicle status to unavailable.
11. The system saves the rental record.
12. The system returns the rental information.

### Alternative / Exception Flows

**A1 — Vehicle Not Found**

* The system cannot find the selected vehicle.
* The rental process is terminated.
* A resource-not-found error is returned.

**A2 — Customer Not Found**

* The system cannot find the selected customer.
* The rental process is terminated.
* A resource-not-found error is returned.

**A3 — Active Price Not Found**

* The vehicle does not have an active daily rental price.
* The rental process is terminated.
* An error is returned.

**A4 — Vehicle Already in Use**

* The vehicle is not available.
* The rental process is terminated.
* A business rule error is returned.

**A5 — License and Vehicle Incompatible**

* The customer's driver's license is not compatible with the vehicle.
* The rental process is terminated.
* A business rule error is returned.

### Postconditions

If the rental is successfully started:

* A rental record exists in the system.
* The rental record is associated with the selected vehicle, customer, and daily rental price.
* The vehicle's starting kilometer is recorded.
* The rental start date is recorded.
* The vehicle is unavailable for another rental.
* The rental information is returned to the System User.

## UC-02 — End Rental

**Actor:** System User

**Purpose:**
Complete an active rental process and calculate the total rental price.

### Preconditions

* The rental record exists in the system.
* The ending kilometer is not lower than the starting kilometer.

### Main Flow

1. The System User sends a rental completion request containing the rental ID and ending kilometer.
2. The system retrieves the rental record.
3. The system checks whether the ending kilometer is valid.
4. The system records the ending kilometer in the rental record.
5. The system updates the vehicle's kilometer with the ending kilometer.
6. The system changes the vehicle status to available.
7. The system records the rental end date.
8. The system calculates the rental duration.
9. The system calculates the total rental price using the daily rental price.
10. The system saves the updated rental record.
11. The system returns the updated rental information.

### Alternative / Exception Flows

**A1 — Rental Record Not Found**

* The system cannot find the specified rental record.
* The rental completion process is terminated.
* A resource-not-found error is returned.

**A2 — Invalid Ending Kilometer**

* The ending kilometer is lower than the starting kilometer.
* The rental completion process is terminated.
* A business rule error is returned.

### Postconditions

If the rental is successfully completed:

* The ending kilometer is recorded.
* The vehicle's kilometer is updated.
* The vehicle becomes available for another rental.
* The rental end date is recorded.
* The total rental price is calculated and recorded.
* The rental record is updated in the system.
* The updated rental information is returned to the System User.

## UC-03 — Add Customer

**Actor:** System User

**Purpose:**
Create a new customer in the vehicle rental system.

### Preconditions

* The required customer information is provided.
* The customer's age is at least 18.
* The national card number contains exactly 11 digits.
* The national card number is not already registered in the system.

### Main Flow

1. The System User sends a request containing the customer information.
2. The system validates the customer information.
3. The system checks whether the customer's age is at least 18.
4. The system checks whether the national card number has exactly 11 digits.
5. The system checks whether the national card number already exists.
6. The system creates a new customer.
7. The system saves the customer.
8. The system returns the created customer information.

### Alternative / Exception Flows

**A1 — Invalid Customer Information**

* One or more required validation rules are not satisfied.
* The customer creation process is terminated.
* A validation error is returned.

**A2 — Customer Is Under 18**

* The customer's age is below 18.
* The customer creation process is terminated.
* A validation err

## UC-04 — Add Car

**Actor:** System User

**Purpose:**
Create a new vehicle in the vehicle rental system.

### Preconditions

* The required vehicle information is provided.
* The vehicle model year is 1990 or later.

### Main Flow

1. The System User sends a request containing the vehicle information.
2. The system validates the vehicle information.
3. The system checks whether the vehicle model year is 1990 or later.
4. The system creates a new vehicle.
5. The system saves the vehicle.
6. The system returns the created vehicle information.

### Alternative / Exception Flows

**A1 — Invalid Vehicle Information**

* One or more required validation rules are not satisfied.
* The vehicle creation process is terminated.
* A validation error is returned.

**A2 — Invalid Model Year**

* The vehicle model year is lower than 1990.
* The vehicle creation process is terminated.
* A business rule error is returned.

### Postconditions

If the vehicle is successfully created:

* A new vehicle exists in the system.
* The vehicle information is saved.
* The created vehicle information is returned to the System User.
