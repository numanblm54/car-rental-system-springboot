package com.numan.Ornek3.Services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.stereotype.Service;
import com.numan.Ornek3.Models.Car;
import com.numan.Ornek3.Models.Customer;
import com.numan.Ornek3.Models.DailyRentalPrice;
import com.numan.Ornek3.Models.DriversLicenseTypes;
import com.numan.Ornek3.Models.MyException;
import com.numan.Ornek3.Models.RentalRecord;
import com.numan.Ornek3.Models.VehicleTypes;
import com.numan.Ornek3.Repositories.RentalRecordRepository;

@Service
public class RentalService {
	
	private RentalRecordRepository rentalRecordRepository;
	private CarService carService;
	private CustomerService customerService;
	private DailyRentalPriceService dailyRentalPriceService;
	
	public RentalService(RentalRecordRepository rentalRecordRepository,
			CarService carService,
			CustomerService customerService,
			DailyRentalPriceService dailyRentalPriceService) {
		this.rentalRecordRepository=rentalRecordRepository;
		this.carService=carService;
		this.customerService=customerService;
		this.dailyRentalPriceService=dailyRentalPriceService;
	}
	
	public RentalRecord GetRentalRecordById(Integer id) {
		return rentalRecordRepository.findById(id)
				.orElseThrow(() -> new MyException("The record wasn't found.")); 
	}
	
	public List<RentalRecord> GetAllRecords(){
		return rentalRecordRepository.findAll();
	}
	
	public RentalRecord StartRental(Car car, Customer customer) {
		
//		Optional<Car> carOptional = carRepository.findById(car.getId());
//
//		if (carOptional.isEmpty()) {
//		    throw new MyException("The car wasn't found.");
		
		carService.getCarById(car.getId());
		customerService.getCustomerById(customer.getId());
		
		dailyRentalPriceService.getDailyRentalPriceByCarIdAndIsItCurrentTrue(car.getId());
			
		

		    if (customer.getDriversLicenseType() == DriversLicenseTypes.A) {

		        if (car.getVehicleType() == VehicleTypes.Car || car.getVehicleType() == VehicleTypes.Truck) {
		            throw new MyException("A person who has a type A driver's license cannot drive a car or a truck.");
		        }

		    } else if (customer.getDriversLicenseType() == DriversLicenseTypes.B) {

		        if (car.getVehicleType() == VehicleTypes.Motorcycle || car.getVehicleType() == VehicleTypes.Truck) {
		        	throw new MyException("Aperson who has a type B driver's license cannot drive a motorcycle or a truck.");
		        }

		    } else if (customer.getDriversLicenseType() == DriversLicenseTypes.C) {

		        if (car.getVehicleType() == VehicleTypes.Motorcycle ) {
		        	throw new MyException("Aperson who has a type C driver's license cannot drive a motorcycle.");
		            
		        }
		    }
		
		
		
		DailyRentalPrice priceList = dailyRentalPriceService.getPriceListDailyRentalPrice(car.getId());
		
        RentalRecord rentalRecord = new RentalRecord();
        rentalRecord.setCar(car);
        rentalRecord.setCustomer(customer);
        rentalRecord.setStartingKm(car.getKm());
        rentalRecord.setStartingRentalDate(LocalDateTime.now());
        rentalRecord.setPriceList(priceList.getPrice());
      
        car.setIsItActive(false);
        
       return  rentalRecordRepository.save(rentalRecord);
	}
	
	public void EndRental(Integer id, Integer finishKm) {
		

		RentalRecord rrecord=GetRentalRecordById(id);
		
		if(finishKm<rrecord.getStartingKm()) {
			throw new MyException("The ending kilometre cannot be less than starting kilometre");
		}
		
		rrecord.setEndingKm(finishKm);
	    Car car = rrecord.getCar();
	    car.setKm(finishKm);
	    car.setIsItActive(true);
		rrecord.setEndingRentalDate(LocalDateTime.now());
		Long days= ChronoUnit.DAYS.between(
		        rrecord.getStartingRentalDate(),
		        rrecord.getEndingRentalDate()
		        )+1;
		rrecord.setTotalRentalPrice(rrecord.getPriceList().multiply(BigDecimal.valueOf(days)));

		
		rentalRecordRepository.save(rrecord);
	}
	
	
	

}
