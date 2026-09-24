package com.numan.Ornek3.Models.dto.response;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Builder
public class CustomerResponse {
	
	private String name;
	private String surName;
	private Integer age;
	
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<CustomerRentalResponse> rentalRecordsList;
	
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private List<CarResponse> carsList;
}