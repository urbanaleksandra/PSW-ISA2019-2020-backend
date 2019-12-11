package com.model;

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
	
	@Column(name = "review", nullable = false)
	private int review;

}
