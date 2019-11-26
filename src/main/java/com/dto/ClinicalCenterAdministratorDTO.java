package com.dto;

import com.model.ClinicalCenterAdministrator;

public class ClinicalCenterAdministratorDTO {

	private Long id;
	private ClinicalCenterDTO centerDTO;
	private String username;
	private String password;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public ClinicalCenterDTO getCenterDTO() {
		return centerDTO;
	}
	public void setCenterDTO(ClinicalCenterDTO centerDTO) {
		this.centerDTO = centerDTO;
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
	public ClinicalCenterAdministratorDTO(Long id, ClinicalCenterDTO centerDTO, String username, String password) {
		super();
		this.id = id;
		this.centerDTO = centerDTO;
		this.username = username;
		this.password = password;
	}
	public ClinicalCenterAdministratorDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ClinicalCenterAdministratorDTO(ClinicalCenterAdministrator administrator) {
		super();
		this.id = administrator.getId();
		this.centerDTO = new ClinicalCenterDTO(administrator.getClinicalCenter());
		this.username = administrator.getUsername();
		this.password = administrator.getPassword();
	}
}
