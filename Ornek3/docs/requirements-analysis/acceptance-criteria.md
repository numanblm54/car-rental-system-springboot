# Acceptance Criteria

## US-01 — Start Rental

### AC-01 — Car and Customer Must Exist

* The selected car must exist in the system.
* The selected customer must exist in the system.
* If the car or customer does not exist, the rental must not start.

### AC-02 — Active Daily Rental Price

* The selected car must have an active daily rental price.
* If there is no active daily rental price, the rental must not start.

### AC-03 — Vehicle Availability

* The vehicle must be available for rental.
* If the vehicle is already being used, the rental must not start.

### AC-04 — License and Vehicle Compatibility

* The customer's driver's license type must be compatible with the vehicle type.
* If the license type is not compatible with the vehicle, the rental must not start.

### AC-05 — Successful Rental Start

When all required conditions are satisfied:

* A rental record must be created.
* The vehicle's current kilometer must be recorded as the starting kilometer.
* The rental start date must be recorded.
* The vehicle must become unavailable for another rental.
* The rental information must be returned.

---

## US-02 — End Rental

### AC-01 — Rental Record Must Exist

* The rental record must exist in the system.
* If the rental record does not exist, the rental must not be completed.

### AC-02 — Valid Ending Kilometer

* The ending kilometer must not be lower than the starting kilometer.
* If the ending kilometer is lower than the starting kilometer, the rental must not be completed.

### AC-03 — Successful Rental Completion

When the ending kilometer is valid:

* The ending kilometer must be recorded.
* The vehicle's kilometer must be updated.
* The vehicle must become available again.
* The rental end date must be recorded.
* The rental record must be updated.
* The updated rental information must be returned.

### AC-04 — Total Rental Price

* The rental duration must be calculated.
* The active daily rental price must be used.
* The total rental price must be calculated according to the rental duration.

---

## US-03 — Add Customer

### AC-01 — Customer Information Validation

* Required customer information must be provided.
* The customer's age must be at least 18.
* The national card number must contain exactly 11 digits.
* If the validation rules are not satisfied, the customer must not be created.

### AC-02 — National Card Number Must Be Unique

* The national card number must not already belong to another customer.
* If the national card number already exists, the customer must not be created.

### AC-03 — Successful Customer Creation

When all required conditions are satisfied:

* A new customer must be created.
* The customer must be saved to the system.
* The created customer information must be returned.

---

## US-04 — Add Car

### AC-01 — Valid Model Year

* The vehicle model year must be 1990 or later.
* If the model year is lower than 1990, the vehicle must not be created.

### AC-02 — Successful Car Creation

When the required conditions are satisfied:

* A new vehicle must be created.
* The vehicle must be saved to the system.
* The created vehicle information must be returned.
