package com.numan.Ornek3.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Car {
	
  
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
	private String name;
	private Integer model;
	

	private Integer km;
	
	@Enumerated(EnumType.STRING)
	private VehicleTypes vehicleType;
	
	private Boolean isItActive;
	
	public String  getName() {
		return name;
	}
	
	public void setName(String nameg) {
		this.name=nameg;
	}

	public int getModel() {
		return model;
	}

	public void setModel(int model) {
		this.model = model;
	}

	public int getKm() {
		return km;
	}

	public void setKm(int km) {
		this.km = km;
	}

	
	public Integer getId() {
		return id;
	}

	public VehicleTypes getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(VehicleTypes vehicleType) {
		this.vehicleType = vehicleType;
	}

	public Boolean getIsItActive() {
		return isItActive;
	}

	public void setIsItActive(Boolean isItActive) {
		this.isItActive =isItActive;
	}
}
