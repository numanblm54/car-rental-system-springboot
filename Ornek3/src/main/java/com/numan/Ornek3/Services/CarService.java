package com.numan.Ornek3.Services;

import java.util.List;
import org.springframework.stereotype.Service;

import com.numan.Ornek3.Repositories.CarRepository;
import com.numan.Ornek3.Models.Car;
import com.numan.Ornek3.Models.MyException;
import com.numan.Ornek3.Models.VehicleTypes;

@Service
public class CarService {
	public CarRepository carRepository;
	
	public CarService(CarRepository carRepository) {
		this.carRepository=carRepository;
	}
	
	public Car addCar(Car car) {
		if(car.getModel()<1990) {
			throw new MyException("The car model year can't be smaller than 1990");
		}
		car.setIsItActive(true);
		return carRepository.save(car);
	}
	
	public List<Car> getAllCars() {
		return carRepository.findAll();
	}
	
	 public Car getCarById(Integer id) {
		 return carRepository.findById(id)
				 .orElseThrow(() -> new MyException("The car wasn't found."));
	 }
	 
	 public void deleteCar(int id) {
		 carRepository.deleteById(id);
	 }
	 
	 public Car updateCar(Car car) {
		 Car oldCar=carRepository.findById(car.getId())
				 .orElseThrow(() -> new MyException("The car wasn't found."));
		 
		 oldCar.setName(car.getName());
		 oldCar.setModel(car.getModel());
		 oldCar.setKm(car.getKm());
		 oldCar.setVehicleType(car.getVehicleType());
		 
		 return carRepository.save(oldCar);
	 }
	 
	 public Car updateVehicleType(VehicleTypes vehicleType,Integer id) {
		 
		 Car oldCar=carRepository.findById(id)
				 .orElseThrow(() -> new RuntimeException("The car wasn't found."));
		 
		 oldCar.setVehicleType(vehicleType);
		 return carRepository.save(oldCar);
	 }

}
