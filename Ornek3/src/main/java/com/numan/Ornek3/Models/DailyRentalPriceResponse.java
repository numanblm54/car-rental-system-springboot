package com.numan.Ornek3.Models;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DailyRentalPriceResponse {
	
	private Integer carId;
	private String carNameString;
	private VehicleTypes vehicleTypes;
	private BigDecimal price;

}
