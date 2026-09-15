package com.numan.Ornek3.Services;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.numan.Ornek3.Models.CarResponse;
import com.numan.Ornek3.Models.Customer;
import com.numan.Ornek3.Models.CustomerRequest;
import com.numan.Ornek3.Models.CustomerResponse;
import com.numan.Ornek3.Models.DailyRentalPriceResponse;
import com.numan.Ornek3.Models.EndingRentalResponse;
import com.numan.Ornek3.Models.MyException;
import com.numan.Ornek3.Models.RentalRecord;
import com.numan.Ornek3.Repositories.CustomerRepository;
import com.numan.Ornek3.Repositories.RentalRecordRepository;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final RentalRecordRepository rentalRecordRepository;

    public CustomerService(CustomerRepository customerRepository,RentalRecordRepository rentalRecordRepository) {
        this.customerRepository = customerRepository;
        this.rentalRecordRepository=rentalRecordRepository;
    }
	
	public List<CustomerResponse> getAllCustomers(){
		List<CustomerResponse> customerResponseList=new ArrayList<>();
		List<Customer> customerList=customerRepository.findAll();
		for(Customer customer:customerList) {
//			CustomerResponse response=new CustomerResponse();
//			BeanUtils.copyProperties(customer, response);
//			customerResponseList.add(response);
			customerResponseList.add(doCopy(customer));
		}
		return customerResponseList;
	}
	
	public Customer getCustomerById(Integer id) { 
		return customerRepository.findById(id)
				.orElseThrow(() -> new MyException("The customer wasn't found."));
	}
	
	public CustomerResponse getCustomerResponseById(Integer id) { 
		Customer realCustomer=customerRepository.findById(id)
				.orElseThrow(() -> new MyException("The customer wasn't found."));
		CustomerResponse response=new CustomerResponse();	
		BeanUtils.copyProperties(realCustomer, response);
		return response;
	}
	
	public Customer getCustomerByNationalCardNo(String nationalCardNo) {
		return customerRepository.findByNationalCardNo(nationalCardNo);
	}

	public CustomerResponse addCustomer(CustomerRequest customer) {
		
//		if (customer.getName() == null) {
//		    throw new MyException("The customer's name cannot be null.");
//		}
//		
//		if (customer.getSurName() == null) {
//		    throw new MyException("The customer's surname cannot be null.");
//		}
//		
//		if (customer.getDriversLicenseType() == null) {
//		    throw new MyException("The customer's driver licanse cannot be null.");
//		}
//		if (customer.getAge() == null) {
//		    throw new MyException("The customer's age cannot be null.");
//		}
//
//		if (customer.getAge() < 18) {
//		    throw new MyException("The customer's age cannot be less than 18.");
//		}
//
//		if (customer.getNationalCardNo() == null) {
//		    throw new MyException("The national card number cannot be null.");
//		}
//
//		if (customer.getNationalCardNo().length() != 11) {
//		    throw new MyException("The national card number must be 11 digits long.");
//		}
//
//		for (int i = 0; i < customer.getNationalCardNo().length(); i++) {
//		    if (!Character.isDigit(customer.getNationalCardNo().charAt(i))) {
//		        throw new MyException("The national card number must contain only digits.");
//		    }
//		}
//
		if (getCustomerByNationalCardNo(customer.getNationalCardNo()) != null) {
		    throw new MyException("There is a customer who uses this national card no.");
		}
	
		Customer realCustomer=new Customer();
		BeanUtils.copyProperties(customer, realCustomer);
		
//		realCustomer.setName(customer.getName());
//		realCustomer.setSurName(customer.getSurName());
//		realCustomer.setAge(customer.getAge());
//		realCustomer.setNationalCardNo(customer.getNationalCardNo());
//		realCustomer.setDriversLicanceType(customer.getDriversLicenseType());
		customerRepository.save(realCustomer);
//		
		CustomerResponse customerResponse=new CustomerResponse();
		BeanUtils.copyProperties(realCustomer, customerResponse);
		
		
//		customerResponse.setName(realCustomer.getName());
//		customerResponse.setSurName(realCustomer.getSurName());
//		customerResponse.setAge(realCustomer.getAge());
//		
		return customerResponse;	
	}
	
	public void deleteCustomer(int id) {
	    Customer customer = getCustomerById(id);
	    customerRepository.delete(customer);
	}
	
	public CustomerResponse updateCustomer(Integer id,CustomerRequest customerRequest) {
		Customer customerr=getCustomerByNationalCardNo(customerRequest.getNationalCardNo());
		Customer oldCustomer = getCustomerById(id);
		if(customerr != null && !oldCustomer.getId().equals(customerr.getId())) {
			throw new MyException("There is a customer who uses this national card no.");
		}	
		BeanUtils.copyProperties(customerRequest,oldCustomer);
		customerRepository.save(oldCustomer);
//		oldCustomer.setName(customer.getName());
//		oldCustomer.setSurName(customer.getSurName());
//		oldCustomer.setAge(customer.getAge());
		
//		CustomerResponse response=new CustomerResponse();	
//		BeanUtils.copyProperties(oldCustomer, response);
		
//		return response
		return doCopy(oldCustomer);
	}
	
	public List<CustomerResponse> getCustomerByName(String name) {
		List<CustomerResponse> customerResponseList= new ArrayList<>();
		List<Customer> customerList=customerRepository.findByName(name);
		for (Customer customer:customerList) {
//			CustomerResponse response=new CustomerResponse();
//			BeanUtils.copyProperties(customer, response);
//			customerResponseList.add(response);
			customerResponseList.add(doCopy(customer));
		}
		return customerResponseList;
	}
	
	public List<CustomerResponse> getCustomerBySurName(String surName) {
		List<CustomerResponse> customerResponseList= new ArrayList<>();
		List<Customer> customerList=customerRepository.findBySurName(surName);
		for (Customer customer:customerList) {
//			CustomerResponse response=new CustomerResponse();
//			BeanUtils.copyProperties(customer, response);
//			customerResponseList.add(response);
			customerResponseList.add(doCopy(customer));
		}
		return customerResponseList;
		
	}
	
	public CustomerResponse doCopy(Customer customer){
		CustomerResponse response=new CustomerResponse();
		BeanUtils.copyProperties(customer, response);
		return response;
	}
	
	public CustomerResponse getCustomerWithRentals(Integer customerId) {
		Customer customer=getCustomerById(customerId);
		CustomerResponse response=new CustomerResponse();
		BeanUtils.copyProperties(customer, response);
		List<RentalRecord> records=rentalRecordRepository.findByCustomerId(customerId);
		if (records==null || records.isEmpty()) {
			throw new MyException("There is no rental for this customer.");
		}
		for(RentalRecord rentalRecord:records) {
			EndingRentalResponse rentalResponse=new EndingRentalResponse();
			BeanUtils.copyProperties(rentalRecord, rentalResponse);
			CarResponse carResponse=new CarResponse();
			DailyRentalPriceResponse priceResponse=new DailyRentalPriceResponse();
			BeanUtils.copyProperties(rentalRecord.getCar(), carResponse);
			BeanUtils.copyProperties(rentalRecord.getDailyRentalPrice(), priceResponse);
			rentalResponse.setCar(carResponse);
			rentalResponse.setDailyRentalPrice(priceResponse);
			response.getRentalRecordsList().add(rentalResponse);
		}
		return response;
	}
	
	public CustomerResponse getCustomerWithCars(Integer customerId) {
		
		Customer customer=getCustomerById(customerId);
		CustomerResponse response=new CustomerResponse();
		BeanUtils.copyProperties(customer, response);
		List<RentalRecord> records=rentalRecordRepository.findByCustomerId(customerId);
		if (records==null || records.isEmpty()) {
			throw new MyException("There is no rental for this customer.");
		}
		
		for(RentalRecord rentalRecord:records) {
			CarResponse carResponse=new CarResponse();
			BeanUtils.copyProperties(rentalRecord.getCar(), carResponse);
			response.getCarsList().add(carResponse);
		}
		
		return response;
		
		
	}
	
}