package com.numan.Ornek3.mapper;

import java.util.List;
import org.springframework.stereotype.Component;
import com.numan.Ornek3.Models.dto.domain.RentalRecordDTO;
import com.numan.Ornek3.Models.dto.response.CustomerRentalResponse;
import com.numan.Ornek3.Models.dto.response.EndingRentalResponse;
import com.numan.Ornek3.Models.dto.response.RentalResponseWithCustomer;
import com.numan.Ornek3.Models.dto.response.StartingRentalResponse;
import com.numan.Ornek3.Models.entity.RentalRecord;

@Component
public class RentalRecordMapper {
	
	private final CustomerMapper customerMapper;
	private final CarMapper carMapper;
	private final DailyRentalPriceMapper dailyRentalPriceMapper;
	
	public RentalRecordMapper(CustomerMapper customerMapper,
			CarMapper carMapper,
			DailyRentalPriceMapper dailyRentalPriceMapper) {
		this.customerMapper = customerMapper;
		this.carMapper = carMapper;
		this.dailyRentalPriceMapper = dailyRentalPriceMapper;
	}
	
	
	public RentalRecordDTO mapToRentalRecordDTO(RentalRecord rentalRecord) {
	    return RentalRecordDTO.builder()
	            .id(rentalRecord.getId())
	            .startingKm(rentalRecord.getStartingKm())
	            .endingKm(rentalRecord.getEndingKm())
	            .startingRentalDate(rentalRecord.getStartingRentalDate())
	            .endingRentalDate(rentalRecord.getEndingRentalDate())
	            .totalRentalPrice(rentalRecord.getTotalRentalPrice())
	            .customer(customerMapper.mapToCustomerDTO(rentalRecord.getCustomer()))
	            .car(carMapper.mapCarToCarDto(rentalRecord.getCar()))
	            .dailyRentalPrice(dailyRentalPriceMapper.mapToDailyRentalPriceDTO(rentalRecord.getDailyRentalPrice()))
	            .build();
	}
	
	
	public List<RentalRecordDTO> mapRentalRecordListToRentalRecordDTOList(List<RentalRecord> rentalRecordList){
		
	    return rentalRecordList.stream()
	            .map(this::mapToRentalRecordDTO)
	            .toList();
	}
	
	
	public RentalRecordDTO mapToRentalRecordDTOWithoutCustomer(RentalRecord rentalRecord) {
	    return RentalRecordDTO.builder()
	            .id(rentalRecord.getId())
	            .startingKm(rentalRecord.getStartingKm())
	            .endingKm(rentalRecord.getEndingKm())
	            .startingRentalDate(rentalRecord.getStartingRentalDate())
	            .endingRentalDate(rentalRecord.getEndingRentalDate())
	            .totalRentalPrice(rentalRecord.getTotalRentalPrice())
	            .car(carMapper.mapCarToCarDto(rentalRecord.getCar()))
	            .dailyRentalPrice(dailyRentalPriceMapper.mapToDailyRentalPriceDTO(rentalRecord.getDailyRentalPrice()))
	            .build();
	}
	

