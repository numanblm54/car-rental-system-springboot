package com.numan.Ornek3.Services;

import java.math.BigDecimal;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.numan.Ornek3.Models.Car;
import com.numan.Ornek3.Models.DailyRentalPrice;
import com.numan.Ornek3.Models.DailyRentalPriceResponse;
import com.numan.Ornek3.Models.MyException;
import com.numan.Ornek3.Repositories.DailyRentalPriceRepository;

@Service
public class DailyRentalPriceService {
	private DailyRentalPriceRepository dailyRentalPriceRepository;
	private CarService carService;
	
	public DailyRentalPriceService(DailyRentalPriceRepository dailyRentalPriceRepository,CarService carService) {
		this.carService=carService;
		this.dailyRentalPriceRepository=dailyRentalPriceRepository;
	}
	
	public DailyRentalPrice getCurrentPrice(Integer id) {

	    DailyRentalPrice price =
	            dailyRentalPriceRepository.findByCarIdAndIsItCurrentTrue(id);

	    if (price == null) {
	        throw new MyException("There is no current price for this vehicle");
	    }

	    return price;
	}
	

	
	public DailyRentalPrice getCurrentPriceOrNull(Integer id) {

	    return dailyRentalPriceRepository
	            .findByCarIdAndIsItCurrentTrue(id);
	}
	

	
	public DailyRentalPriceResponse AddDailyRentalPrice(Integer carId, BigDecimal dailyPrice) {
		Car car= carService.getCarById(carId);
		DailyRentalPrice dailyRentalPrice=new DailyRentalPrice();
		DailyRentalPrice oldDailyRentalPrice=getCurrentPriceOrNull(carId);
		if(oldDailyRentalPrice!=null) {
			oldDailyRentalPrice.setIsItCurrent(false);
			dailyRentalPriceRepository.save(oldDailyRentalPrice);
		}
		
		dailyRentalPrice.setCarId(car.getId());
		dailyRentalPrice.setCarNameString(car.getName());
		dailyRentalPrice.setVehicleTypes(car.getVehicleType());
		dailyRentalPrice.setPrice(dailyPrice);
		dailyRentalPrice.setIsItCurrent(true);
		dailyRentalPriceRepository.save(dailyRentalPrice);

		DailyRentalPriceResponse  priceResponse=new DailyRentalPriceResponse();
		BeanUtils.copyProperties(dailyRentalPrice, priceResponse);
		return priceResponse;
	}
	

	

}
