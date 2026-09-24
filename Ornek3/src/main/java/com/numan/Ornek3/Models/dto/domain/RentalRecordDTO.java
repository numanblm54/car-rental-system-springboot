package com.numan.Ornek3.Models.dto.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RentalRecordDTO {
	
    private Integer id;
	private Integer startingKm;
	private Integer endingKm;
	private LocalDateTime startingRentalDate;
	private LocalDateTime endingRentalDate;
    private CarDTO car;
    private CustomerDTO customer;
    private DailyRentalPriceDTO dailyRentalPrice;
    private BigDecimal totalRentalPrice;
}