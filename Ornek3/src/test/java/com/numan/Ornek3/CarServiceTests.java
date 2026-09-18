package com.numan.Ornek3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.numan.Ornek3.Models.Car;
import com.numan.Ornek3.Models.CarRequest;
import com.numan.Ornek3.Models.CarResponse;
import com.numan.Ornek3.Models.VehicleTypes;
import com.numan.Ornek3.Repositories.CarRepository;

import com.numan.Ornek3.Services.CarService;

@ExtendWith(MockitoExtension.class)
public class CarServiceTests {
	
    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarService carService;
    
    @Test
    public void testAddCar() {
    	
    	CarRequest request=new CarRequest();
    	request.setName("Fiat");
    	request.setKm(5000);
    	request.setVehicleType(VehicleTypes.Car);
    	request.setModel(1899);
    
    	Car savedCar=new Car();
    	savedCar.setName("Fiat");
    	savedCar.setVehicleType(VehicleTypes.Car);
    	savedCar.setId(1);
    	savedCar.setKm(5000);
    	savedCar.setModel(1999);
    	savedCar.setIsItActive(true);
    	
    	when(carRepository.save(any(Car.class))).thenReturn(savedCar);
    	
    	CarResponse carResponse=carService.addCar(request);
    	
    	assertNotNull(carResponse);
    	assertEquals("Fiat", request.getName());
    	
    }
}
