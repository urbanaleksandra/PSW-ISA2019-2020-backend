package com.model;

import com.fasterxml.jackson.annotation.*;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
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

	@JsonBackReference
	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	private Set<Surgery> surgeries = new HashSet<Surgery>();
	
	@OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<Appointment> appointments = new HashSet<Appointment>();



	@ManyToOne(cascade = CascadeType.REFRESH)
	private Clinic clinic;
	
	@Column(name = "review", nullable = false)
	private int review;

	public int getReview() {
		return review;
	}

	public void setReview(int review) {
		this.review = review;
	}

	public Clinic getClinic() {
		return clinic;
	}

	public void setClinic(Clinic clinic) {
		this.clinic = clinic;
	}

	public Set<Surgery> getSurgeries() {
		return surgeries;
	}

	public void setSurgeries(Set<Surgery> surgeries) {
		this.surgeries = surgeries;
	}

	public Set<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(Set<Appointment> appointments) {
		this.appointments = appointments;
	}



	@Override
	public String toString() {
		return "Doctor{" +
				"surgeries=" + surgeries +
				", appointments=" + appointments +
				", clinic=" + clinic +
				", review=" + review +
				'}';
	}
}
