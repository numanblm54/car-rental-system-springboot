package com.numan.Ornek3.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.numan.Ornek3.Models.Car;

public interface CarRepository extends JpaRepository<Car, Integer> {

}
