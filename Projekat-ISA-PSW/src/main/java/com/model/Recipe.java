package com.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class Recipe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private boolean authenticated;
	@Column(nullable = false)
	private String description;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Drug drug;
	
	@OneToOne(optional=true)
	private Appointment appointment;

	@OneToOne(fetch = FetchType.LAZY)
	private Nurse nurse;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public boolean isAuthenticated() {
		return authenticated;
	}
	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public Drug getDrug() {
		return drug;
	}

	public Appointment getAppointment() {
		return appointment;
	}

	public void setDrug(Drug drug) {
		this.drug = drug;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}
}
