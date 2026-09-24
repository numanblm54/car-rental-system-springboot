package com.numan.Ornek3.Models.dto.response;

import com.numan.Ornek3.Models.VehicleTypes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CarResponse {
	
	private String name;
	private Integer model;
	private VehicleTypes vehicleType;
}