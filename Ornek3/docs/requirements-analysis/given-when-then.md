# Given / When / Then Scenarios

## US-01 — Start Rental

### Scenario 1 — Start Rental Successfully

**Given**

* The selected car exists in the system.
* The selected customer exists in the system.
* The car has an active daily rental price.
* The car is available for rental.
* The customer's driver's license is compatible with the vehicle.

**When**

* The system receives a rental start request with the car ID and customer ID.

**Then**

* A rental record is created.
* The current vehicle kilometer is recorded as the starting kilometer.
* The rental start date is recorded.
* The vehicle becomes unavailable for another rental.
* The rental information is returned.

---

### Scenario 2 — Car Does Not Exist

**Given**

* The selected car does not exist in the system.
* The selected customer exists in the system.

**When**

* The system receives a rental start request with the car ID and customer ID.

**Then**

* The rental must not start.
* The system returns a resource-not-found error.

---

### Scenario 3 — Customer Does Not Exist

**Given**

* The selected car exists in the system.
* The selected customer does not exist in the system.

**When**

* The system receives a rental start request with the car ID and customer ID.

**Then**

* The rental must not start.
* The system returns a resource-not-found error.

---

### Scenario 4 — No Active Daily Rental Price

**Given**

* The selected car exists in the system.
* The selected customer exists in the system.
* The car does not have an active daily rental price.

**When**

* The system receives a rental start request with the car ID and customer ID.

**Then**

* The rental must not start.
* The system returns an error indicating that there is no active price for the vehicle.

---

### Scenario 5 — Vehicle Is Already Being Used

**Given**

* The selected car exists in the system.
* The selected customer exists in the system.
* The car has an active daily rental price.
* The car is already being used.

**When**

* The system receives a rental start request with the car ID and customer ID.

**Then**

* The rental must not start.
* The system returns an error indicating that the vehicle is already being used.

---

### Scenario 6 — Driver's License Is Not Compatible

**Given**

* The selected car exists in the system.
* The selected customer exists in the system.
* The car has an active daily rental price.
* The car is available for rental.
* The customer's driver's license is not compatible with the vehicle type.

**When**

* The system receives a rental start request with the car ID and customer ID.

**Then**

* The rental must not start.
* The system returns a business rule error.

## US-02 — End Rental

### Scenario 1 — End Rental Successfully

**Given**

* The rental record exists in the system.
* The ending kilometer is not lower than the starting kilometer.

**When**

* The system receives a rental completion request with the rental ID and ending kilometer.

**Then**

* The ending kilometer is recorded.
* The vehicle's kilometer is updated.
* The vehicle becomes available for another rental.
* The rental end date is recorded.
* The total rental price is calculated.
* The rental record is updated.
* The updated rental information is returned.

---

### Scenario 2 — Rental Record Does Not Exist

**Given**

* The specified rental record does not exist in the system.

**When**

* The system receives a rental completion request with the rental ID and ending kilometer.

**Then**

* The rental must not be completed.
* The system returns a resource-not-found error.

---

### Scenario 3 — Ending Kilometer Is Lower Than Starting Kilometer

**Given**

* The rental record exists in the system.
* The ending kilometer is lower than the starting kilometer.

**When**

* The system receives a rental completion request with the rental ID and ending kilometer.

**Then**

* The rental must not be completed.
* The system returns a business rule error indicating that the ending kilometer cannot be lower than the starting kilometer.

## US-03 — Add Customer

### Scenario 1 — Add Customer Successfully

**Given**

* The required customer information is provided.
* The customer's age is 18 or older.
* The national card number contains exactly 11 digits.
* The national card number is not already registered in the system.

**When**

* The system receives a request to add a new customer.

**Then**

* A new customer is created.
* The customer is saved to the system.
* The created customer information is returned.

---

### Scenario 2 — Customer Is Under 18

**Given**

* The required customer information is provided.
* The customer's age is below 18.

**When**

* The system receives a request to add a new customer.

**Then**

* The customer must not be created.
* The system returns a validation error.

---

### Scenario 3 — National Card Number Is Invalid

**Given**

* The required customer information is provided.
* The national card number does not contain exactly 11 digits.

**When**

* The system receives a request to add a new customer.

**Then**

* The customer must not be created.
* The system returns a validation error.

---

### Scenario 4 — National Card Number Already Exists

**Given**

* The required customer information is provided.
* The customer's age is 18 or older.
* The national card number contains exactly 11 digits.
* The national card number is already registered to another customer.

**When**

* The system receives a request to add a new customer.

**Then**

* The customer must not be created.
* The system returns a duplicate resource error.

## US-04 — Add Car

### Scenario 1 — Add Car Successfully

**Given**

* The required vehicle information is provided.
* The vehicle model year is 1990 or later.

**When**

* The system receives a request to add a new vehicle.

**Then**

* A new vehicle is created.
* The vehicle is saved to the system.
* The created vehicle information is returned.

---

### Scenario 2 — Vehicle Model Year Is Invalid

**Given**

* The required vehicle information is provided.
* The vehicle model year is lower than 1990.

**When**

* The system receives a request to add a new vehicle.

**Then**

* The vehicle must not be created.
* The system returns a business rule error.
