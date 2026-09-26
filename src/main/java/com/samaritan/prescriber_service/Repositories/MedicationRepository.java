package com.samaritan.prescriber_service.Repositories;

import org.springframework.data.repository.CrudRepository;

import com.samaritan.prescriber_service.Entities.Medication;

public interface MedicationRepository extends CrudRepository<Medication, Long>{
 
}
