package com.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
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

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private AppointmentType type2;
	
	@OneToOne(optional=true)
	 private Recipe recipe;

	@Column(name = "patient", nullable = true)
	private String patient;

	@Column(name = "doctorUsername", nullable = true)
	private String doctorUsername;
	
	@Column(name = "date", nullable = false)
	private String date;
	
	@Column(name = "description", nullable = false)
	private String description;
	
	@Column(name = "type", nullable = false)
	private String type ;
	
	@Column(name = "duration", nullable = false)
	private long duration;


	@Column(name = "finished", nullable = false)
	private boolean finished;


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

	@Override
	public String toString() {
		return "Course [id=" + id + "]";
	}

	public AppointmentType getType2() {
		return type2;
	}

	public void setType2(AppointmentType type2) {
		this.type2 = type2;
	}
}
