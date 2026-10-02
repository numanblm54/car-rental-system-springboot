package com.numan.Ornek3.Services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.numan.Ornek3.Models.dto.domain.CustomerDTO;
import com.numan.Ornek3.Models.entity.Car;
import com.numan.Ornek3.Models.entity.Customer;
import com.numan.Ornek3.Models.entity.RentalRecord;
import com.numan.Ornek3.Repositories.CustomerRepository;
import com.numan.Ornek3.Repositories.RentalRecordRepository;
import com.numan.Ornek3.enums.DriversLicenseTypes;
import com.numan.Ornek3.exception.BaseException;
import com.numan.Ornek3.exception.DuplicateResourceException;
import com.numan.Ornek3.exception.ErrorCode;
import com.numan.Ornek3.exception.FileOperationException;
import com.numan.Ornek3.exception.ImportValidationException;
import com.numan.Ornek3.exception.ResourceNotFoundException;
import com.numan.Ornek3.mapper.CarMapper;
import com.numan.Ornek3.mapper.CustomerMapper;
import com.numan.Ornek3.mapper.RentalRecordMapper;

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
		if(customerList.isEmpty()) {
			throw new ResourceNotFoundException(ErrorCode.RESOURCE_NOT_FOUND, "There is no customer.");
		}
		var customerDTOList = customerMapper.mapToCustomerDTOList(customerList);
		return customerDTOList;
	}
	
	
	public Customer getCustomerById(Integer id) { 
		var customer = customerRepository .findById(id)
					.orElseThrow(() -> new ResourceNotFoundException(ErrorCode.RESOURCE_NOT_FOUND, "The customer wasn't found."));
		return customer;
	}
	
	
	public CustomerDTO getCustomerDTOById(Integer id) { 
		var customer=customerRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(ErrorCode.RESOURCE_NOT_FOUND, "The customer wasn't found."));
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
		    throw new DuplicateResourceException(ErrorCode.DUPLİCATE_RESOURCE, "There is already a customer with this national card number.");
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
			throw new DuplicateResourceException(ErrorCode.DUPLİCATE_RESOURCE, "There is already a customer with this national card number.");
		}	
		BeanUtils.copyProperties(customerDTO, oldCustomer,"id");
		customerRepository.save(oldCustomer);
		var lastCustomerDTO = customerMapper.mapToCustomerDTO(oldCustomer);
		return lastCustomerDTO;
	}
	
	
	public List<CustomerDTO> getCustomerByName(String name) {
		List<Customer> customerList = customerRepository.findByName(name);
		if(customerList.isEmpty()) {
			throw new ResourceNotFoundException(ErrorCode.RESOURCE_NOT_FOUND, "There is no customer with this name.");
		}
		var customerDTOList = customerMapper.mapToCustomerDTOList(customerList);
		return customerDTOList;
	}
	
	
	public List<CustomerDTO> getCustomerBySurName(String surName) {
		List<Customer> customerList = customerRepository.findBySurName(surName);
		if(customerList.isEmpty()) {
			throw new ResourceNotFoundException(ErrorCode.RESOURCE_NOT_FOUND, "There is no customer with this surname.");
		}
		var customerDTOList = customerMapper.mapToCustomerDTOList(customerList);
		return customerDTOList;
	}
		
	
	public CustomerDTO getCustomerWithRentals(Integer customerId) {
		Customer customer = getCustomerById(customerId);
		List<RentalRecord> records = rentalRecordRepository.findByCustomerId(customerId);
		if (records==null || records.isEmpty()) {
			throw new ResourceNotFoundException(ErrorCode.RESOURCE_NOT_FOUND, "There is no rental for this customer.");
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
			throw new ResourceNotFoundException(ErrorCode.RESOURCE_NOT_FOUND, "There is no rental for this customer.");
		}
		
		var customerDTO = customerMapper.mapToCustomerDTO(customer);
		List<Car> cars = records.stream()
		        .map(RentalRecord::getCar)
		        .toList();
		var carDTOList = carMapper.mapToCarDTOList(cars);
		customerDTO.setCar(carDTOList);
		return customerDTO;
	}
	
	public void importCustomers(MultipartFile file)  {
		 
		List<String> errors = new ArrayList<>();

	    List<Customer> customers = new ArrayList<>();

	    int batchSize = 500;

	    try (BufferedReader reader =
	                 new BufferedReader(
	                         new InputStreamReader(
	                                 file.getInputStream()))) {

	        
	        reader.readLine();

	        String line;

	        while ((line = reader.readLine()) != null) {

	            String[] parts = line.split(",");

	            CustomerDTO customerDTO = new CustomerDTO();

	            customerDTO.setName(parts[0]);

	            customerDTO.setSurName(parts[1]);

	            customerDTO.setAge(
	                    Integer.parseInt(parts[2])
	            );

	            customerDTO.setNationalCardNo(parts[3]);

	            customerDTO.setDriversLicenseType(
	                    DriversLicenseTypes.valueOf(parts[4])
	            );

	            try {

	                
	                if (getCustomerByNationalCardNo(
	                        customerDTO.getNationalCardNo()) != null) {

	                    throw new DuplicateResourceException(
	                            ErrorCode.DUPLİCATE_RESOURCE,
	                            "There is a customer who uses this national card no."
	                    );
	                }

	                
	                Customer customer =
	                        customerMapper.mapToCustomer(customerDTO);

	                
	                customers.add(customer);

	                
	                if (customers.size() == batchSize) {

	                    customerRepository.saveAll(customers);

	                    
	                    customers.clear();
	                }

	            } catch (BaseException e) {

	                
	                errors.add(
	                        customerDTO.getNationalCardNo()
	                                + ": "
	                                + e.getMessage()
	                );
	            }
	        }

	        
	        if (!customers.isEmpty()) {

	            customerRepository.saveAll(customers);
	        }

	    } catch (IOException e) {

	        
	        throw new FileOperationException(
	                ErrorCode.FILE_OPERATION_ERROR,
	                "Customer import failed because the file could not be read."
	        );

	    } catch (NumberFormatException e) {

	        
	        throw new ImportValidationException(
	                ErrorCode.INVALID_FILE_FORMAT,
	                "Customer import failed because the CSV contains an invalid number."
	        );

	    } catch (IllegalArgumentException e) {

	        
	        throw new ImportValidationException(
	                ErrorCode.INVALID_FILE_FORMAT,
	                "Customer import failed because the CSV contains an invalid value."
	        );
	    }

	    
	    if (!errors.isEmpty()) {

	        throw new ImportValidationException(
	                ErrorCode.IMPORT_VALIDATION_ERROR,
	                String.join(" | ", errors)
	        );
	    }
	  }
	
	public void exportCustomers() {

	    List<Customer> customers = customerRepository.findAll();

	    Path path = Paths.get("customers.csv");

	    System.out.println("Dosya yolu: " + path.toAbsolutePath());

	    try (BufferedWriter writer = Files.newBufferedWriter(path)) {

	        writer.write("id,name,surName,age,nationalCardNo");
	        writer.newLine();

	        for (Customer customer : customers) {

	            writer.write(
	                    customer.getId() + "," +
	                    customer.getName() + "," +
	                    customer.getSurName() + "," +
	                    customer.getAge() + "," +
	                    customer.getNationalCardNo()
	            );

	            writer.newLine();
	        }

	    } catch (IOException e) {

	        throw new FileOperationException(
	                ErrorCode.FILE_OPERATION_ERROR,
	                "Customer export failed because the file could not be written.",
	                e
	        );
	    }
	}
}