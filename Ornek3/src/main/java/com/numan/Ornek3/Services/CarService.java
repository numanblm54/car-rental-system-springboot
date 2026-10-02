package com.numan.Ornek3.Services;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.numan.Ornek3.Repositories.CarRepository;
import com.numan.Ornek3.exception.BusinessRuleException;
import com.numan.Ornek3.exception.ErrorCode;
import com.numan.Ornek3.exception.ResourceNotFoundException;
import com.numan.Ornek3.mapper.CarMapper;
import com.numan.Ornek3.mapper.DailyRentalPriceMapper;
import com.numan.Ornek3.Models.dto.domain.CarDTO;
import com.numan.Ornek3.Models.entity.Car;
import com.numan.Ornek3.Models.entity.DailyRentalPrice;

@Service
public class CarService {
	
	private final CarRepository carRepository;
	private final CarMapper carMapper;
	private final DailyRentalPriceService dailyRentalPriceService;
	private final DailyRentalPriceMapper dailyRentalPriceMapper;
	
	
	public CarService(CarRepository carRepository,
			CarMapper carMapper,
			DailyRentalPriceService dailyRentalPriceService,
			DailyRentalPriceMapper dailyRentalPriceMapper) {
		this.carRepository = carRepository;
		this.carMapper = carMapper;
		this.dailyRentalPriceService = dailyRentalPriceService;
		this.dailyRentalPriceMapper = dailyRentalPriceMapper;
	}
	
	
	public CarDTO addCar(CarDTO carDTO) {
		if(carDTO.getModel()<1990) {
			throw new BusinessRuleException(ErrorCode.BUSSİNES_RULE_VIOLATION, "The car model cannot be less than 1990.");
		}
		
		var car = carMapper.mapCarDTOToCar(carDTO);
		car.setIsItActive(true);
		carRepository.save(car);
		
		var savedCarDTO = carMapper.mapCarToCarDto(car);
		return savedCarDTO;
	}
	
	
	public List<CarDTO> getAllCars() {
		List<Car> carList=carRepository.findAll();
		if(carList.isEmpty()) {
			throw new ResourceNotFoundException(ErrorCode.RESOURCE_NOT_FOUND, "There is no car.");
		}
		var carDTOList = carMapper.mapToCarDTOList(carList);
		return carDTOList;
	}
	
	
	 public Car getCarById(Integer id) {
		    return carRepository.findById(id)
		            .orElseThrow(() -> new ResourceNotFoundException(ErrorCode.RESOURCE_NOT_FOUND, "The car wasn't found."));
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
	 
	 public CarDTO addCurrentPrice(Integer carId, BigDecimal dailyPrice) {
		 Car car = getCarById(carId);
			DailyRentalPrice oldPrice=dailyRentalPriceService.getCurrentPrice(carId);
			if(oldPrice!=null) {
			oldPrice.setIsItCurrent(false);
			dailyRentalPriceService.saveDailyRentalPrice(oldPrice);
			}
			
			DailyRentalPrice dailyRentalPrice = dailyRentalPriceService.createNewDailyrentalPrice(car, dailyPrice);
			
			
			var carDTO = carMapper.mapCarToCarDtoWithPriceDto(car, dailyRentalPrice.getPrice());
			return carDTO;	
	 }
}