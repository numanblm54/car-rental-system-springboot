package com.numan.Ornek3.Services;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.numan.Ornek3.Repositories.CarRepository;
import com.numan.Ornek3.Models.Car;
import com.numan.Ornek3.Models.CarRequest;
import com.numan.Ornek3.Models.CarResponse;
import com.numan.Ornek3.Models.MyException;


@Service
public class CarService {
	public CarRepository carRepository;
	
	public CarService(CarRepository carRepository) {
		this.carRepository=carRepository;
	}
	
	public CarResponse addCar(CarRequest carRequest) {
		if(carRequest.getModel()<1990) {
			throw new MyException("The car model year can't be smaller than 1990");
		}
		Car car =new Car();
		BeanUtils.copyProperties(carRequest, car);
		car.setIsItActive(true);
		carRepository.save(car);
		
		CarResponse response=new CarResponse();
		BeanUtils.copyProperties(car,response);
		return response;
	}
	
	public List<CarResponse> getAllCars() {
		List<Car> carList=carRepository.findAll();
		List<CarResponse> carResponseList=new ArrayList<>();
		for ( Car car :carList) {
			CarResponse carResponse= new CarResponse();
			BeanUtils.copyProperties(car, carResponse);
			carResponseList.add(carResponse);
		}
		return carResponseList;
	}
	
	 public Car getCarById(Integer id) {
		 return carRepository.findById(id)
				 .orElseThrow(() -> new MyException("The car wasn't found."));
	 }
	 
	 public CarResponse getCarResponseById(Integer id) {
		return doCopy(getCarById(id));
	 }
	 
	 public void deleteCar(Integer id) {
		 Car car = getCarById(id);
		 carRepository.delete(car);
	 }
	 
	 public CarResponse updateCarById(Integer id, CarRequest carRequest) {
		 Car oldCar= getCarById(id);
		 
		 BeanUtils.copyProperties(carRequest, oldCar);
//		 oldCar.setName(car.getName());
//		 oldCar.setModel(car.getModel());
//		 oldCar.setKm(car.getKm());
//		 oldCar.setVehicleType(car.getVehicleType());

		 return doCopy(oldCar);
		 
	 }
	 
	 public CarResponse doCopy(Car car) {
		 CarResponse response=new CarResponse();
		 BeanUtils.copyProperties(car, response);
		 return response;
	
	 }

}
