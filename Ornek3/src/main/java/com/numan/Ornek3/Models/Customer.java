package com.numan.Ornek3.Models;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
	
	@OneToMany
	private List<RentalRecord> rentalRecord;
	
	@ManyToMany
	private List<Car> car;
	
}
