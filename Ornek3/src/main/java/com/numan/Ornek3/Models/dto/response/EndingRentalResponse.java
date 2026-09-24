package com.numan.Ornek3.Models.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class EndingRentalResponse extends RentalResponse {
	
    private Integer endingKm;
    private LocalDateTime endingRentalDate;
    private BigDecimal totalRentalPrice;
}