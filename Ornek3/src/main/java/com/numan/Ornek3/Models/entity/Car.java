package com.numan.Ornek3.Models.entity;

import com.numan.Ornek3.Models.VehicleTypes;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Car {
	
  
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	private String name;
	private Integer model;
	private Integer km;
	@Enumerated(EnumType.STRING)
	private VehicleTypes vehicleType;
	private Boolean isItActive;
	
}
