package com.numan.Ornek3.Models.dto.response;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public abstract class RentalResponse {
	
    private Integer startingKm;
    private LocalDateTime startingRentalDate;
    private CarResponse car;
    private CustomerResponse customer;
    private DailyRentalPriceResponse dailyRentalPrice;
}