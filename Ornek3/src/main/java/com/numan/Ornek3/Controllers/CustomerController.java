package com.numan.Ornek3.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.numan.Ornek3.Models.Customer;
import com.numan.Ornek3.Services.CustomerService;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
public class CustomerController {
	
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
	

	@GetMapping("/all-customers-list")
	public List<Customer> getAllCustomer(){
		return customerService.getAllCustomers();
	}
	
	@GetMapping("/customer/{id}")
	public Customer getCustomerById(@PathVariable int id){
		return customerService.getCustomerById(id);
	}
	
	@GetMapping("/customer-getby-name/{name}")
	public List<Customer> getCustomerByName(@PathVariable (name = "name")  String name){
		return customerService.getCustomerByName(name);
	}
	
	@GetMapping("/customer-getby-surname")
	public List<Customer> getCustomerBySurName(@RequestParam String surName){
		return customerService.getCustomerBySurName(surName);
	}
	
	@PostMapping("/add-customer")
	public Customer addCustomer(@RequestBody Customer customer) {
		return customerService.addCustomer(customer);
	}
	
	@DeleteMapping("/delete-customer/{id}")
	public void deleteCustomer(@PathVariable Integer id) {
		customerService.deleteCustomer(id);
	}
	
	@PutMapping("/update-customer")
	public void putCustomer(@RequestBody Customer customer) {
		customerService.updateCustomer(customer);
	}

}
