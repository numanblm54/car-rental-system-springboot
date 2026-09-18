package com.numan.Ornek3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.numan.Ornek3.Models.Car;
import com.numan.Ornek3.Models.Customer;
import com.numan.Ornek3.Models.DailyRentalPrice;
import com.numan.Ornek3.Models.EndingRentalResponse;
import com.numan.Ornek3.Models.RentalRecord;
import com.numan.Ornek3.Models.StartingRentalResponse;
import com.numan.Ornek3.Models.VehicleTypes;
import com.numan.Ornek3.Repositories.RentalRecordRepository;
import com.numan.Ornek3.Services.CarService;
import com.numan.Ornek3.Services.CustomerService;
import com.numan.Ornek3.Services.DailyRentalPriceService;
import com.numan.Ornek3.Services.RentalService;

@ExtendWith(MockitoExtension.class)
public class RentalServiceTests {
	
    @Mock
    private RentalRecordRepository rentalRecordRepository;
    
    @Mock
    private CarService carService;
    @Mock
    private CustomerService customerService;
    
    @Mock
    private DailyRentalPriceService dailyRentalPriceservice;
    @InjectMocks
    private RentalService rentalService;
    
    
    @Test
    public void testStartRental() {
    	
    	Integer carId=1;
    	Integer customerId=1;
    	
    	Customer savedCustomer = new Customer();
        savedCustomer.setId(1);
        savedCustomer.setName("Ahmet");
        savedCustomer.setSurName("Yılmaz");
        savedCustomer.setAge(25);
        savedCustomer.setNationalCardNo("12345678901");
        when(customerService.getCustomerById(customerId)).thenReturn(savedCustomer);
        
    	Car savedCar=new Car();
    	savedCar.setId(1);
    	savedCar.setName("Fiat");
    	savedCar.setVehicleType(VehicleTypes.Car);
    	savedCar.setKm(5000);
    	savedCar.setModel(1999);
    	savedCar.setIsItActive(true);
    	when(carService.getCarById(carId)).thenReturn(savedCar);
    	
    	DailyRentalPrice savedPrice=new DailyRentalPrice();
    	savedPrice.setId(1);
    	savedPrice.setIsItCurrent(true);
    	savedPrice.setPrice(new BigDecimal("1000.00"));
    	savedPrice.setCar(savedCar);
    	when(dailyRentalPriceservice.getCurrentPrice(savedCar.getId())).thenReturn(savedPrice);
    
    	
    	
    	RentalRecord savedRecord=new RentalRecord();
    	savedRecord.setId(1);
    	savedRecord.setCar(savedCar);
    	savedRecord.setCustomer(savedCustomer);
    	savedRecord.setDailyRentalPrice(savedPrice);
    	savedRecord.setStartingKm(savedCar.getKm());
    	savedRecord.setStartingRentalDate(LocalDateTime.now());
    	
    	when(rentalRecordRepository.save(any(RentalRecord.class))).thenReturn(savedRecord);
    	
    	StartingRentalResponse response=rentalService.startRental(carId, customerId);
    	
    	System.out.println(response.getCar().getName());
    	assertNotNull(response);
    	assertEquals("Fiat", response.getCar().getName());
    	assertEquals("Ahmet", response.getCustomer().getName());
    	assertEquals("Yılmaz", response.getCustomer().getSurName());
    	assertFalse(savedCar.getIsItActive());
    	System.out.println(savedCar.getIsItActive());
    	
    	
    }
    
    
    @Test
    public void testEndRental() {
    	
    	Integer carId=1;
    	Integer customerId=1;
    	
    	Customer savedCustomer = new Customer();
        savedCustomer.setId(1);
        savedCustomer.setName("Ahmet");
        savedCustomer.setSurName("Yılmaz");
        savedCustomer.setAge(25);
        savedCustomer.setNationalCardNo("12345678901");
        //when(customerService.getCustomerById(customerId)).thenReturn(savedCustomer);
        
    	Car savedCar=new Car();
    	savedCar.setId(1);
    	savedCar.setName("Fiat");
    	savedCar.setVehicleType(VehicleTypes.Car);
    	savedCar.setKm(5000);
    	savedCar.setModel(1999);
    	savedCar.setIsItActive(true);
    	//when(carService.getCarById(carId)).thenReturn(savedCar);
    	
    	DailyRentalPrice savedPrice=new DailyRentalPrice();
    	savedPrice.setId(1);
    	savedPrice.setIsItCurrent(true);
    	savedPrice.setPrice(new BigDecimal("1000.00"));
    	savedPrice.setCar(savedCar);
    	//when(dailyRentalPriceservice.getCurrentPrice(savedCar.getId())).thenReturn(savedPrice);
    
    	
    	
    	RentalRecord savedRecord=new RentalRecord();
    	savedRecord.setId(1);
    	savedRecord.setCar(savedCar);
    	savedRecord.setCustomer(savedCustomer);
    	savedRecord.setDailyRentalPrice(savedPrice);
    	savedRecord.setStartingKm(savedCar.getKm());
//    	savedRecord.setStartingRentalDate(LocalDateTime.now());
    	savedRecord.setStartingRentalDate( LocalDateTime.of(2026, 9, 15, 10, 0));
    	
    	when(rentalRecordRepository.findById(1))
        .thenReturn(Optional.of(savedRecord));
    	
    	savedRecord.setEndingKm(10000);
    	savedRecord.setEndingRentalDate(LocalDateTime.now());
    	savedRecord.setTotalRentalPrice(new BigDecimal(3000));
    	
    	when(rentalRecordRepository.save(any(RentalRecord.class))).thenReturn(savedRecord);
    
    	
    	EndingRentalResponse response=rentalService.EndRental(savedRecord.getId(), 10000);
    	
    	
    	assertNotNull(response);
    	assertEquals("Fiat", response.getCar().getName());
    	assertEquals("Ahmet", response.getCustomer().getName());
    	assertEquals("Yılmaz", response.getCustomer().getSurName());
    	assertEquals(5000, response.getStartingKm());
    	assertEquals(10000, response.getEndingKm());
    	assertEquals(10000, savedCar.getKm());
    	assertTrue(savedCar.getIsItActive());
    	assertEquals(new BigDecimal("4000.00"), response.getTotalRentalPrice());
    	System.out.println(response.getCar().getName());
    	System.out.println(savedCar.getIsItActive());
    	System.out.println(savedRecord.getEndingRentalDate());
    	System.out.println(savedRecord.getStartingRentalDate());
    	
    	
    }

}
