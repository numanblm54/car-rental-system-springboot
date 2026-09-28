package com.numan.Ornek3.serviceTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.numan.Ornek3.Repositories.DailyRentalPriceRepository;
import com.numan.Ornek3.Services.CarService;
import com.numan.Ornek3.Services.DailyRentalPriceService;
import com.numan.Ornek3.enums.VehicleTypes;
import com.numan.Ornek3.exception.MyException;
import com.numan.Ornek3.mapper.DailyRentalPriceMapper;
import com.numan.Ornek3.Models.dto.domain.CarDTO;
import com.numan.Ornek3.Models.dto.domain.DailyRentalPriceDTO;
import com.numan.Ornek3.Models.entity.Car;
import com.numan.Ornek3.Models.entity.DailyRentalPrice;
import com.numan.Ornek3.testUtils.TestDataFactory;

@ExtendWith(MockitoExtension.class)
public class DailyRentalPriceServiceTests {
	
	@InjectMocks
	private DailyRentalPriceService dailyRentalPriceService;
	
	 @Mock
	 private DailyRentalPriceRepository dailyRentalPriceRepository;
	 
	 @Mock
	 private CarService carService;
	 
	 @Mock
	 private DailyRentalPriceMapper dailyRentalPriceMapper;
	 
	 @Test
	 public void testAddDailyrentalPrice() {
		 
	    	Car savedCar = TestDataFactory.newCar(1, "Fiat", 1999, 5000,VehicleTypes.Car);
	    	when(carService.getCarById(1)).thenReturn(savedCar);
	        
	    	BigDecimal dailyPrice = new BigDecimal("5000");
	    	when(dailyRentalPriceRepository.findByCarIdAndIsItCurrentTrue(savedCar.getId())).thenReturn(null);
	    	
	    	CarDTO carDTO = TestDataFactory.newCarDTO(savedCar.getId(), savedCar.getName(), savedCar.getModel(), savedCar.getKm(), savedCar.getVehicleType());
	    	
	    	DailyRentalPriceDTO priceDTO = TestDataFactory.savedDailyRentalPriceDTO(1, carDTO, dailyPrice);
	    	when(dailyRentalPriceMapper.mapToDailyRentalPriceDTO(any(DailyRentalPrice.class))).thenReturn(priceDTO);
	    	
	    	DailyRentalPriceDTO result = dailyRentalPriceService.addDailyRentalPrice(savedCar.getId(), dailyPrice);
	    	assertNotNull(result);
	    	assertEquals(priceDTO.getPrice(), result.getPrice());	
	 }
	 
	 @Test
	 public void testAddDailyrentalPrice2() {
		 
		 when(carService.getCarById(1)).thenThrow(new MyException("The car wasn't found."));

		 MyException exception = assertThrows(MyException.class,() -> dailyRentalPriceService.addDailyRentalPrice(1,new BigDecimal("5000")));

		 assertEquals("The car wasn't found.", exception.getMessage());
	 }
	 
	 @Test
	 public void testAddDailyRentalPrice3() {
		 
		 Car savedCar = TestDataFactory.newCar(1, "Fiat", 1999, 5000,VehicleTypes.Car);
		 when(carService.getCarById(1)).thenReturn(savedCar);
	        
		 DailyRentalPrice oldPrice = TestDataFactory.newDailyRentalPrice(1, savedCar, new BigDecimal(5000));
		 oldPrice.setIsItCurrent(true);
		 when(dailyRentalPriceRepository.findByCarIdAndIsItCurrentTrue(savedCar.getId())).thenReturn(oldPrice);
	    	
		 dailyRentalPriceService.addDailyRentalPrice(1, new BigDecimal(5000));
	    	
		 assertEquals(false,oldPrice.getIsItCurrent()); 
	}	
}