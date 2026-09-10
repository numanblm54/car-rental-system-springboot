package com.numan.Ornek3.Controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.numan.Ornek3.Models.Car;
import com.numan.Ornek3.Models.Customer;
import com.numan.Ornek3.Models.RentalRecord;
import com.numan.Ornek3.Models.RentalRequest;
import com.numan.Ornek3.Services.CarService;
import com.numan.Ornek3.Services.CustomerService;
import com.numan.Ornek3.Services.RentalService;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
public class RentalController {
	
	private RentalService  rentalService;
	private CarService carService;
	private CustomerService customerService;
	
	public RentalController(RentalService rentalService,CarService carService, CustomerService customerService) {
		this.rentalService=rentalService;
		this.carService=carService;
		this.customerService=customerService;
	}
	
	@GetMapping("/get-all-records")
	public List<RentalRecord> GetAllRecords(){
		return rentalService.GetAllRecords();
	}
	
	@GetMapping("/get-record-byid/{id}")
	public RentalRecord GetRecordById(@PathVariable Integer id){
		return rentalService.GetRentalRecordById(id);
	}
	
	@PostMapping("/start-rental")
	public RentalRecord RentalStart(@RequestBody RentalRequest rentalRequest) {
		Car car=carService.getCarById(rentalRequest.getCarId());
		Customer customer=customerService.getCustomerById(rentalRequest.getCustomerId());
		return rentalService.StartRental(car,customer);
	}
	
	@PutMapping("/end-rental/{id}")
	public void EndRental(@PathVariable Integer id,@RequestParam Integer finishKm) {
		rentalService.EndRental(id,finishKm);
		
	}
	
	
	

}
