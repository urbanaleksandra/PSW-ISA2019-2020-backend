package com.dto;

import com.model.HolidayRequest;

public class HolidayRequestDTO {
	private long id;
	private ClinicAdministratorDTO administratorDTO;

	public long getId() {
		return id;
	} 
	public void setId(long id) {
		this.id = id;
	}
	public ClinicAdministratorDTO getAdministratorDTO() {
		return administratorDTO;
	}
	public void setAdministratorDTO(ClinicAdministratorDTO administratorDTO) {
		this.administratorDTO = administratorDTO;
	}

	

	public HolidayRequestDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public HolidayRequestDTO(HolidayRequest holidayRequest) {
		super();
		this.id = holidayRequest.getId();

	}
	
}
