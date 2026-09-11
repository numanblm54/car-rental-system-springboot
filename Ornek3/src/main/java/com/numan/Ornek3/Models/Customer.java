package com.numan.Ornek3.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Customer {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
    
	
	private String name;
	private String surName;
	private Integer age;
	private String nationalCardNo;
	
	@Enumerated(EnumType.STRING)
	private DriversLicenseTypes driversLicenseType;
	
}
