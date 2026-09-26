package com.samaritan.prescriber_service.Repositories;

import org.springframework.data.repository.CrudRepository;

import com.samaritan.prescriber_service.Entities.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long>{
 
}
