package dto;

import javax.persistence.Column;

import model.ClinicAdministrator;

public class ClinicAdministratorDTO {

	private Long id;
	private String password;
	private String username;

	
	
	public ClinicAdministratorDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ClinicAdministratorDTO(ClinicAdministrator ca) {
		super();
		this.password = ca.getPassword();
		this.username = ca.getUsername();
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

	
}
