package com.numan.Ornek3.mapper;

import org.springframework.stereotype.Component;
import com.numan.Ornek3.Models.dto.domain.DailyRentalPriceDTO;
import com.numan.Ornek3.Models.dto.response.DailyRentalPriceResponse;
import com.numan.Ornek3.Models.entity.DailyRentalPrice;

@Component
public class DailyRentalPriceMapper {
	
	private final CarMapper carMapper;
	
	public DailyRentalPriceMapper(CarMapper carMapper) {
		this.carMapper = carMapper;
	}
	
	
	public DailyRentalPriceDTO mapToDailyRentalPriceDTO(DailyRentalPrice dailyRentalPrice) {
		return DailyRentalPriceDTO.builder()
				.id(dailyRentalPrice.getId())
				.price(dailyRentalPrice.getPrice())
				.car(carMapper.mapCarToCarDto(dailyRentalPrice.getCar()))
				.build();
	}
	
	
	public DailyRentalPriceResponse mapToDailyRentalPriceResponse(DailyRentalPrice dailyRentalPrice) {
	    return DailyRentalPriceResponse.builder()
	            .car(carMapper.mapCarToCarResponse(dailyRentalPrice.getCar()))
	            .price(dailyRentalPrice.getPrice())
	            .build();
	}
	
	
	public DailyRentalPriceResponse mapDailyRentalPriceDTOToResponse(DailyRentalPriceDTO dailyRentalPriceDTO) {
	    return DailyRentalPriceResponse.builder()
	            .car(carMapper.mapCarDTOToCarResponse(dailyRentalPriceDTO.getCar()))
	            .price(dailyRentalPriceDTO.getPrice())
	            .build();
	}
	
	
	public DailyRentalPriceResponse mapDailyRentalPriceDTOToResponseWithoutCar(DailyRentalPriceDTO dailyRentalPriceDTO) {
	    return DailyRentalPriceResponse.builder()
	            .price(dailyRentalPriceDTO.getPrice())
	            .build();
	}
}