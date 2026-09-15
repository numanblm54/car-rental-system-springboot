package com.numan.Ornek3.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.numan.Ornek3.Models.CustomerRequest;
import com.numan.Ornek3.Models.CustomerResponse;
import com.numan.Ornek3.Services.CustomerService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PutMapping;


@RestController
public class CustomerController {
	
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
	

	@GetMapping("/all-customers-list")
	public List<CustomerResponse> getAllCustomer(){
		return customerService.getAllCustomers();
	}
	
	@GetMapping("/customer/{id}")
	public CustomerResponse getCustomerById(@PathVariable int id){
		return customerService.getCustomerResponseById(id);
	}
	
	@GetMapping("/customer-with-rentals/{id}")
	public CustomerResponse getCustomerWithRentals(@PathVariable int id){
		return customerService.getCustomerWithRentals(id);
	}
	
	@GetMapping("/customer-with-cars/{id}")
	public CustomerResponse getCustomerWithCars(@PathVariable int id){
		return customerService.getCustomerWithCars(id);
	}
	
	@GetMapping("/customer-getby-name/{name}")
	public List<CustomerResponse> getCustomerByName(@PathVariable String name){
		return customerService.getCustomerByName(name);
	}
	
	@GetMapping("/customer-getby-surname")
	public List<CustomerResponse> getCustomerBySurName(@RequestParam String surName){
		return customerService.getCustomerBySurName(surName);
	}
	
	@PostMapping("/add-customer")
	public CustomerResponse addCustomer(@Valid @RequestBody CustomerRequest customer) {
		return customerService.addCustomer(customer);
	}
	
	@DeleteMapping("/delete-customer/{id}")
	public void deleteCustomer(@PathVariable Integer id) {
		customerService.deleteCustomer(id);
	}
	
	@PutMapping("/update-customer/{id}")
	public CustomerResponse putCustomer(@Valid @PathVariable Integer id,@RequestBody CustomerRequest customerRequest) {
		return customerService.updateCustomer(id,customerRequest);
	}

}
