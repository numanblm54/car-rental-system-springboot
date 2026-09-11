package com.numan.Ornek3.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Car {
	
  
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
	private String name;
	private Integer model;
	

	private Integer km;
	
	@Enumerated(EnumType.STRING)
	private VehicleTypes vehicleType;
	
	private Boolean isItActive;
	
}
