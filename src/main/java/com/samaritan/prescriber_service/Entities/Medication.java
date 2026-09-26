package com.samaritan.prescriber_service.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "medication", catalog = "medication")
public class Medication {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long medication_id;

	@Column(name="medication_name")
	private String medication_name;
	
	@Column(name="medication_description")
	private String medication_description;

	public Medication() {
		super();
	}

	
	
	public Medication(long medication_id, String medication_name, String medication_description) {
		super();
		this.medication_id = medication_id;
		this.medication_name = medication_name;
		this.medication_description = medication_description;
	}



	public long getMedication_id() {
		return medication_id;
	}

	public void setMedication_id(long medication_id) {
		this.medication_id = medication_id;
	}

	public String getMedication_name() {
		return medication_name;
	}

	public void setMedication_name(String medication_name) {
		this.medication_name = medication_name;
	}

	public String getMedication_description() {
		return medication_description;
	}

	public void setMedication_description(String medication_description) {
		this.medication_description = medication_description;
	}



	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Medication [medication_id=").append(medication_id).append(", medication_name=")
				.append(medication_name).append(", medication_description=").append(medication_description).append("]");
		return builder.toString();
	}
	
}
