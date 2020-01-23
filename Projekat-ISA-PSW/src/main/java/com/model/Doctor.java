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

	@OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Surgery> surgeries = new HashSet<Surgery>();
	
	@OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Appointment> appointments = new HashSet<Appointment>();



	@ManyToOne(cascade = CascadeType.ALL)
	private Clinic clinic;
	
	@Column(name = "review", nullable = false)
	private int review;



	public Clinic getClinic() {
		return clinic;
	}

	public void setClinic(Clinic clinic) {
		this.clinic = clinic;
	}
}
