package com.dto;

import com.model.MedicalRecord;

public class MedicalRecordDTO {
	private Long id;
	private PatientDTO patient;

	public MedicalRecordDTO(MedicalRecord md) {
		super();
		this.id = md.getId();
		this.patient = new PatientDTO(md.getPatient());
	}
	
	public MedicalRecordDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MedicalRecordDTO(Long id, PatientDTO patient) {
		super();
		this.id = id;
		this.patient = patient;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public PatientDTO getPatient() {
		return patient;
	}
	public void setPatient(PatientDTO patient) {
		this.patient = patient;
	}
	
}
