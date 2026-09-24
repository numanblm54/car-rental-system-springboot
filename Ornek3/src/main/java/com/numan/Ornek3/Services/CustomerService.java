package com.numan.Ornek3.Services;

import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.numan.Ornek3.Models.dto.domain.CustomerDTO;
import com.numan.Ornek3.Models.entity.Car;
import com.numan.Ornek3.Models.entity.Customer;
import com.numan.Ornek3.Models.entity.RentalRecord;
import com.numan.Ornek3.Repositories.CustomerRepository;
import com.numan.Ornek3.Repositories.RentalRecordRepository;
import com.numan.Ornek3.mapper.CarMapper;
import com.numan.Ornek3.mapper.CustomerMapper;
import com.numan.Ornek3.mapper.RentalRecordMapper;
import com.numan.Ornek3.Models.MyException;

@Service
public class CustomerService {
	
    private final CustomerRepository customerRepository;
    private final RentalRecordRepository rentalRecordRepository;
    private final CustomerMapper customerMapper;
    private final RentalRecordMapper rentalRecordMapper;
    private final CarMapper carMapper; 
    
    public CustomerService(CustomerRepository customerRepository,
    	RentalRecordRepository rentalRecordRepository,
    	CustomerMapper customerMapper,
    	RentalRecordMapper rentalRecordMapper,
    	CarMapper carMapper) {
    	this.customerRepository = customerRepository;
    	this.rentalRecordRepository = rentalRecordRepository;
    	this.customerMapper = customerMapper;
    	this.rentalRecordMapper = rentalRecordMapper;
    	this.carMapper = carMapper;
    }

    
	public List<CustomerDTO> getAllCustomers(){
		List<Customer> customerList=customerRepository.findAll();
		var customerDTOList = customerMapper.mapToCustomerDTOList(customerList);
		return customerDTOList;
	}
	
	
	public Customer getCustomerById(Integer id) { 
		var customer = customerRepository .findById(id)
					.orElseThrow(() -> new MyException("The customer wasn't found."));
		return customer;
	}
	
	
	public CustomerDTO getCustomerDTOById(Integer id) { 
		var customer=customerRepository.findById(id)
				.orElseThrow(() -> new MyException("The customer wasn't found."));
		var customerDTO = customerMapper.mapToCustomerDTO(customer);
		return customerDTO;
	}
	
	
	public CustomerDTO getCustomerDTOByNationalCardNo(String nationalCardNo) {
		var customer = customerRepository.findByNationalCardNo(nationalCardNo);
		var customerDTO = customerMapper.mapToCustomerDTO(customer);
		return customerDTO;
	}
	
	
	public Customer getCustomerByNationalCardNo(String nationalCardNo) {
		var customer = customerRepository.findByNationalCardNo(nationalCardNo);
		return customer;
	}

	
	public CustomerDTO addCustomer(CustomerDTO customerDTO) {
		if (getCustomerByNationalCardNo(customerDTO.getNationalCardNo()) != null) {
		    throw new MyException("There is a customer who uses this national card no.");
		}
	
		var customer = customerMapper.mapToCustomer(customerDTO);
		customerRepository.save(customer);
		var lastCustomerDTO = customerMapper.mapToCustomerDTO(customer);
		return lastCustomerDTO;	
	}
	
	
	public void deleteCustomer(int id) {
	    Customer customer = getCustomerById(id);
	    customerRepository.delete(customer);
	}
	
	
	public CustomerDTO updateCustomer(Integer id,CustomerDTO customerDTO) {
		Customer customer=getCustomerByNationalCardNo(customerDTO.getNationalCardNo());
		Customer oldCustomer = getCustomerById(id);
		if(customer != null && !oldCustomer.getId().equals(customer.getId())) {
			throw new MyException("There is a customer who uses this national card no.");
		}	
		BeanUtils.copyProperties(customerDTO, oldCustomer,"id");
		customerRepository.save(oldCustomer);
		var lastCustomerDTO = customerMapper.mapToCustomerDTO(oldCustomer);
		return lastCustomerDTO;
	}
	
	
	public List<CustomerDTO> getCustomerByName(String name) {
		List<Customer> customerList = customerRepository.findByName(name);
		var customerDTOList = customerMapper.mapToCustomerDTOList(customerList);
		return customerDTOList;
	}
	
	
	public List<CustomerDTO> getCustomerBySurName(String surName) {
		List<Customer> customerList = customerRepository.findBySurName(surName);
		var customerDTOList = customerMapper.mapToCustomerDTOList(customerList);
		return customerDTOList;
	}
		
	
	public CustomerDTO getCustomerWithRentals(Integer customerId) {
		Customer customer = getCustomerById(customerId);
		List<RentalRecord> records = rentalRecordRepository.findByCustomerId(customerId);
		if (records==null || records.isEmpty()) {
			throw new MyException("There is no rental for this customer.");
		}

		var customerDTO = customerMapper.mapToCustomerDTO(customer);
		var rentalRecordDTOList = rentalRecordMapper.mapToRentalRecordDTOListWithoutCustomer(records);
		customerDTO.setRentalRecord(rentalRecordDTOList);
		return customerDTO;
	}
	
	
	public CustomerDTO getCustomerWithCars(Integer customerId) {
		
		Customer customer=getCustomerById(customerId);

		List<RentalRecord> records = rentalRecordRepository.findByCustomerId(customerId);
		if (records==null || records.isEmpty()) {
			throw new MyException("There is no rental for this customer.");
		}
		
		var customerDTO = customerMapper.mapToCustomerDTO(customer);
		List<Car> cars = records.stream()
		        .map(RentalRecord::getCar)
		        .toList();
		var carDTOList = carMapper.mapToCarDTOList(cars);
		customerDTO.setCar(carDTOList);
		return customerDTO;
	}
}