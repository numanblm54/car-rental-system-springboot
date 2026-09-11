package com.numan.Ornek3.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CarResponse {
	
	private String name;
	private Integer model;
	private VehicleTypes vehicleType;

}
