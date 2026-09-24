package com.numan.Ornek3.Services;

import java.math.BigDecimal;
import org.springframework.stereotype.Service;
import com.numan.Ornek3.Repositories.DailyRentalPriceRepository;
import com.numan.Ornek3.mapper.DailyRentalPriceMapper;
import com.numan.Ornek3.Models.MyException;
import com.numan.Ornek3.Models.dto.domain.DailyRentalPriceDTO;
import com.numan.Ornek3.Models.entity.Car;
import com.numan.Ornek3.Models.entity.DailyRentalPrice;

@Service
public class DailyRentalPriceService {
	
	private final DailyRentalPriceRepository dailyRentalPriceRepository;
	private final CarService carService;
	private final DailyRentalPriceMapper dailyRentalPriceMapper;
	
	public DailyRentalPriceService(DailyRentalPriceRepository dailyRentalPriceRepository,
			CarService carService,
			DailyRentalPriceMapper dailyRentalPriceMapper) {
		this.carService = carService;
		this.dailyRentalPriceRepository = dailyRentalPriceRepository;
		this.dailyRentalPriceMapper = dailyRentalPriceMapper;
	}
	
	
	public DailyRentalPrice getCurrentPrice(Integer carId) {

	    DailyRentalPrice price = dailyRentalPriceRepository.findByCarIdAndIsItCurrentTrue(carId);       
	    return price;
	}
	
	
	public DailyRentalPriceDTO getCurrentPriceDTO(Integer carId) {
		DailyRentalPrice price = getCurrentPrice(carId);
		if(price==null) {
			throw new MyException("There is no current price for this vehicle");
		}
		var priceDTO = dailyRentalPriceMapper.mapToDailyRentalPriceDTO(price);
		return priceDTO;
	}
	
	
	public DailyRentalPriceDTO addDailyRentalPrice(Integer carId, BigDecimal dailyPrice) {
		Car car= carService.getCarById(carId);
		DailyRentalPrice oldPrice=getCurrentPrice(carId);
		if(oldPrice!=null) {
		oldPrice.setIsItCurrent(false);
		dailyRentalPriceRepository.save(oldPrice);
		}
		
		DailyRentalPrice newPrice=new DailyRentalPrice();
		newPrice.setCar(car);
		newPrice.setPrice(dailyPrice);
		newPrice.setIsItCurrent(true);
		dailyRentalPriceRepository.save(newPrice);
		
		var priceDTO = dailyRentalPriceMapper.mapToDailyRentalPriceDTO(newPrice);
		return priceDTO;
	}
}