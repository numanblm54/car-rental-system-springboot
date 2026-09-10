package com.numan.Ornek3.Services;
import java.util.List;

import org.springframework.stereotype.Service;

import com.numan.Ornek3.Models.Customer;
import com.numan.Ornek3.Models.MyException;
import com.numan.Ornek3.Repositories.CustomerRepository;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
	
	public List<Customer> getAllCustomers(){
		return customerRepository.findAll();
	}
	
	public Customer getCustomerById(Integer id) { 
		return customerRepository.findById(id)
				.orElseThrow(() -> new MyException("The customer wsnt found."));
	}
	public Customer getCustomerByNationalCardNo(String nationalCardNo) {
		return customerRepository.findByNationalCardNo(nationalCardNo);
	}
	
	public Customer addCustomer(Customer customer) {
		if(customer.getYas()<18) {
			throw new MyException("The customer's age cannot be less than 18.");
		}
		
		if(customer.getNationalCardNo().length()!=11) {
			throw new MyException("The national card number must be 11 digits long.");
		}
		
		for(int i=0;i<customer.getNationalCardNo().length(); i++) {
			if(!Character.isDigit(customer.getNationalCardNo().charAt(i))) {
				throw new MyException("The national card number must contain only digits.");
			}
		}
		
		if(getCustomerByNationalCardNo(customer.getNationalCardNo())!=null) {
			throw new MyException("There is a cutomer who uses this national card no.");
		}
		
		return customerRepository.save(customer);	
	}
	
	public void deleteCustomer(int id) {
		customerRepository.deleteById(id);
	}
	
	public Customer updateCustomer(Customer customer) {
		Customer oldCustomer = customerRepository.findById(customer.getId())
				.orElseThrow(() -> new MyException("Customer bulunamadı"));
		
		oldCustomer.setName(customer.getName());
		oldCustomer.setSurName(customer.getSurName());
		oldCustomer.setYas(customer.getYas());
		
		return customerRepository.save(oldCustomer);
	}
	
	public List<Customer> getCustomerByName(String name) {
		
		return customerRepository.findByName(name);
	}
	
	public List<Customer> getCustomerBySurName(String surName) {
		
		return customerRepository.findBySurName(surName);
	}

}
