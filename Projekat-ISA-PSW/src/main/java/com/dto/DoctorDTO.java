package com.dto;

import com.model.MedicalStaff;

public class DoctorDTO extends MedicalStaffDTO {

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
