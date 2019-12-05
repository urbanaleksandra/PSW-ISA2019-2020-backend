package com.dto;

import com.model.MedicalStaff;

public class NurseDTO extends MedicalStaffDTO {

	public NurseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NurseDTO(Long id, String username, String password, String firstName, String lastName) {
		super(id, username, password, firstName, lastName);
		// TODO Auto-generated constructor stub
	}

	public NurseDTO(MedicalStaff medicalStaff) {
		super(medicalStaff);
		// TODO Auto-generated constructor stub
	}

}
