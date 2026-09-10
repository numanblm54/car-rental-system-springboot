package com.numan.Ornek3.Models;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class DailyRentalPrice {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@ManyToOne
	private Car car;
	
	private BigDecimal price;
	private Boolean isItCurrent;
	
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Boolean getIsItCurrent() {
		return isItCurrent;
	}
	public void setIsItCurrent(Boolean isItCurrent) {
		this.isItCurrent = isItCurrent;
	}
	
	public Car getCar() {
		return car;
	}
	
	public void setCar(Car car) {
		this.car=car;
	}
	


	
	
}
