package com.samaritan.prescriber_service;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrescriberController {
	private final CustomerRepository customerRepository;
	private final StoreRepository storeRepository;
	
	public PrescriberController(CustomerRepository customerRepository, StoreRepository storeRepository) {
		this.customerRepository = customerRepository;
		this.storeRepository = storeRepository;
	}
	
	// Get all customers
	@GetMapping("/customers")
	public Iterable<Customer> hello() {
		return customerRepository.findAll();
	}
	
	// Get customer 
	@GetMapping("/customer/{id}")
	public ResponseEntity<Customer> customerById(@PathVariable Long id) {
		Optional<Customer> medication = customerRepository.findById(id);
		return medication.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	// Create customer
	@PostMapping("/customer")
	@ResponseBody
	public ResponseEntity<?> createCustomer(@RequestParam("customer_name") String customer_name,
							   @RequestParam("customer_address") String customer_address,
							   @RequestParam("customer_phone") String customer_phone,
							   @RequestParam("customer_email") String customer_email,
							   @RequestParam("store_id") long store_id){
		Optional<Store> storeOpt = storeRepository.findById(store_id);
		
		if (storeOpt.isEmpty()) {
			return ResponseEntity.badRequest().body("Store not found for store_id: " + store_id);
		}
		
		Customer customer = new Customer(customer_name, customer_address, customer_phone, customer_email);
		Customer savedCustomer = customerRepository.save(customer);

		Store store = storeOpt.get();
        store.getCustomers().add(savedCustomer);
		storeRepository.save(store);
		
		return ResponseEntity.ok().build();
	}
}

