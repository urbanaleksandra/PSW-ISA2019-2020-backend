package com.dto;

import com.model.HospitalRoom;

public class HospitalRoomDTO {

	private Long id;
	private ClinicDTO clinicDTO;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public ClinicDTO getClinicDTO() {
		return clinicDTO;
	}
	public void setClinicDTO(ClinicDTO clinicDTO) {
		this.clinicDTO = clinicDTO;
	}
	public HospitalRoomDTO(Long id, ClinicDTO clinicDTO) {
		super();
		this.id = id;
		this.clinicDTO = clinicDTO;
	}
	public HospitalRoomDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public HospitalRoomDTO(HospitalRoom hospitalRoom) {
		super();
		this.id = hospitalRoom.getId();
		this.clinicDTO = new ClinicDTO(hospitalRoom.getClinic());
	}
}
