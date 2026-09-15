package com.numan.Ornek3.Models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EndingRentalResponse implements RentalResponse {
	
//	private Integer customerId;
//	private String customerName;
//	private String customerSurname;
//	private Integer carId;
//	private String carName;
//	private Integer carModel;
	private Integer startingKm;
	private Integer endingKm;
	private LocalDateTime startingRentalDate;
	private LocalDateTime endingRentalDate;
//	private VehicleTypes vehicleType;
//	private BigDecimal priceList;
	private BigDecimal totalRentalPrice;
	
	private CarResponse car;
	private CustomerResponse customer;
	private DailyRentalPriceResponse dailyRentalPrice;
	
	
//	public EndingRentalResponse(RentalRecord rentalRecord) {
//	    Customer customer = rentalRecord.getCustomer();
//	    Car car = rentalRecord.getCar();
//
//	    this.customerId = customer.getId();
//	    this.customerName = customer.getName();
//	    this.customerSurname = customer.getSurName();
//
//	    this.carId = car.getId();
//	    this.carName = car.getName();
//	    this.carModel = car.getModel();
//
//	    this.startingKm = rentalRecord.getStartingKm();
//	    this.endingKm = rentalRecord.getEndingKm();
//	    this.startingRentalDate = rentalRecord.getStartingRentalDate();
//	    this.endingRentalDate = rentalRecord.getEndingRentalDate();
//
//	    this.vehicleType = car.getVehicleType();
//	    this.priceList = rentalRecord.getPriceList();
//	    this.totalRentalPrice = rentalRecord.getTotalRentalPrice();
//	}
	
    @Override
    public void setCar(CarResponse car) {
        this.car = car;
    }

    @Override
    public void setCustomer(CustomerResponse customer) {
        this.customer = customer;
    }

    @Override
    public void setDailyRentalPrice(DailyRentalPriceResponse price) {
        this.dailyRentalPrice = price;
    }

}
