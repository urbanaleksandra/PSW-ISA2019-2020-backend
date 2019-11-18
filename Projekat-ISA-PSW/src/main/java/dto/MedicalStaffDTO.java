package dto;

import model.MedicalStaff;

public class MedicalStaffDTO {

	private Long id;
	
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public MedicalStaffDTO(Long id, String username, String password, String firstName, String lastName) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	public MedicalStaffDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public MedicalStaffDTO(MedicalStaff medicalStaff) {
		this.id = medicalStaff.getId();
		this.username = medicalStaff.getUsername();
		this.password = medicalStaff.getPassword();
		this.firstName = medicalStaff.getFirstName();
		this.lastName = medicalStaff.getLastName();
	}
}
