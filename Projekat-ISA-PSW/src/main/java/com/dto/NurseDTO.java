package com.dto;

import com.model.MedicalStaff;

public class NurseDTO extends MedicalStaffDTO {


	private ClinicDTO clinicDTO;

	public ClinicDTO getClinicDTO() {
		return clinicDTO;
	}

	public void setClinicDTO(ClinicDTO clinicDTO) {
		this.clinicDTO = clinicDTO;
	}

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
