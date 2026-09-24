package com.numan.Ornek3.serviceTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
import com.numan.Ornek3.Models.entity.Car;
import com.numan.Ornek3.Models.entity.Customer;
import com.numan.Ornek3.Models.entity.DailyRentalPrice;
import com.numan.Ornek3.Models.entity.RentalRecord;
import com.numan.Ornek3.Repositories.RentalRecordRepository;
import com.numan.Ornek3.Services.CarService;
import com.numan.Ornek3.Services.CustomerService;
import com.numan.Ornek3.Services.DailyRentalPriceService;
import com.numan.Ornek3.Services.RentalService;
import com.numan.Ornek3.mapper.RentalRecordMapper;
import com.numan.Ornek3.testUtils.TestDataFactory;
import com.numan.Ornek3.Models.DriversLicenseTypes;
import com.numan.Ornek3.Models.MyException;
import com.numan.Ornek3.Models.VehicleTypes;
import com.numan.Ornek3.Models.dto.domain.CarDTO;
import com.numan.Ornek3.Models.dto.domain.CustomerDTO;
import com.numan.Ornek3.Models.dto.domain.DailyRentalPriceDTO;
import com.numan.Ornek3.Models.dto.domain.RentalRecordDTO;

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
    
    @Mock
    private RentalRecordMapper rentalRecordMapper;
    @InjectMocks
    private RentalService rentalService;
    
 
    @Test
    public void testStartRental() {
    	
    	Integer carId=1;
    	Integer customerId=1;
    	
    	Customer savedCustomer = TestDataFactory.newCustomer(1,"Ahmet", "Yılmaz", 25,"12345678901", DriversLicenseTypes.B);
        when(customerService.getCustomerById(customerId)).thenReturn(savedCustomer);
        
        Car savedCar = TestDataFactory.newCar(1, "Fiat", 1999, 5000, VehicleTypes.Car);
        savedCar.setIsItActive(true);
    	when(carService.getCarById(savedCar.getId())).thenReturn(savedCar);
    	
    	DailyRentalPrice savedPrice = TestDataFactory.newDailyRentalPrice(1, savedCar, new BigDecimal(5000));
		savedPrice.setIsItCurrent(true);
    	when(dailyRentalPriceservice.getCurrentPrice(savedCar.getId())).thenReturn(savedPrice);
    
    	RentalRecord savedRecord = TestDataFactory.newStartingRentalRecord(1, savedCar, savedCustomer, savedPrice, savedCar.getKm(),LocalDateTime.now());
    	when(rentalRecordRepository.save(any(RentalRecord.class))).thenReturn(savedRecord);
    	
    	CarDTO carDTO = TestDataFactory.newCarDTO(1, "Fiat", 1999, 5000, VehicleTypes.Car);
    	CustomerDTO customerDTO= TestDataFactory.newCustomerDTO(1,"Ahmet","Yılmaz", 25, "12345678901", DriversLicenseTypes.B);
    	DailyRentalPriceDTO priceDTO = TestDataFactory.savedDailyRentalPriceDTO(savedPrice.getId(), carDTO, new BigDecimal(5000));
    	RentalRecordDTO recordDTO = TestDataFactory.newStartingRentalRecordDTO(savedRecord.getId(),
    			carDTO,
    			customerDTO,
    			priceDTO,
    			carDTO.getKm(),
    			savedRecord.getStartingRentalDate());
    	when(rentalRecordMapper.mapToRentalRecordDTO(any(RentalRecord.class))).thenReturn(recordDTO);
    	
    	RentalRecordDTO result = rentalService.startRental(carId, customerId);
    	
    	assertNotNull(result);
    	assertEquals(recordDTO.getCar().getName(), result.getCar().getName());
    	assertEquals(recordDTO.getCustomer().getName(), result.getCustomer().getName());
    	assertEquals(recordDTO.getCustomer().getSurName(), result.getCustomer().getSurName());
    	assertFalse(savedCar.getIsItActive());
    }
    
    
    @Test
    public void testStartRental2() {
    	
    	Integer carId=1;
    	Integer customerId=1;
    	
    	Customer savedCustomer = TestDataFactory.newCustomer(1,"Ahmet", "Yılmaz", 25,"12345678901", DriversLicenseTypes.B);
        when(customerService.getCustomerById(customerId)).thenReturn(savedCustomer);
        
        Car savedCar = TestDataFactory.newCar(1, "Fiat", 1999, 5000, VehicleTypes.Car);
        savedCar.setIsItActive(true);
    	when(carService.getCarById(savedCar.getId())).thenReturn(savedCar);
    	
    	when(dailyRentalPriceservice.getCurrentPrice(savedCar.getId())).thenReturn(null);
    	
    	MyException exception = assertThrows(MyException.class, () -> rentalService.startRental(carId, customerId));	
    	assertEquals("There is no current price for this vehicle", exception.getMessage());
    }
    
    
    @Test
    public void testStartRental3() {
    	
    	Integer carId=1;
    	Integer customerId=1;
    	
    	Customer savedCustomer = TestDataFactory.newCustomer(1,"Ahmet", "Yılmaz", 25,"12345678901", DriversLicenseTypes.A);
        when(customerService.getCustomerById(customerId)).thenReturn(savedCustomer);
        
        Car savedCar = TestDataFactory.newCar(1, "Fiat", 1999, 5000, VehicleTypes.Car);
        savedCar.setIsItActive(true);
    	when(carService.getCarById(savedCar.getId())).thenReturn(savedCar);
    	
    	DailyRentalPrice savedPrice = TestDataFactory.newDailyRentalPrice(1, savedCar, new BigDecimal(5000));
		savedPrice.setIsItCurrent(true);
    	when(dailyRentalPriceservice.getCurrentPrice(savedCar.getId())).thenReturn(savedPrice);
    
    	MyException exception = assertThrows(MyException.class, () -> rentalService.startRental(carId, customerId));	
    	assertEquals("A person who has a type A driver's license cannot drive a car or a truck.", exception.getMessage());
    }
    
    
    
    @Test
    public void testEndRental() {
    
    	Customer savedCustomer = TestDataFactory.newCustomer(1,"Ahmet", "Yılmaz", 25,"12345678901", DriversLicenseTypes.B);
        
    	Car savedCar = TestDataFactory.newCar(1, "Fiat", 1999, 5000, VehicleTypes.Car);
        savedCar.setIsItActive(true);
    	
        DailyRentalPrice savedPrice = TestDataFactory.newDailyRentalPrice(1, savedCar, new BigDecimal(5000));
		savedPrice.setIsItCurrent(true);
    	
		RentalRecord savedRecord = TestDataFactory.newEndingRentalRecord(1,
				savedCar,
				savedCustomer,
				savedPrice,
				savedCar.getKm(),
				10000,
				LocalDateTime.of(2026, 9, 15, 10, 0),
				LocalDateTime.now(),
				new BigDecimal(3000));
    	when(rentalRecordRepository.findById(1))
        .thenReturn(Optional.of(savedRecord));
    
    	
    	CarDTO carDTO = TestDataFactory.newCarDTO(1, "Fiat", 1999, 5000, VehicleTypes.Car);
    	CustomerDTO customerDTO= TestDataFactory.newCustomerDTO(1,"Ahmet","Yılmaz", 25, "12345678901", DriversLicenseTypes.B);
    	DailyRentalPriceDTO priceDTO = TestDataFactory.savedDailyRentalPriceDTO(savedPrice.getId(), carDTO, new BigDecimal(5000));
    	RentalRecordDTO recordDTO = TestDataFactory.newEndingRentalRecordDTO(savedRecord.getId(),
    			carDTO,
    			customerDTO,
    			priceDTO,
    			carDTO.getKm(),
    			savedRecord.getEndingKm(),
    			savedRecord.getStartingRentalDate(),
    			savedRecord.getEndingRentalDate(),
    			savedRecord.getTotalRentalPrice());
    	when(rentalRecordMapper.mapToRentalRecordDTO(any(RentalRecord.class))).thenReturn(recordDTO);
    	
    	RentalRecordDTO result = rentalService.endRental(1, 10000);
    	
    	assertNotNull(result);
    	assertEquals(recordDTO.getCar().getName(), result.getCar().getName());
    	assertEquals(recordDTO.getCustomer().getName(), result.getCustomer().getName());
    	assertEquals(recordDTO.getCustomer().getSurName(), result.getCustomer().getSurName());
    	assertTrue(savedCar.getIsItActive());
    }
    
    @Test
    public void testEndRental2() {
    	
    	Customer savedCustomer = TestDataFactory.newCustomer(1,"Ahmet", "Yılmaz", 25,"12345678901", DriversLicenseTypes.B);
        
    	Car savedCar = TestDataFactory.newCar(1, "Fiat", 1999, 5000, VehicleTypes.Car);
        savedCar.setIsItActive(true);
    	
        DailyRentalPrice savedPrice = TestDataFactory.newDailyRentalPrice(1, savedCar, new BigDecimal(5000));
		savedPrice.setIsItCurrent(true);
    	
		RentalRecord savedRecord = TestDataFactory.newStartingRentalRecord(1,
				savedCar,
				savedCustomer,
				savedPrice,
				savedCar.getKm(),
				LocalDateTime.of(2026, 9, 15, 10, 0));
    	when(rentalRecordRepository.findById(1))
        .thenReturn(Optional.of(savedRecord));
    
    	MyException exception = assertThrows(MyException.class, () -> rentalService.endRental(1, 4000));	
    	assertEquals("The ending kilometer cannot be less than starting kilometer.", exception.getMessage());
    }
}