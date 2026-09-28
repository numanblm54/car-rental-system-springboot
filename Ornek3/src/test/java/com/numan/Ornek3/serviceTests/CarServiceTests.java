package com.numan.Ornek3.serviceTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.numan.Ornek3.Repositories.CarRepository;
import com.numan.Ornek3.Services.CarService;
import com.numan.Ornek3.enums.VehicleTypes;
import com.numan.Ornek3.mapper.CarMapper;
import com.numan.Ornek3.Models.dto.domain.CarDTO;
import com.numan.Ornek3.testUtils.TestDataFactory;
import com.numan.Ornek3.Models.entity.Car;

@ExtendWith(MockitoExtension.class)
public class CarServiceTests {
	
    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarService carService;
    
    @Mock
    private CarMapper carMapper;
    
    
    @Test
    public void testAddCar() {
    	
    	CarDTO carDTO = TestDataFactory.newCarDTO(1, "Fiat", 1999, 5000, VehicleTypes.Car);
    
    	Car savedCar = TestDataFactory.newCar(1, "Fiat", 1999, 5000, VehicleTypes.Car);

    	CarDTO saveCarDTO = TestDataFactory.savedCarDTO(1, "Fiat", 1999, 5000, VehicleTypes.Car);
    	
    	when(carMapper.mapCarDTOToCar(carDTO)).thenReturn(savedCar);
    	when(carMapper.mapCarToCarDto(savedCar)).thenReturn(saveCarDTO);
    	
    	CarDTO result = carService.addCar(carDTO);
    	assertNotNull(result);
    	assertEquals("Fiat", result.getName());
    }
    
    
    @Test
    public void testGetAllCars() {
    	
    	CarDTO carDTO1 = TestDataFactory.newCarDTO(1, "Fiat", 1999, 5000, VehicleTypes.Car);

    	CarDTO carDTO2 = TestDataFactory.newCarDTO(2, "Mercedes", 2000, 1000, VehicleTypes.Car);

     	CarDTO carDTO3= TestDataFactory.newCarDTO(3, "Porche", 2015, 500, VehicleTypes.Car);
     	
    	Car car1 = TestDataFactory.newCar(1, "Fiat", 1999, 5000, VehicleTypes.Car);

    	Car car2 = TestDataFactory.newCar(2, "Mercedes", 2000, 1000, VehicleTypes.Car);

     	Car car3= TestDataFactory.newCar(3, "Porche", 2015, 500, VehicleTypes.Car);
     	
    	List<Car> carList=new ArrayList<>();
    	carList.add(0, car1);
    	carList.add(1,car2);
    	carList.add(2,car3);
    	when(carRepository.findAll()).thenReturn(carList);
    	
    	List<CarDTO> carDTOList = new ArrayList<>();
    	carDTOList.add(0, carDTO1);
    	carDTOList.add(1,carDTO2);
    	carDTOList.add(2,carDTO3);
    	when(carMapper.mapToCarDTOList(carList)).thenReturn(carDTOList);
    	
    	List<CarDTO> result=carService.getAllCars();
    	 assertNotNull(result);
    	 assertEquals(result.get(0).getName(),"Fiat");
    	 assertEquals(result.get(1).getName(),"Mercedes");
    	 assertEquals(result.get(2).getName(),"Porche");
    }
}