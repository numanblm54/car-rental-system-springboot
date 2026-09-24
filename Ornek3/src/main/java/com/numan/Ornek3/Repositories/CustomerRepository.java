package com.numan.Ornek3.Repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.numan.Ornek3.Models.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	
	List<Customer> findByName(String name);
	List<Customer> findBySurName(String surName);
	Customer findByNationalCardNo(String nationalCardNo);
}