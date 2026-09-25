package com.samaritan.prescriber_service;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
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
	private final MedicationRepository medicationRepository;
	private final JdbcTemplate jdbcTemplate;

	public PrescriberController(CustomerRepository customerRepository, 
								StoreRepository storeRepository,
								MedicationRepository medicationRepository,
								JdbcTemplate jdbcTemplate) {
		this.customerRepository = customerRepository;
		this.storeRepository = storeRepository;
		this.medicationRepository = medicationRepository;
	    this.jdbcTemplate = jdbcTemplate;
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
	
	// Prescribe medication
	@PostMapping("/prescribe")
	public ResponseEntity<?> prescribeMedication(@RequestParam("customer_id") long customer_id,
	                                              @RequestParam("medication_id") long medication_id) {
	    Optional<Customer> customerOpt = customerRepository.findById(customer_id);
	    if (customerOpt.isEmpty()) {
	        return ResponseEntity.badRequest().body("Customer not found for customer_id: " + customer_id);
	    }

	    Optional<Medication> medicationOpt = medicationRepository.findById(medication_id);
	    if (medicationOpt.isEmpty()) {
	        return ResponseEntity.badRequest().body("Medication not found for medication_id: " + medication_id);
	    }

	    jdbcTemplate.update(
	        "INSERT INTO customer_medication (customer_id, medication_id) VALUES (?, ?)",
	        customer_id, medication_id
	    );

	    return ResponseEntity.ok().build();
	}
	
			// View prescription details for chosen customer
}

