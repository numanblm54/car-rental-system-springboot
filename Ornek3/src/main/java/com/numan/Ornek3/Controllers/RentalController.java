package com.numan.Ornek3.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.numan.Ornek3.Services.CarService;
import com.numan.Ornek3.Services.CustomerService;
import com.numan.Ornek3.Services.RentalService;
import com.numan.Ornek3.mapper.RentalRecordMapper;
import com.numan.Ornek3.Models.dto.request.RentalRequest;
import com.numan.Ornek3.Models.dto.response.EndingRentalResponse;
import com.numan.Ornek3.Models.dto.response.RentalResponseWithCustomer;
import com.numan.Ornek3.Models.dto.response.StartingRentalResponse;
import org.springframework.web.bind.annotation.PutMapping;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class RentalController {
	
	private final RentalService  rentalService;
	private final RentalRecordMapper rentalRecordMapper;

	public RentalController(RentalService rentalService,CarService carService,
			CustomerService customerService,
			RentalRecordMapper rentalRecordMapper) {
		this.rentalService = rentalService;
		this.rentalRecordMapper = rentalRecordMapper;
	}
	
	
	@GetMapping("/get-all-records")
	public List<EndingRentalResponse> getAllRecords(){
		var recordDTOList = rentalService.getAllRecords();
		var endingRentalRecordResponseList = rentalRecordMapper.mapRentalRecordDTOListToEndingRentalResponseList(recordDTOList);
		return endingRentalRecordResponseList;
	}
	
	
	@GetMapping("/get-record-byid/{id}")
	public EndingRentalResponse GetRecordById(@PathVariable Integer id){
		var recordDTO =  rentalService.getRentalRecordDTOById(id);
		var endingRentalResponse = rentalRecordMapper.mapRentalRecordDTOToEndingRentalResponse(recordDTO);
		return endingRentalResponse;
	}
	
	@GetMapping("/get-record-by-customerid/{id}")
	public RentalResponseWithCustomer GetRecordByCustomerId( @PathVariable("id") Integer customerId){
		var recordDTOList =  rentalService.getRentalRecordByCustomerId(customerId);
		var response = rentalRecordMapper.mapToRentalResponseWithCustomer(recordDTOList);
		return response;
	}
	
	@PostMapping("/start-rental")
	public StartingRentalResponse RentalStart(@RequestBody RentalRequest rentalRequest) {
		var recordDTO = rentalService.startRental(rentalRequest.getCarId(),rentalRequest.getCustomerId());
		var startingRentalResponse = rentalRecordMapper.mapRentalRecordDTOToStartingRentalResponse(recordDTO);
		return startingRentalResponse;
	}
	
	
	@PutMapping("/end-rental/{id}")
	public EndingRentalResponse EndRental(@PathVariable Integer id,@RequestParam Integer finishKm) {
		var recordDTO = rentalService.endRental(id,finishKm);
		var endingRentalResponse = rentalRecordMapper.mapRentalRecordDTOToEndingRentalResponse(recordDTO);
		return endingRentalResponse;
	}
}