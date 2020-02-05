package com.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.fasterxml.jackson.annotation.JsonIgnore;


import java.util.Objects;

import javax.persistence.*;
import javax.persistence.Version;
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "appointments")
public class Appointment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;


	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Doctor doctor;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private HospitalRoom hospitalRoom;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private MedicalRecord medicalRecord;

	@JsonBackReference
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Recipe recipe;

	@ManyToOne
	@JsonIgnore
	private Diagnosis diagnosis;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private AppointmentType type2;
	
	@Column(name = "patient", nullable = true)
	private String patient;

	@Column(name = "doctorUsername", nullable = true)
	private String doctorUsername;
	
	@Column(name = "date", nullable = false)
	private String date;
	
	@Column(name = "description", nullable = false)
	private String description;
	
	@Column(name = "type", nullable = true)
	private String type ;
	
	@Column(name = "duration", nullable = false)
	private long duration;


	@Column(name = "finished", nullable = false)
	private boolean finished;

	@Column(name = "info", nullable = true)
	private String info;


	public Diagnosis getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(Diagnosis diagnosis) {
		this.diagnosis = diagnosis;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getDoctorUsername() {
		return doctorUsername;
	}

	public void setDoctorUsername(String doctorUsername) {
		this.doctorUsername = doctorUsername;
	}

	public Doctor getDoctor() {
		return doctor;
	}
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	public HospitalRoom getHospitalRoom() {
		return hospitalRoom;
	}
	public void setHospitalRoom(HospitalRoom hospitalRoom) {
		this.hospitalRoom = hospitalRoom;
	}
	public MedicalRecord getMedicalRecord() {
		return medicalRecord;
	}
	public void setMedicalRecord(MedicalRecord medicalRecord) {
		this.medicalRecord = medicalRecord;
	}
	public Recipe getRecipe() {
		return recipe;
	}
	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public long getDuration() {
		return duration;
	}
	public void setDuration(long duration) {
		this.duration = duration;
	}

	public String getPatient() {
		return patient;
	}

	public void setPatient(String patient) {
		this.patient = patient;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public Appointment(Long id, String patient, String date, String description, long duration) {
		this.id = id;
		this.patient = patient;
		this.date = date;
		this.description = description;
		this.duration = duration;
	}

	public Appointment() {
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Appointment c = (Appointment) o;
		if (c.id == null || id == null) {
			return false;
		}
		return Objects.equals(id, c.id);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}

	public AppointmentType getType2() {
		return type2;
	}

	public void setType2(AppointmentType type2) {
		this.type2 = type2;
	}

	@Override
	public String toString() {
		return "Appointment{" +
				"id=" + id +
				'}';
	}
}
