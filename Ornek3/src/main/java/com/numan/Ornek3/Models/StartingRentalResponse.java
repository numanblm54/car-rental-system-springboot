package com.numan.Ornek3.Models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StartingRentalResponse {
	
	private Integer customerId;
	private String customerName;
	private String customerSurname;
	private Integer carId;
	private String carName;
	private Integer carModel;
	private Integer startingKm;
	private LocalDateTime startingRentalDate;
	private VehicleTypes vehicleType;
	private BigDecimal priceList;
	
	public StartingRentalResponse(RentalRecord rentalRecord) {
	    Customer customer = rentalRecord.getCustomer();
	    Car car = rentalRecord.getCar();

	    this.customerId = customer.getId();
	    this.customerName = customer.getName();
	    this.customerSurname = customer.getSurName();
	    this.carId = car.getId();
	    this.carName = car.getName();
	    this.carModel = car.getModel();
	    this.startingKm = rentalRecord.getStartingKm();
	    this.startingRentalDate = rentalRecord.getStartingRentalDate();
	    this.vehicleType = car.getVehicleType();
	    this.priceList = rentalRecord.getPriceList();
	    
	}
}
