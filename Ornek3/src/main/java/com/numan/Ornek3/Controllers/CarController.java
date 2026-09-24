package com.numan.Ornek3.controllers;

import org.springframework.web.bind.annotation.RestController;
import com.numan.Ornek3.Services.CarService;
import com.numan.Ornek3.mapper.CarMapper;
import com.numan.Ornek3.Models.dto.request.CarRequest;
import com.numan.Ornek3.Models.dto.response.CarResponse;
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
	
	private final CarService carService;
	private final CarMapper carMapper;
	
	public CarController(CarService carService, CarMapper carMapper) {
		this.carService = carService;
		this.carMapper = carMapper;
	}
	
	
	@PostMapping("/add-Car")
	public CarResponse addCar(@Valid @RequestBody CarRequest carRequest) {
		var carDto = carMapper.mapCarRequestToCarDTO(carRequest);
		var savedCarDTO = carService.addCar(carDto);
		var carResponse = carMapper.mapCarDTOToCarResponse(savedCarDTO);
		return carResponse;
	}
	
	
	@GetMapping("/getAllCars")
	public List<CarResponse> getAllCars() {
		var carDTOList = carService.getAllCars();
		var carResponseList = carMapper.mapCarDTOListToCarResponseList(carDTOList);
		return carResponseList;
	}
	
	
	@GetMapping("/getOneCar/{id}")
	public CarResponse getCarById(@PathVariable int id) {
		var carDTO = carService.getCarDTOById(id);
		var carResponse = carMapper.mapCarDTOToCarResponse(carDTO);
		return carResponse;
	}
	
	
	@DeleteMapping("/delete-car/{id}")
	public void deleteCar(@PathVariable Integer id) {
		carService.deleteCar(id);
	}
	
	
	@PutMapping("/update-car/{id}")
	public CarResponse updateCarById(@PathVariable Integer id,@RequestBody CarRequest carRequest) {
		var carDTO = carMapper.mapCarRequestToCarDTO(carRequest);
		var savedCarDTO = carService.updateCarById(id,carDTO);
		var carResponse = carMapper.mapCarDTOToCarResponse(savedCarDTO);
		return carResponse;
	}
}