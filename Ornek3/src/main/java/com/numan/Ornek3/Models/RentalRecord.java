package com.numan.Ornek3.Models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
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
	
	
	
//	public Integer getCustomerIdInteger() {
//		return customerIdInteger;
//	}
//	public void setCustomerIdInteger(Integer customerIdInteger) {
//		this.customerIdInteger = customerIdInteger;
//	}
//	public String getCustomerNameString() {
//		return customerNameString;
//	}
//	public void setCustomerNameString(String customerNameString) {
//		this.customerNameString = customerNameString;
//	}
//	public String getCustomerSurnameString() {
//		return customerSurnameString;
//	}
//	public void setCustomerSurnameString(String customerSurnameString) {
//		this.customerSurnameString = customerSurnameString;
//	}
//	public Integer getCarIdInteger() {
//		return carIdInteger;
//	}
//	public void setCarIdInteger(Integer carIdInteger) {
//		this.carIdInteger = carIdInteger;
//	}
//	public String getCarNameString() {
//		return carNameString;
//	}
//	public void setCarNameString(String carNameString) {
//		this.carNameString = carNameString;
//	}
	public Integer getStartingKm() {
		return startingKm;
	}
	public void setStartingKm(Integer startingKm) {
		this.startingKm = startingKm;
	}
	public Integer getEndingKm() {
		return endingKm;
	}
	public void setEndingKm(Integer endingKm) {
		this.endingKm = endingKm;
	}
	public LocalDateTime getStartingRentalDate() {
		return startingRentalDate;
	}
	public void setStartingRentalDate(LocalDateTime startingRentalDate) {
		this.startingRentalDate = startingRentalDate;
	}
	public LocalDateTime getEndingRentalDate() {
		return endingRentalDate;
	}
	public void setEndingRentalDate(LocalDateTime endingRentalDate) {
		this.endingRentalDate = endingRentalDate;
	}
	
	public Car getCar() {
	    return car;
	}

	public void setCar(Car car) {
	    this.car = car;
	}

	public Customer getCustomer() {
	    return customer;
	}

	public void setCustomer(Customer customer) {
	    this.customer = customer;
	}
	public BigDecimal getRentalPrice() {
		return totalRentalPrice;
	}
	public void setTotalRentalPrice(BigDecimal totalRentalPrice) {
		this.totalRentalPrice = totalRentalPrice;
	}
	public BigDecimal getPriceList() {
		return priceList;
	}
	public void setPriceList(BigDecimal priceList) {
		this.priceList = priceList;
	}


}
