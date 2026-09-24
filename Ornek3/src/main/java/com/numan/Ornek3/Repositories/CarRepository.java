package com.numan.Ornek3.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.numan.Ornek3.Models.entity.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

}