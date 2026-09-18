package com.numan.Ornek3;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.intThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.numan.Ornek3.Models.Car;
import com.numan.Ornek3.Models.CarRequest;
import com.numan.Ornek3.Models.CarResponse;
import com.numan.Ornek3.Models.CustomerRequest;
import com.numan.Ornek3.Models.CustomerResponse;
import com.numan.Ornek3.Models.DriversLicenseTypes;
import com.numan.Ornek3.Models.EndingRentalResponse;
import com.numan.Ornek3.Models.MyException;
import com.numan.Ornek3.Models.VehicleTypes;
import com.numan.Ornek3.Services.CarService;
import com.numan.Ornek3.Services.CustomerService;
import com.numan.Ornek3.Services.RentalService;

@SpringBootTest(classes= {Ornek3Application.class})
class Ornek3ApplicationTests {
	
	@Autowired
	private CarService carService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private RentalService rentalService;
	
	@Test
	void contextLoads() {
	}
	@Test 
	public void testgetCarById() {
		Car car = carService.getCarById(1);
		assertNotNull(car);
		System.out.println("Car name is " + car.getName());
	}
	
	@Test 
	public void testAddCustomer() {
		
		CustomerRequest request=new CustomerRequest();
		request.setName("Ahmet");
		request.setSurName("Yılmaz");
		request.setAge(25);
		request.setNationalCardNo("12345678913");
		request.setDriversLicenseType(DriversLicenseTypes.B);
		
		MyException exception = assertThrows(
			    MyException.class,
			    () -> customerService.addCustomer(request)
			);

		System.out.println("Error: " + exception.getMessage());

	}
	
	@Test
	public void testAddCar() {
		CarRequest request=new CarRequest();
		
		request.setKm(8000);
		request.setModel(1889);
		request.setName("Ferrai");
		request.setVehicleType(VehicleTypes.Car);
		
		MyException excepiton=assertThrows(MyException.class,() -> carService.addCar(request));
		
		System.out.println("Error: "+ excepiton.getMessage());
	}
	
	@Test
	public void testAddCarSucces() {
		CarRequest request=new CarRequest();
		
		request.setKm(8000);
		request.setModel(1990);
		request.setName("Ferrai");
		request.setVehicleType(VehicleTypes.Car);
		
		CarResponse car = carService.addCar(request);
		assertNotNull(car);
		System.out.println(car.getName());
	}
	
	@Test
	public void testStartRental() {
		
		MyException exception=assertThrows(MyException.class, () -> rentalService.startRental(7,1));
		System.out.println("Error: "+ exception.getMessage());
	}
	
	@Test
	public void testGetAllCars() {
		
		List<CarResponse> carList=carService.getAllCars();
		
		System.out.println("Araç sayısı: " + carList.size());
		for(CarResponse car: carList) {
			
			System.out.println("The car's name: "+ car.getName());
		}
	}
	
	@Test
	public void testGetAllCustomers() {
		List<CustomerResponse> customerList=customerService.getAllCustomers();
		for(CustomerResponse response:customerList) {
			System.out.print(response.getName()+" ");
			System.out.print(response.getSurName()+" ");
			System.out.print(response.getAge()+" ");
			System.out.println();
		}
		
	}
	
	@Test
	public void testGetCustomerWithRentals() {
		
		CustomerResponse customer=customerService.getCustomerWithRentals(1);
		System.out.print(customer.getName()+" ");
		System.out.print(customer.getSurName()+" ");
		System.out.print(customer.getAge()+" ");
		System.out.println();
		for(int i=0; i<customer.getRentalRecordsList().size();i++) {
			EndingRentalResponse rental =(EndingRentalResponse) customer.getRentalRecordsList().get(i);
			System.out.println(rental.getCar().getName());
		}
	}
	
	@Test
	public void testGetCustomerWithCars() {
		CustomerResponse customer=customerService.getCustomerWithCars(1);
		System.out.print(customer.getName()+" ");
		System.out.print(customer.getSurName()+" ");
		System.out.print(customer.getAge()+" ");
		System.out.println();
		for(int i=0;i<customer.getCarsList().size();i++){
			CarResponse car=customer.getCarsList().get(i);
			System.out.println("Car name: "+ car.getName());
			System.out.println("Car model: "+ car.getModel());
		}
	}
}
	

