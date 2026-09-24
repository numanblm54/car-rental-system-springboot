package com.numan.Ornek3.Models.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Builder
public class CustomerRentalResponse {
	
    private Integer startingKm;
    private Integer endingKm;
    private LocalDateTime startingRentalDate;
    private LocalDateTime endingRentalDate;
    private BigDecimal totalRentalPrice;
    private CarResponse car;
    private DailyRentalPriceResponse dailyRentalPrice;
}