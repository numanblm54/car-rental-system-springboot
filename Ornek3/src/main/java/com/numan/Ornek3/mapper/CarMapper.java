package com.numan.Ornek3.mapper;

import java.util.List;
import org.springframework.stereotype.Component;
import com.numan.Ornek3.Models.dto.domain.CarDTO;
import com.numan.Ornek3.Models.dto.request.CarRequest;
import com.numan.Ornek3.Models.dto.response.CarResponse;
import com.numan.Ornek3.Models.entity.Car;

@Component
public class CarMapper {
	
	public CarDTO mapCarToCarDto(Car car) {
		return CarDTO.builder()
				.id(car.getId())
				.name(car.getName())
				.model(car.getModel())
				.km(car.getKm())
				.vehicleType(car.getVehicleType())
				.build();
	}
	
	
	public List<CarDTO> mapToCarDTOList(List<Car> carList){
	    return carList.stream()
	            .map(this::mapCarToCarDto)
	            .toList();
	}
	
	
	public CarResponse mapCarToCarResponse(Car car) {
		return CarResponse.builder()
				.name(car.getName())
				.model(car.getModel())
				.vehicleType(car.getVehicleType())
				.build();
	}
	
	
	public CarResponse mapCarDTOToCarResponse(CarDTO carDTO) {
		return CarResponse.builder()
				.name(carDTO.getName())
				.model(carDTO.getModel())
				.vehicleType(carDTO.getVehicleType())
				.build();
	}
	
	public List<CarResponse> mapCarDTOListToCarResponseList(List<CarDTO> carDTOList){
	    return carDTOList.stream()
	            .map(this::mapCarDTOToCarResponse)
	            .toList();
	}
	 
	
	public CarDTO mapCarRequestToCarDTO(CarRequest carRequest) {
		return CarDTO.builder()
				.name(carRequest.getName())
				.model(carRequest.getModel())
				.km(carRequest.getKm())
				.vehicleType(carRequest.getVehicleType())
				.build();
	}
	
	
	public Car mapCarDTOToCar(CarDTO carDTO) {
		return Car.builder()
				.name(carDTO.getName())
				.model(carDTO.getModel())
				.km(carDTO.getKm())
				.vehicleType(carDTO.getVehicleType())
				.build();
	}
}