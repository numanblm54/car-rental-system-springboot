package com.numan.Ornek3.Controllers;

import org.springframework.web.bind.annotation.RestController;

import com.numan.Ornek3.Models.Car;
import com.numan.Ornek3.Models.VehicleTypes;
import com.numan.Ornek3.Services.CarService;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
public class CarController {
	public CarService carService;
	
	public CarController(CarService carService) {
		this.carService=carService;
	}
	
	@PostMapping("/add-Car")
	public void addCar(@RequestBody Car car) {
		carService.addCar(car);
	}
	
	@GetMapping("/getAllCars")
	public List<Car> getAllCars() {
		return carService.getAllCars();
	}
	
	@GetMapping("/getOneCar/{id}")
	public Car getCarById(@PathVariable int id) {
		return carService.getCarById(id);
	}
	
	@DeleteMapping("/delete-car/{id}")
	public void deleteCar(@PathVariable int id) {
		carService.deleteCar(id);
	}
	
	@PutMapping("/update-car")
	public Car updateCar(@RequestBody Car car) {
		return carService.updateCar(car);
	}
	
	@PutMapping("/update-car-type/{id}")
	public Car updateVehicleType(@RequestBody VehicleTypes vehicleType,@PathVariable Integer id) {
		return carService.updateVehicleType(vehicleType,id);
	}
	
	
	

	
	

}
