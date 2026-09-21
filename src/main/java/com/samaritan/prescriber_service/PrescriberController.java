package com.samaritan.prescriber_service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrescriberController {
	private final CustomerRepository customerRepository;
	
	public PrescriberController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
	
	@GetMapping("/hello")
	public String hello() {
		return "hello world";
	}
	
	// Create customer
	@PostMapping("/customer")
	@ResponseBody
	public void createCustomer(@RequestParam("customer_name") String customer_name,
							   @RequestParam("customer_address") String customer_address,
							   @RequestParam("customer_phone") String customer_phone,
							   @RequestParam("customer_email") String customer_email) {
		// TODO: Need to give customer a prescribed medication
		Customer customer = new Customer(0, customer_name, customer_address, customer_phone, customer_email);
		
        customerRepository.save(customer);
	}
}

