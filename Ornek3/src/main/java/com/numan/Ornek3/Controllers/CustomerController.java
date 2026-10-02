package com.numan.Ornek3.controllers;

import java.io.IOException;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.numan.Ornek3.Models.dto.request.CustomerRequest;
import com.numan.Ornek3.Models.dto.response.CustomerResponse;
import com.numan.Ornek3.Services.CustomerService;
import com.numan.Ornek3.mapper.CustomerMapper;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class CustomerController {
	
	private final CustomerService customerService;
    private final CustomerMapper customerMapper;
    
	public CustomerController(CustomerService customerService, CustomerMapper customerMapper) {
		this.customerService = customerService;
		this.customerMapper = customerMapper;
	}

	
	@GetMapping("/all-customers-list")
	public List<CustomerResponse> getAllCustomers(){
		var customerDTOList = customerService.getAllCustomers();
		var customerResponseList = customerMapper.mapToCustomerResponseList(customerDTOList);
		return customerResponseList;
	}
	
	
	@GetMapping("/customer/{id}")
	public CustomerResponse getCustomerById(@PathVariable int id){
		var customerDTO = customerService.getCustomerDTOById(id);
		var customerResponse = customerMapper.mapToCustomerResponse(customerDTO);
		return customerResponse;
	}
	
	
	@GetMapping("/customer-with-rentals/{id}")
	public CustomerResponse getCustomerWithRentals(@PathVariable int id){
	    var customerDTO = customerService.getCustomerWithRentals(id);
	    var customerResponse = customerMapper.mapToCustomerResponse(customerDTO);
	    return customerResponse;
	}
	
	
	@GetMapping("/customer-with-cars/{id}")
	public CustomerResponse getCustomerWithCars(@PathVariable int id){
		var customerDTO = customerService.getCustomerWithCars(id);
		var customerResponse = customerMapper.mapToCustomerResponseWithCar(customerDTO);
		return customerResponse;
	}
	
	
	@GetMapping("/customer-getby-name/{name}")
	public List<CustomerResponse> getCustomerByName(@PathVariable String name){
		var customerDTOList = customerService.getCustomerByName(name);
		var customerResponseList = customerMapper.mapToCustomerResponseList(customerDTOList);
		return customerResponseList;
	}
	
	
	@GetMapping("/customer-getby-surname")
	public List<CustomerResponse> getCustomerBySurName(@RequestParam String surName){
		var customerDTOList = customerService.getCustomerBySurName(surName);
		var customerResponseList = customerMapper.mapToCustomerResponseList(customerDTOList);
		return customerResponseList;
	}
	
	
	@PostMapping("/add-customer")
	public CustomerResponse addCustomer(@Valid @RequestBody CustomerRequest customerRequest) {
		var customerDTO = customerMapper.mapCustomerRequestToCustomerDTO(customerRequest);
		var lastCustomerDTO = customerService.addCustomer(customerDTO);
		var customerResponse = customerMapper.mapToCustomerResponse(lastCustomerDTO);
		return customerResponse;
	}
	
	
	@DeleteMapping("/delete-customer/{id}")
	public void deleteCustomer(@PathVariable Integer id) {
		customerService.deleteCustomer(id);
	}
	
	
	@PutMapping("/update-customer/{id}")
	public CustomerResponse putCustomer(@Valid @PathVariable Integer id,@RequestBody CustomerRequest customerRequest) {
		var customerDTO = customerMapper.mapCustomerRequestToCustomerDTO(customerRequest);
		var lastCustomerDTO = customerService.updateCustomer(id,customerDTO);
		var customerResponse = customerMapper.mapToCustomerResponse(lastCustomerDTO);
		return customerResponse;
	}
	
	@PostMapping("/customers/import")
	public String importCustomers(@RequestParam MultipartFile file) {
	    customerService.importCustomers(file);
	    return "Customers imported successfully";
	}
	
	@GetMapping("/export")
	public String exportCustomers() {

	    customerService.exportCustomers();

	    return "Customers exported successfully";
	}
}