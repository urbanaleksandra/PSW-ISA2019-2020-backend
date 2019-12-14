package com.model;
import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class MedicalRecord {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonBackReference
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	 private Patient patient;
	
	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
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

	@OneToMany(mappedBy = "medicalRecord", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Surgery> surgeries = new HashSet<>();
	
	@OneToMany(mappedBy = "medicalRecord", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Appointment> appointments = new HashSet<>();

	@OneToMany(mappedBy = "medicalRecord", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<RequestAppointment> requestAppointments = new HashSet<>();

	public Set<RequestAppointment> getRequestAppointments() {
		return requestAppointments;
	}

	public void setRequestAppointments(Set<RequestAppointment> requestAppointments) {
		this.requestAppointments = requestAppointments;
	}

	public void addRequestAppointment(RequestAppointment ra){
		this.requestAppointments.add(ra);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "MedicalRecord{" +
				"id=" + id +
				", patient=" + patient +
				", surgeries=" + surgeries +
				", appointments=" + appointments +
				", requestAppointments=" + requestAppointments +
				'}';
	}
}