	public List<RentalRecordDTO>  mapToRentalRecordDTOListWithoutCustomer(List<RentalRecord> rentalRecordList){
	    return rentalRecordList.stream()
	            .map(this::mapToRentalRecordDTOWithoutCustomer)
	            .toList();
	}
	
	
	public EndingRentalResponse mapRentalRecordDTOToEndingRentalResponse( RentalRecordDTO rentalRecordDTO) {
	    return EndingRentalResponse.builder()
	    		.startingKm(rentalRecordDTO.getStartingKm())
	    		.endingKm(rentalRecordDTO.getEndingKm())
	    		.startingRentalDate(rentalRecordDTO.getStartingRentalDate())
	    		.endingRentalDate(rentalRecordDTO.getEndingRentalDate())
	    		.car(carMapper.mapCarDTOToCarResponse(rentalRecordDTO.getCar()))
	    		.customer(customerMapper.mapToCustomerResponse(rentalRecordDTO.getCustomer()))
	    		.dailyRentalPrice(dailyRentalPriceMapper.mapDailyRentalPriceDTOToResponseWithoutCar(rentalRecordDTO.getDailyRentalPrice()))
	    		.totalRentalPrice(rentalRecordDTO.getTotalRentalPrice())  		
	    		.build(); 
	}
	
	
	public List<EndingRentalResponse> mapRentalRecordDTOListToEndingRentalResponseList(
	        List<RentalRecordDTO> rentalRecordDTOList) {
	    return rentalRecordDTOList.stream()
	            .map(this::mapRentalRecordDTOToEndingRentalResponse)
	            .toList();
	}
	
	
	public EndingRentalResponse mapRentalRecordDTOToEndingRentalResponseWithoutCustomer( RentalRecordDTO rentalRecordDTO) {
	    return EndingRentalResponse.builder()
	    		.startingKm(rentalRecordDTO.getStartingKm())
	    		.endingKm(rentalRecordDTO.getEndingKm())
	    		.startingRentalDate(rentalRecordDTO.getStartingRentalDate())
	    		.endingRentalDate(rentalRecordDTO.getEndingRentalDate())
	    		.car(carMapper.mapCarDTOToCarResponse(rentalRecordDTO.getCar()))
	    		.dailyRentalPrice(dailyRentalPriceMapper.mapDailyRentalPriceDTOToResponseWithoutCar(rentalRecordDTO.getDailyRentalPrice()))
	    		.totalRentalPrice(rentalRecordDTO.getTotalRentalPrice())  		
	    		.build(); 
	}
	
	
	public List<EndingRentalResponse> mapRentalRecordDTOListToEndingRentalResponseListWithoutCustomer(
	        List<RentalRecordDTO> rentalRecordDTOList) {
	    return rentalRecordDTOList.stream()
	            .map(this::mapRentalRecordDTOToEndingRentalResponseWithoutCustomer)
	            .toList();
	}
	
	public StartingRentalResponse mapRentalRecordDTOToStartingRentalResponse( RentalRecordDTO rentalRecordDTO) {
	    return StartingRentalResponse.builder()
	    		.startingKm(rentalRecordDTO.getStartingKm())
	    		.startingRentalDate(rentalRecordDTO.getStartingRentalDate())
	    		.car(carMapper.mapCarDTOToCarResponse(rentalRecordDTO.getCar()))
	    		.customer(customerMapper.mapToCustomerResponse(rentalRecordDTO.getCustomer()))
	    		.dailyRentalPrice(dailyRentalPriceMapper.mapDailyRentalPriceDTOToResponse(rentalRecordDTO.getDailyRentalPrice()))	
	    		.build(); 
	}
	
	
	public CustomerRentalResponse mapToCustomerRentalResponse(
	        RentalRecordDTO rentalRecordDTO) {

	    return CustomerRentalResponse.builder()
	            .startingKm(rentalRecordDTO.getStartingKm())
	            .endingKm(rentalRecordDTO.getEndingKm())
	            .startingRentalDate(rentalRecordDTO.getStartingRentalDate())
	            .endingRentalDate(rentalRecordDTO.getEndingRentalDate())
	            .totalRentalPrice(rentalRecordDTO.getTotalRentalPrice())
	            .car(
	                carMapper.mapCarDTOToCarResponse(
	                    rentalRecordDTO.getCar()
	                )
	            )
	            .dailyRentalPrice(
	                dailyRentalPriceMapper
	                    .mapDailyRentalPriceDTOToResponse(
	                        rentalRecordDTO.getDailyRentalPrice()
	                    )
	            )
	            .build();
	}
	
	
	public List<CustomerRentalResponse> mapToCustomerRentalResponseList(
	        List<RentalRecordDTO> rentalRecordDTOList) {

	    return rentalRecordDTOList.stream()
	            .map(this::mapToCustomerRentalResponse)
	            .toList();
	}
	
	public RentalResponseWithCustomer mapToRentalResponseWithCustomer(List<RentalRecordDTO> recordDTOList) {
		
		return RentalResponseWithCustomer.builder()
				.customer(customerMapper.mapToCustomerResponse(recordDTOList.get(0).getCustomer()))
				.recordList(mapRentalRecordDTOListToEndingRentalResponseListWithoutCustomer(recordDTOList))
				.build();
	}
}