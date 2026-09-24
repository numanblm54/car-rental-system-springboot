package com.numan.Ornek3.Models.dto.response;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DailyRentalPriceResponse {
	
	private CarResponse car;
	private BigDecimal price;
}