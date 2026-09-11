package com.numan.Ornek3.Models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class RentalRecord {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
//	private Integer customerIdInteger;
//	private String customerNameString;
//	private String customerSurnameString;
//	private Integer carIdInteger;
//	private String carNameString;
	private Integer startingKm;
	private Integer endingKm;
	private LocalDateTime startingRentalDate;
	private LocalDateTime endingRentalDate;
	private BigDecimal priceList;
	
    @ManyToOne
    private Car car;

    
    @ManyToOne
    private Customer customer;
    
    private BigDecimal totalRentalPrice;
	
}
