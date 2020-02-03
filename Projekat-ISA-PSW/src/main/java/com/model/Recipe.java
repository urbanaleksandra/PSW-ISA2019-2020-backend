package com.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Drug> drug = new HashSet<Drug>();
	
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

	public Appointment getAppointment() {
		return appointment;
	}

	public Set<Drug> getDrug() {
		return drug;
	}

	public void setDrug(Set<Drug> drug) {
		this.drug = drug;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

	public Nurse getNurse() {
		return nurse;
	}

	public void setNurse(Nurse nurse) {
		this.nurse = nurse;
	}

	@Override
	public String toString() {
		return "Recipe{" +
				"id=" + id +
				", authenticated=" + authenticated +
				", description='" + description + '\'' +
				", drug=" + drug +
				", appointment=" + appointment +
				", nurse=" + nurse +
				'}';
	}
}
