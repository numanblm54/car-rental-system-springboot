package com.numan.Ornek3.Models;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class DailyRentalPrice {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
//	@ManyToOne
//	private Car car;
	
	private Integer carId;
	
	private String carNameString;
	
	@Enumerated(EnumType.STRING)
	private VehicleTypes vehicleTypes;
	
	private BigDecimal price;
	
	private Boolean isItCurrent;
	
}
