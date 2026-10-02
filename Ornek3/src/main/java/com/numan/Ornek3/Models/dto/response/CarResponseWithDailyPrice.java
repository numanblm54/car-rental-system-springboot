package com.numan.Ornek3.Models.dto.response;


import java.math.BigDecimal;

import com.numan.Ornek3.enums.VehicleTypes;
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
public class CarResponseWithDailyPrice {
	private String name;
	private Integer model;
	private VehicleTypes vehicleType;
	private BigDecimal price;
}


