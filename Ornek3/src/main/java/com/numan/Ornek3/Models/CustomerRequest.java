package com.numan.Ornek3.Models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest {
	
	private String name;
	private String surName;
	private Integer age;
	private String nationalCardNo;
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
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
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
	public void setDriversLicenseType(DriversLicenseTypes driversLicenseType) {
		this.driversLicenseType = driversLicenseType;
	}

}
