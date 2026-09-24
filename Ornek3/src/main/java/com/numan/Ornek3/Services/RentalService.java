package com.numan.Ornek3.Services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import org.springframework.stereotype.Service;
import com.numan.Ornek3.Models.dto.domain.RentalRecordDTO;
import com.numan.Ornek3.Models.entity.Car;
import com.numan.Ornek3.Models.entity.Customer;
import com.numan.Ornek3.Models.entity.DailyRentalPrice;
import com.numan.Ornek3.Models.entity.RentalRecord;
import com.numan.Ornek3.Repositories.RentalRecordRepository;
import com.numan.Ornek3.mapper.RentalRecordMapper;
import com.numan.Ornek3.Models.DriversLicenseTypes;
import com.numan.Ornek3.Models.MyException;
import com.numan.Ornek3.Models.VehicleTypes;

@Service
public class RentalService {
	
	private final RentalRecordRepository rentalRecordRepository;
	private final CarService carService;
	private final CustomerService customerService;
	private final DailyRentalPriceService dailyRentalPriceService;
	private final RentalRecordMapper rentalRecordMapper;
	
	public RentalService(RentalRecordRepository rentalRecordRepository,
			CarService carService,
			CustomerService customerService,
			DailyRentalPriceService dailyRentalPriceService,
			RentalRecordMapper rentalRecordMapper) {
		this.rentalRecordRepository = rentalRecordRepository;
		this.carService = carService;
		this.customerService = customerService;
		this.dailyRentalPriceService = dailyRentalPriceService;
		this.rentalRecordMapper = rentalRecordMapper;
	}
	
	
	public RentalRecord getRentalRecordById(Integer id) {
		return rentalRecordRepository.findById(id)
				.orElseThrow(() -> new MyException("The record wasn't found.")); 
	}
	
	
	public RentalRecordDTO getRentalRecordDTOById(Integer id) {
		var rentalRecord = getRentalRecordById(id);
		var recordDTO = rentalRecordMapper.mapToRentalRecordDTO(rentalRecord);
		return recordDTO;
	}
	
	
	public List<RentalRecordDTO> getAllRecords(){
		List<RentalRecord> recordList=rentalRecordRepository.findAll();
		var recordDTOList = rentalRecordMapper.mapRentalRecordListToRentalRecordDTOList(recordList);
		return recordDTOList;		
	}
	
	
	public List<RentalRecordDTO> getRentalRecordByCustomerId(Integer id) {
	    List<RentalRecord> recordList = rentalRecordRepository.findByCustomerId(id);
	    if (recordList.isEmpty()) {
	        throw new MyException("The records weren't found.");
	    }
	    
	    var recordDTOList = rentalRecordMapper.mapRentalRecordListToRentalRecordDTOList(recordList);
	    return recordDTOList;
	}
	
	
	public RentalRecordDTO startRental(Integer carId, Integer customerId) {
		Car car=carService.getCarById(carId);
		Customer customer=customerService.getCustomerById(customerId);
		DailyRentalPrice price=dailyRentalPriceService.getCurrentPrice(carId);
		
		if(car.getIsItActive()==false) {
			throw new MyException("The car isn't active for rental.");
		}
		
		if(price==null) {
			throw new MyException("There is no current price for this vehicle");
		}
	
		if (customer.getDriversLicenseType() == DriversLicenseTypes.A) {

			if (car.getVehicleType() == VehicleTypes.Car || car.getVehicleType() == VehicleTypes.Truck) {
		            throw new MyException("A person who has a type A driver's license cannot drive a car or a truck.");
		    }
		} 
		    
		else if (customer.getDriversLicenseType() == DriversLicenseTypes.B) {

			if (car.getVehicleType() == VehicleTypes.Motorcycle || car.getVehicleType() == VehicleTypes.Truck) {
		        	throw new MyException("A person who has a type B driver's license cannot drive a motorcycle or a truck.");
			}
		} 
		
		else if (customer.getDriversLicenseType() == DriversLicenseTypes.C) {

			if (car.getVehicleType() == VehicleTypes.Motorcycle ) {
		        	throw new MyException("A person who has a type C driver's license cannot drive a motorcycle.");    
			}
		}
		
        RentalRecord rentalRecord = new RentalRecord();
        rentalRecord.setCar(car);
        rentalRecord.setCustomer(customer);
        rentalRecord.setDailyRentalPrice(price);
        rentalRecord.setStartingKm(car.getKm());
        rentalRecord.setStartingRentalDate(LocalDateTime.now());
        car.setIsItActive(false);
        rentalRecordRepository.save(rentalRecord);
        
        var recordDTO = rentalRecordMapper.mapToRentalRecordDTO(rentalRecord);
        return recordDTO;
	}
	
	
	public RentalRecordDTO endRental(Integer id, Integer finishKm) {
		RentalRecord rentalRecord=getRentalRecordById(id);
		if(finishKm<rentalRecord.getStartingKm()) {
			throw new MyException("The ending kilometer cannot be less than starting kilometer.");
		}
		
		rentalRecord.setEndingKm(finishKm);
	    Car car = rentalRecord.getCar();
	    car.setKm(finishKm);
	    car.setIsItActive(true);
	    rentalRecord.setEndingRentalDate(LocalDateTime.now());
		Long days= ChronoUnit.DAYS.between(
				rentalRecord.getStartingRentalDate(),
				rentalRecord.getEndingRentalDate()
		        )+1;
		rentalRecord.setTotalRentalPrice(rentalRecord.getDailyRentalPrice().getPrice().multiply(BigDecimal.valueOf(days)));
		rentalRecordRepository.save(rentalRecord);
		
		var recordDTO = rentalRecordMapper.mapToRentalRecordDTO(rentalRecord);
		return recordDTO;
	}
}