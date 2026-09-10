package com.numan.Ornek3.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.numan.Ornek3.Models.RentalRecord;
@Repository
public interface RentalRecordRepository extends JpaRepository<RentalRecord, Integer>{

}
