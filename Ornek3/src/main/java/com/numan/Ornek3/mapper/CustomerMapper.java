package com.numan.Ornek3.mapper;

import java.util.List;
import org.springframework.stereotype.Component;
import com.numan.Ornek3.Models.dto.domain.CustomerDTO;
import com.numan.Ornek3.Models.dto.request.CustomerRequest;
import com.numan.Ornek3.Models.dto.response.CustomerResponse;
import com.numan.Ornek3.Models.entity.Customer;

@Component
public class CustomerMapper {
	
	private final CarMapper carMapper;
	
	public CustomerMapper(
	        CarMapper carMapper,
	        DailyRentalPriceMapper dailyRentalPriceMapper) {
	    this.carMapper = carMapper;
	}
	
	
	public CustomerDTO mapCustomerRequestToCustomerDTO(CustomerRequest customerRequest) {
		return CustomerDTO.builder()
				.name(customerRequest.getName())
				.surName(customerRequest.getSurName())
				.age(customerRequest.getAge())
				.nationalCardNo(customerRequest.getNationalCardNo())
				.driversLicenseType(customerRequest.getDriversLicenseType())
				.build();
	}
	
	public CustomerResponse mapToCustomerResponse(CustomerDTO customerDTO) {
	    return CustomerResponse.builder()
	            .name(customerDTO.getName())
	            .surName(customerDTO.getSurName())
	            .age(customerDTO.getAge())
	            .build();
	}


	public List<CustomerResponse> mapToCustomerResponseList(List<CustomerDTO> customerDTOList){
	    return customerDTOList.stream()
	            .map(this::mapToCustomerResponse)
	            .toList();
	}
	
	
	public Customer mapToCustomer(CustomerDTO customerDTO) {
		return Customer.builder()
				.name(customerDTO.getName())
				.surName(customerDTO.getSurName())
				.age(customerDTO.getAge())
				.nationalCardNo(customerDTO.getNationalCardNo())
				.driversLicenseType(customerDTO.getDriversLicenseType())
				.build();
	}
	
	
	public List<Customer> mapToCustomerList(List<CustomerDTO> customerDTOList){
	    return customerDTOList.stream()
	            .map(this::mapToCustomer)
	            .toList();
	}
	
	
	public CustomerDTO mapToCustomerDTO(Customer customer) {
	    return CustomerDTO.builder()
	            .id(customer.getId())
	            .name(customer.getName())
	            .surName(customer.getSurName())
	            .age(customer.getAge())
	            .nationalCardNo(customer.getNationalCardNo())
	            .driversLicenseType(customer.getDriversLicenseType())
	            .build();
	}
	
	
	public List<CustomerDTO> mapToCustomerDTOList(List<Customer> customerList){
	    return customerList.stream()
	            .map(this::mapToCustomerDTO)
	            .toList();
	}
	
	
	public CustomerResponse mapToCustomerResponseWithCar(CustomerDTO customerDTO) {
		return CustomerResponse.builder()
	            .name(customerDTO.getName())
	            .surName(customerDTO.getSurName())
	            .age(customerDTO.getAge())
	            .carsList(carMapper.mapCarDTOListToCarResponseList(customerDTO.getCar()))
				.build();
	}
}