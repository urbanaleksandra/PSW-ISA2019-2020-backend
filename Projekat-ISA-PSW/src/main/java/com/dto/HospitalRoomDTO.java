package com.dto;

import com.model.HospitalRoom;

public class HospitalRoomDTO {

	private Long id;
	private ClinicDTO clinicDTO;
	private String name;
	private int number;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

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


	public HospitalRoomDTO(String name, int number) {
		this.name = name;
		this.number = number;
	}

	public HospitalRoomDTO(Long id, ClinicDTO clinicDTO, String name, int number) {
		this.id = id;
		this.clinicDTO = clinicDTO;
		this.name = name;
		this.number = number;
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
