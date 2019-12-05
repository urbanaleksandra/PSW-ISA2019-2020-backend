package com.dto;

import com.model.HolidayRequest;

public class HolidayRequestDTO {
	private long id;
	private ClinicAdministratorDTO administratorDTO;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public HolidayRequestDTO(long id, ClinicAdministratorDTO administratorDTO, String username, String password,
			String firstName, String lastName) {
		super();
		this.id = id;
		this.administratorDTO = administratorDTO;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	public HolidayRequestDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public HolidayRequestDTO(HolidayRequest holidayRequest) {
		super();
		this.id = holidayRequest.getId();
		this.administratorDTO = new ClinicAdministratorDTO(holidayRequest.getClinicAdministrator());
		this.username = holidayRequest.getUsername();
		this.password = holidayRequest.getPassword();
		this.firstName = holidayRequest.getFirstName();
		this.lastName = holidayRequest.getLastName();
	}
	
}
