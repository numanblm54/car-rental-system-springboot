package com.numan.Ornek3.Services;

import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.numan.Ornek3.Repositories.CarRepository;
import com.numan.Ornek3.exception.AppException;
import com.numan.Ornek3.exception.AppExceptionB;
import com.numan.Ornek3.exception.ErrorCode;
import com.numan.Ornek3.exception.ErrorCodeB;
import com.numan.Ornek3.exception.MyException;
import com.numan.Ornek3.mapper.CarMapper;
import com.numan.Ornek3.Models.dto.domain.CarDTO;
import com.numan.Ornek3.Models.entity.Car;

@Service
public class CarService {
	
	private final CarRepository carRepository;
	private final CarMapper carMapper;
	
	public CarService(CarRepository carRepository,CarMapper carMapper) {
		this.carRepository = carRepository;
		this.carMapper = carMapper;
	}
	
	
	public CarDTO addCar(CarDTO carDTO) {
		if(carDTO.getModel()<1990) {
			throw new AppException(ErrorCode.RESOURCE_NOT_FOUND);
		}
		
		var car = carMapper.mapCarDTOToCar(carDTO);
		car.setIsItActive(true);
		carRepository.save(car);
		
		var savedCarDTO = carMapper.mapCarToCarDto(car);
		return savedCarDTO;
	}
	
	
	public List<CarDTO> getAllCars() {
		List<Car> carList=carRepository.findAll();
		var carDTOList = carMapper.mapToCarDTOList(carList);
		return carDTOList;
	}
	
	
	 public Car getCarById(Integer id) {
		    return carRepository.findById(id)
		            .orElseThrow(() -> new AppExceptionB(ErrorCodeB.RESOURCE_NOT_FOUND, "The car wasn't found."));
	 }
	 
	 
	 public CarDTO getCarDTOById(Integer id) {
		var carDTO = carMapper.mapCarToCarDto(getCarById(id));
		return carDTO;
	 }
	 
	 
	 public void deleteCar(Integer id) {
		 Car car = getCarById(id);
		 carRepository.delete(car);
	 }
	 
	 
	 public CarDTO updateCarById(Integer id, CarDTO carDTO) {
		 Car oldCar= getCarById(id);
		 BeanUtils.copyProperties(carDTO, oldCar,"id");
		 carRepository.save(oldCar);
		 var savedCarDTO = carMapper.mapCarToCarDto(oldCar);
		 return savedCarDTO;
	 }
}