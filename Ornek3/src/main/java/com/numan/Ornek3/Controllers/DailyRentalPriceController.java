package com.numan.Ornek3.controllers;

import java.math.BigDecimal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.numan.Ornek3.Models.dto.response.DailyRentalPriceResponse;
import com.numan.Ornek3.Services.DailyRentalPriceService;
import com.numan.Ornek3.mapper.DailyRentalPriceMapper;

@RestController
public class DailyRentalPriceController {
	
	private final DailyRentalPriceService dailyRentalPriceService;
	private final DailyRentalPriceMapper dailyRentalPriceMapper;
	
	public DailyRentalPriceController(DailyRentalPriceService dailyRentalPriceService,
			 DailyRentalPriceMapper dailyRentalPriceMapper) {
		this.dailyRentalPriceService = dailyRentalPriceService;
		this.dailyRentalPriceMapper = dailyRentalPriceMapper;
	}
	
	
//	@PostMapping("/add-dailyrentalprice/{carId}")
//	public DailyRentalPriceResponse addDailyRentalPrice(@PathVariable Integer carId,@RequestParam BigDecimal dailyPrice) {
//		var priceDTO = dailyRentalPriceService.addDailyRentalPrice(carId, dailyPrice);
//		var priceResponse = dailyRentalPriceMapper.mapDailyRentalPriceDTOToResponse(priceDTO);
//		return priceResponse;
//	}
	
	
	@GetMapping("/get-dailyrentalprice/{carId}")
	public DailyRentalPriceResponse getDailyRentalPriceByCarId(@PathVariable("carId") Integer carId) {
		var priceDTO = dailyRentalPriceService.getCurrentPriceDTO(carId);
		var priceResponse = dailyRentalPriceMapper.mapDailyRentalPriceDTOToResponse(priceDTO);
		return priceResponse;
	}
}