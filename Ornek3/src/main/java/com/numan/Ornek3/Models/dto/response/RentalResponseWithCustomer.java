package com.numan.Ornek3.Models.dto.response;

import java.util.List;
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
public class RentalResponseWithCustomer {
	
	private CustomerResponse customer;
	private List<EndingRentalResponse> recordList;
}