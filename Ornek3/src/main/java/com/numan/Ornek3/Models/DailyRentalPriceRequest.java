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
public class DailyRentalPriceRequest {
	
	private Integer carId;

	private BigDecimal price;
	
//	private Boolean isItCurrent;

}
