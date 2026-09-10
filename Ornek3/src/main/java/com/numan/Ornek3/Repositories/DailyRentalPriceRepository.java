package com.numan.Ornek3.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.numan.Ornek3.Models.DailyRentalPrice;

@Repository
public interface DailyRentalPriceRepository extends JpaRepository<DailyRentalPrice, Integer>{
	 // DailyRentalPrice findByCarIdAndIsItCurrent(Integer carId,Boolean isItCurrent);
	 DailyRentalPrice findByCarIdAndIsItCurrentTrue(Integer carId);
}
