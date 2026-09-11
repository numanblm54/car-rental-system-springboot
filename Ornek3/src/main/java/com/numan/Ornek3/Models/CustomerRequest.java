package com.numan.Ornek3.Models;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerRequest {
	@NotBlank(message = "The customer's name cannot be empty.")
	private String name;
	@NotBlank(message = "The customer's surname cannot be empty.")
	private String surName;
	
	@NotNull(message = "The customer's age cannot be null.")
	@Min(value = 18, message = "The customer's age cannot be less than 18.")
	private Integer age;
	
	@NotBlank(message = "The national card number cannot be null or empty.")
	@Pattern(regexp = "\\d{11}", message = "The national card number must contain exactly 11 digits.")
	private String nationalCardNo;
	
	@NotNull(message = "The driver's license type cannot be null.")
	@Enumerated(EnumType.STRING)
	private DriversLicenseTypes driversLicenseType;
	
}
