package com.numan.Ornek3.testUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.numan.Ornek3.Models.DriversLicenseTypes;
import com.numan.Ornek3.Models.VehicleTypes;
import com.numan.Ornek3.Models.dto.domain.CarDTO;
import com.numan.Ornek3.Models.dto.domain.CustomerDTO;
import com.numan.Ornek3.Models.dto.domain.DailyRentalPriceDTO;
import com.numan.Ornek3.Models.dto.domain.RentalRecordDTO;
import com.numan.Ornek3.Models.entity.Car;
import com.numan.Ornek3.Models.entity.Customer;
import com.numan.Ornek3.Models.entity.DailyRentalPrice;
import com.numan.Ornek3.Models.entity.RentalRecord;

public class TestDataFactory {
	
    public static CarDTO newCarDTO(Integer id, String name, Integer model, Integer km, VehicleTypes vehicleType) {
    	CarDTO carDTO = new CarDTO();
    	carDTO.setId(id);
    	carDTO.setName(name);
    	carDTO.setModel(model);
    	carDTO.setKm(km);
    	carDTO.setVehicleType(vehicleType);
    	return carDTO;
    }
    
    
    public static Car newCar(Integer id, String name, Integer model, Integer km, VehicleTypes vehicleType) {
    	Car car = new Car();
    	car.setId(id);
    	car.setName(name);
    	car.setModel(model);
    	car.setKm(km);
    	car.setVehicleType(vehicleType);
    	return car;
    }
    
    
    public static CarDTO savedCarDTO(Integer id, String name, Integer model, Integer km, VehicleTypes vehicleType) {
    	CarDTO carDTO = new CarDTO();
    	carDTO.setId(id);
    	carDTO.setName(name);
    	carDTO.setModel(model);
    	carDTO.setKm(km);
    	carDTO.setVehicleType(vehicleType);
    	return carDTO;
    }
    
    public static CustomerDTO newCustomerDTO(Integer id,
    		String name,
    		String surName,
    		Integer age,
    		String nationalCardNo,
    		DriversLicenseTypes driversLicenseType) {
    	CustomerDTO customerDTO=new CustomerDTO();
    	customerDTO.setId(id);
    	customerDTO.setName(name);
    	customerDTO.setSurName(surName);
    	customerDTO.setAge(age);
    	customerDTO.setNationalCardNo(nationalCardNo);
    	customerDTO.setDriversLicenseType(driversLicenseType);
    	return customerDTO;
    }
    
    
    public static Customer newCustomer(Integer id,
    		String name,
    		String surName,
    		Integer age,
    		String nationalCardNo,
    		DriversLicenseTypes driversLicenseType) {
        Customer customer = new Customer();
        customer.setId(id);
        customer.setName(name);
        customer.setSurName(surName);
        customer.setAge(age);
        customer.setNationalCardNo(nationalCardNo);
        customer.setDriversLicenseType(driversLicenseType);
        return customer;
    }
    
    
    public static CustomerDTO savedCustomerDTO(Integer id,
    		String name,
    		String surName,
    		Integer age,
    		String nationalCardNo,
    		DriversLicenseTypes driversLicenseType) {
    	CustomerDTO customerDTO=new CustomerDTO();
    	customerDTO.setId(id);
    	customerDTO.setName(name);
    	customerDTO.setSurName(surName);
    	customerDTO.setAge(age);
    	customerDTO.setNationalCardNo(nationalCardNo);
    	customerDTO.setDriversLicenseType(driversLicenseType);
    	return customerDTO;
    }
    
    public static DailyRentalPrice newDailyRentalPrice(Integer id, Car car, BigDecimal price) {
    	
    	DailyRentalPrice rentalPrice = new DailyRentalPrice();
    	rentalPrice.setId(id);
    	rentalPrice.setCar(car);
    	rentalPrice.setPrice(price);
    	return rentalPrice;
    }
    
