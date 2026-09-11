package com.numan.Ornek3.Controllers;

import org.springframework.web.bind.annotation.RestController;


import com.numan.Ornek3.Models.CarRequest;
import com.numan.Ornek3.Models.CarResponse;

import com.numan.Ornek3.Services.CarService;

import jakarta.validation.Valid;

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
	public CarResponse addCar(@Valid @RequestBody CarRequest carRequest) {
		return carService.addCar(carRequest);
	}
	
	@GetMapping("/getAllCars")
	public List<CarResponse> getAllCars() {
		return carService.getAllCars();
	}
	
	@GetMapping("/getOneCar/{id}")
	public CarResponse getCarById(@PathVariable int id) {
		return carService.getCarResponseById(id);
	}
	
	@DeleteMapping("/delete-car/{id}")
	public void deleteCar(@PathVariable Integer id) {
		carService.deleteCar(id);
	}
	
	@PutMapping("/update-car/{id}")
	public CarResponse updateCarById(@PathVariable Integer id,@RequestBody CarRequest carRequest) {
		return carService.updateCarById(id,carRequest);
	}
	
}
