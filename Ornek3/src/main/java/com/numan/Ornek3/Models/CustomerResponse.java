package com.numan.Ornek3.Models;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerResponse {
	
	private String name;
	private String surName;
	private Integer age;
	
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private List<RentalResponse> rentalRecordsList=new ArrayList<>();
	
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private List<CarResponse> carsList=new ArrayList<>();
	
}
