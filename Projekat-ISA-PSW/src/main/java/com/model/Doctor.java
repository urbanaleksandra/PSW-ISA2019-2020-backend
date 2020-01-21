package com.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@DiscriminatorValue("1")
public class Doctor extends MedicalStaff {

	public Doctor(Set<Surgery> surgeries, Set<Appointment> appointments, int review) {
		super();
		this.surgeries = surgeries;
		this.appointments = appointments;
		this.review = review;
	}

	public Doctor() {
	}

	@OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Surgery> surgeries = new HashSet<Surgery>();
	
	@OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Appointment> appointments = new HashSet<Appointment>();


	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	private Clinic clinic;
	
	@Column(name = "review", nullable = false)
	private int review;

	@Column(name = "rating", nullable = false)
	private int rating;

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public Clinic getClinic() {
		return clinic;
	}

	public void setClinic(Clinic clinic) {
		this.clinic = clinic;
	}
}
