package com.numan.Ornek3.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customer {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
    
	private String name;
	private String surName;
	private Integer yas;
	private String nationalCardNo;
	
	@Enumerated(EnumType.STRING)
	private DriversLicenseTypes driversLicenseType;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurName() {
		return surName;
	}
	public void setSurName(String surName) {
		this.surName = surName;
	}
	public int getYas() {
		return yas;
	}
	public void setYas(int yas) {
		this.yas = yas;
	}
	
	public Integer getId() {
		return id; 
	}
	public String getNationalCardNo() {
		return nationalCardNo;
	}
	public void setNationalCardNo(String nationalCardNo) {
		this.nationalCardNo = nationalCardNo;
	}
	
	public DriversLicenseTypes getDriversLicenseType() {
		return driversLicenseType;
	}
	
	public void setDriversLicanceType(DriversLicenseTypes driversLicenseType){
		this.driversLicenseType=driversLicenseType;
	}
	
	

}
