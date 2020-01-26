package com.dto;

import com.model.Surgery;

public class SurgeryDTO {

	private Long id;
	private String date;
	private String description;
	private String patient;
	private String doctor;
	private int duration;

	public void setId(Long id) {
		this.id = id;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPatient(String patient) {
		this.patient = patient;
	}

	public Long getId() {
		return id;
	}

	public String getDate() {
		return date;
	}

	public String getDescription() {
		return description;
	}

	public String getPatient() {
		return patient;
	}

	public String getDoctor() {
		return doctor;
	}

	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}

	@Override
	public String toString() {
		return "SurgeryDTO{" +
				"id=" + id +
				", date='" + date + '\'' +
				", description='" + description + '\'' +
				", patient='" + patient + '\'' +
				", doctor='" + doctor + '\'' +
				'}';
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}
}