    public static DailyRentalPriceDTO savedDailyRentalPriceDTO(Integer id, CarDTO carDTO, BigDecimal price) {
    	
    	DailyRentalPriceDTO rentalPriceDTO = new DailyRentalPriceDTO();
    	rentalPriceDTO.setId(id);
    	rentalPriceDTO.setCar(carDTO);
    	rentalPriceDTO.setPrice(price);
    	return rentalPriceDTO;
    }
    
    public static RentalRecord newStartingRentalRecord(Integer id,
    		Car car,
    		Customer customer,
    		DailyRentalPrice dailyRentalPrice,
    		Integer startingKm,
    		LocalDateTime startingDate) {
    	RentalRecord rentalRecord = new RentalRecord();
    	rentalRecord.setId(id);
    	rentalRecord.setCar(car);
    	rentalRecord.setCustomer(customer);
    	rentalRecord.setDailyRentalPrice(dailyRentalPrice);
    	rentalRecord.setStartingKm(startingKm);
    	rentalRecord.setStartingRentalDate(startingDate);
    	return rentalRecord;
    }
    
    public static RentalRecordDTO newStartingRentalRecordDTO(Integer id,
    		CarDTO carDTO,
    		CustomerDTO customerDTO,
    		DailyRentalPriceDTO dailyRentalPriceDTO,
    		Integer startingKm,
    		LocalDateTime startingDate) {
    	RentalRecordDTO rentalRecordDTO = new RentalRecordDTO();
    	rentalRecordDTO.setId(id);
    	rentalRecordDTO.setCar(carDTO);
    	rentalRecordDTO.setCustomer(customerDTO);
    	rentalRecordDTO.setDailyRentalPrice(dailyRentalPriceDTO);
    	rentalRecordDTO.setStartingKm(startingKm);
    	rentalRecordDTO.setStartingRentalDate(startingDate);
    	return rentalRecordDTO;
    }
    
    
    public static RentalRecord newEndingRentalRecord(Integer id,
    		Car car,
    		Customer customer,
    		DailyRentalPrice dailyRentalPrice,
    		Integer startingKm,
    		Integer finishKm,
    		LocalDateTime startingDate,
    		LocalDateTime endingDate,
    		BigDecimal totalRentalPrice) {
    	RentalRecord rentalRecord = new RentalRecord();
    	rentalRecord.setId(id);
    	rentalRecord.setCar(car);
    	rentalRecord.setCustomer(customer);
    	rentalRecord.setDailyRentalPrice(dailyRentalPrice);
    	rentalRecord.setStartingKm(startingKm);
    	rentalRecord.setEndingKm(finishKm);
    	rentalRecord.setStartingRentalDate(startingDate);
    	rentalRecord.setEndingRentalDate(endingDate);
    	rentalRecord.setTotalRentalPrice(totalRentalPrice);
    	return rentalRecord;
    }
    
    public static RentalRecordDTO newEndingRentalRecordDTO(Integer id,
    		CarDTO carDTO,
    		CustomerDTO customerDTO,
    		DailyRentalPriceDTO dailyRentalPriceDTO,
    		Integer startingKm,
    		Integer finishKm,
    		LocalDateTime startingDate,
    		LocalDateTime endingDate,
    		BigDecimal totalRentalPrice) {
    	RentalRecordDTO rentalRecordDTO = new RentalRecordDTO();
    	rentalRecordDTO.setId(id);
    	rentalRecordDTO.setCar(carDTO);
    	rentalRecordDTO.setCustomer(customerDTO);
    	rentalRecordDTO.setDailyRentalPrice(dailyRentalPriceDTO);
    	rentalRecordDTO.setStartingKm(startingKm);
    	rentalRecordDTO.setEndingKm(startingKm);
    	rentalRecordDTO.setStartingRentalDate(startingDate);
    	rentalRecordDTO.setEndingRentalDate(endingDate);
    	rentalRecordDTO.setTotalRentalPrice(totalRentalPrice);
    	return rentalRecordDTO;
    }
}