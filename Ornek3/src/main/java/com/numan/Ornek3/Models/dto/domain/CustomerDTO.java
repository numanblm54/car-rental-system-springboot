package com.numan.Ornek3.Models.dto.domain;

import java.util.List;

import com.numan.Ornek3.Models.DriversLicenseTypes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
	
	private Integer id;
	private String name;
	private String surName;
	private Integer age;
	private String nationalCardNo;
	private DriversLicenseTypes driversLicenseType;
	private List<RentalRecordDTO> rentalRecord;
	private List<CarDTO> car;
}