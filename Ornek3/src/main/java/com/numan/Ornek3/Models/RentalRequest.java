package com.numan.Ornek3.Models;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RentalRequest {
	
	@NotNull(message = "The customer id cannot be empty.")
	private Integer customerId;
	
	@NotNull(message = "The car id cannot be empty.")
	private Integer carId;
	
}
