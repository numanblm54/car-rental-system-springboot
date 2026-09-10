package com.numan.Ornek3.Controllers;

import java.math.BigDecimal;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.numan.Ornek3.Services.DailyRentalPriceService;

@RestController
public class DailyRentalPriceController {
	private DailyRentalPriceService dailyRentalPriceService;
	
	public DailyRentalPriceController(DailyRentalPriceService dailyRentalPriceService) {
		this.dailyRentalPriceService=dailyRentalPriceService;
	}
	
	
	@PostMapping("/add-dailyrentalprice/{carId}")
	public void AddDailyRentalPrice(@PathVariable Integer carId,@RequestParam BigDecimal dailyPrice) {
		dailyRentalPriceService.AddDailyRentalPrice(carId, dailyPrice);
		
	}

}
