package com.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class HolidayRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;


	@Column(nullable = false)
	private String dateStart;
	@Column(nullable = false)
	private String dateEnd;

	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private MedicalStaff medicalStaff;
	

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public HolidayRequest() {
	}

	public String getDateStart() {
		return dateStart;
	}

	public String getDateEnd() {
		return dateEnd;
	}

	public MedicalStaff getMedicalStaff() {
		return medicalStaff;
	}

	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}

	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}

	public void setMedicalStaff(MedicalStaff medicalStaff) {
		this.medicalStaff = medicalStaff;
	}

	@Override
	public String toString() {
		return "HolidayRequest{" +
				"id=" + id +
				", dateStart='" + dateStart + '\'' +
				", dateEnd='" + dateEnd + '\'' +
				", medicalStaff=" + medicalStaff +
				'}';
	}
}
