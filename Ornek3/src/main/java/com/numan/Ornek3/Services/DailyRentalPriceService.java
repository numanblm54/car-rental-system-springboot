package com.numan.Ornek3.Services;

import java.math.BigDecimal;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.numan.Ornek3.Repositories.DailyRentalPriceRepository;
import com.numan.Ornek3.exception.ErrorCode;
import com.numan.Ornek3.exception.ResourceNotFoundException;
import com.numan.Ornek3.mapper.DailyRentalPriceMapper;
import com.numan.Ornek3.Models.dto.domain.DailyRentalPriceDTO;
import com.numan.Ornek3.Models.entity.Car;
import com.numan.Ornek3.Models.entity.DailyRentalPrice;

@Service
public class DailyRentalPriceService {
	
	private final DailyRentalPriceRepository dailyRentalPriceRepository;
	
	private final DailyRentalPriceMapper dailyRentalPriceMapper;
	
	public DailyRentalPriceService(DailyRentalPriceRepository dailyRentalPriceRepository,
			DailyRentalPriceMapper dailyRentalPriceMapper) {
		
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
			throw new ResourceNotFoundException(ErrorCode.RESOURCE_NOT_FOUND, "There is no current price for this vehicle");
		}
		var priceDTO = dailyRentalPriceMapper.mapToDailyRentalPriceDTO(price);
		return priceDTO;
	}
	
	

	
	public void passiveToDailyRentalPrice(Integer carId) {
		DailyRentalPrice price = getCurrentPrice(carId);
		price.setIsItCurrent(false);
		dailyRentalPriceRepository.save(price);
	}

	public void saveDailyRentalPrice(DailyRentalPrice dailyRentalPrice) {
		dailyRentalPriceRepository.save(dailyRentalPrice);
	}
	public DailyRentalPrice createNewDailyrentalPrice(Car car, BigDecimal dailyPrice) {
		DailyRentalPrice newPrice=new DailyRentalPrice();
		newPrice.setCar(car);
		newPrice.setPrice(dailyPrice);
		newPrice.setIsItCurrent(true);
		dailyRentalPriceRepository.save(newPrice);
		return newPrice;
	}
	
	public void importDailyRentalPrices(MultipartFile file) {
		
	}
}