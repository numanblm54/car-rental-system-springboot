package com.numan.Ornek3.Models;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarRequest {
	
	@NotBlank(message = "The car name cannot be empty.")
	private String name;
	
	@NotNull(message = "The car model cannot be empty.")
	private Integer model;
	
	@NotNull(message = "The kilometer cannot be null.")
	private Integer km;
		
	@NotNull(message = "The vehicle type cannot be null.")
	@Enumerated(EnumType.STRING)
	private VehicleTypes vehicleType;

}
