package com.dto;

import com.model.MedicalStaff;

public class DoctorDTO extends MedicalStaffDTO {

	private ClinicDTO clinicDTO;

	private int review;

	public int getReview() {
		return review;
	}

	public void setReview(int review) {
		this.review = review;
	}

	public ClinicDTO getClinicDTO() {
		return clinicDTO;
	}

	public void setClinicDTO(ClinicDTO clinicDTO) {
		this.clinicDTO = clinicDTO;
	}

	public DoctorDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DoctorDTO(Long id, String username, String password, String firstName, String lastName) {
		super(id, username, password, firstName, lastName);
		// TODO Auto-generated constructor stub
	}

	public DoctorDTO(MedicalStaff medicalStaff) {
		super(medicalStaff);
		// TODO Auto-generated constructor stub
	}
 
	
	
}
