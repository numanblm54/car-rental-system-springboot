package com.numan.Ornek3.Models.dto.domain;


import com.numan.Ornek3.enums.VehicleTypes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarDTO {
	
    private Integer id;
	private String name;
	private Integer model;
	private Integer km;
	private VehicleTypes vehicleType;
}
