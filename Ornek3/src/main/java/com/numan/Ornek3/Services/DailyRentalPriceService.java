package com.numan.Ornek3.Services;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.numan.Ornek3.Models.Car;
import com.numan.Ornek3.Models.DailyRentalPrice;
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
	
	public DailyRentalPrice getDailyRentalPriceByCarIdAndIsItCurrentTrue(Integer id) {
		
	 DailyRentalPrice price=dailyRentalPriceRepository.findByCarIdAndIsItCurrentTrue(id);
	 if(price==null) {
		 throw new MyException("There is no current price for this vehicle");
	 }
	 return price;
				
	}
	
	public void AddDailyRentalPrice(Integer carId, BigDecimal dailyPrice) {
		Car car= carService.getCarById(carId);
		
		DailyRentalPrice oldDailyRentalPrice=dailyRentalPriceRepository.findByCarIdAndIsItCurrentTrue(carId);

		if(oldDailyRentalPrice!=null) {
			oldDailyRentalPrice.setIsItCurrent(false);
			dailyRentalPriceRepository.save(oldDailyRentalPrice);
		}
		
		
		DailyRentalPrice dailyRentalPrice=new DailyRentalPrice();
		dailyRentalPrice.setCar(car);
		dailyRentalPrice.setIsItCurrent(true);
		dailyRentalPrice.setPrice(dailyPrice);
		
		dailyRentalPriceRepository.save(dailyRentalPrice);
	}
	
	public DailyRentalPrice getPriceListDailyRentalPrice(Integer carId) {
		return dailyRentalPriceRepository.findByCarIdAndIsItCurrentTrue(carId);
	}
	

}
