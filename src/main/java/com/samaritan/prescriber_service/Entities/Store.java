package com.samaritan.prescriber_service.Entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class Store {

	@Id
	private long store_id;

	@Column(name = "store_address")
	private String store_address;

	@Column(name = "store_phone")
	private String store_phone;

	@ManyToMany
	@JoinTable(
		name = "store_customer",
		joinColumns = @JoinColumn(name = "store_id"),
		inverseJoinColumns = @JoinColumn(name = "customer_id")
	)
	private Set<Customer> customers = new HashSet<>();

	public Store() {
		super();
	}

	public Store(long store_id, String store_address, String store_phone) {
		super();
		this.store_id = store_id;
		this.store_address = store_address;
		this.store_phone = store_phone;
	}

	public long getStore_id() {
		return store_id;
	}

	public void setStore_id(long store_id) {
		this.store_id = store_id;
	}

	public String getStore_address() {
		return store_address;
	}

	public void setStore_address(String store_address) {
		this.store_address = store_address;
	}

	public String getStore_phone() {
		return store_phone;
	}

	public void setStore_phone(String store_phone) {
		this.store_phone = store_phone;
	}

	public Set<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(Set<Customer> customers) {
		this.customers = customers;
	}

	@Override
	public String toString() {
		return "Store [store_id=" + store_id + ", store_address=" + store_address + ", store_phone=" + store_phone + "]";
	}
}