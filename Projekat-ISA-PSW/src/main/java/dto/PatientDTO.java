package dto;

import javax.persistence.Column;

import model.Patient;

public class PatientDTO {

	private Long id;
	private ClinicalCenterDTO centerDTO;
	private MedicalRecordDTO medicalRecordDTO;
	
	private String username;
	private String password;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String adress;
	
	private String city;
	
	private String country;
	private int mobileNumber;
	private int jmbg;
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
	public MedicalRecordDTO getMedicalRecordDTO() {
		return medicalRecordDTO;
	}
	public void setMedicalRecordDTO(MedicalRecordDTO medicalRecordDTO) {
		this.medicalRecordDTO = medicalRecordDTO;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(int mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public int getJmbg() {
		return jmbg;
	}
	public void setJmbg(int jmbg) {
		this.jmbg = jmbg;
	}
	public PatientDTO(Long id, ClinicalCenterDTO centerDTO, MedicalRecordDTO medicalRecordDTO, String username,
			String password, String firstName, String lastName, String email, String adress, String city,
			String country, int mobileNumber, int jmbg) {
		super();
		this.id = id;
		this.centerDTO = centerDTO;
		this.medicalRecordDTO = medicalRecordDTO;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.adress = adress;
		this.city = city;
		this.country = country;
		this.mobileNumber = mobileNumber;
		this.jmbg = jmbg;
	}
	public PatientDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PatientDTO(Patient p) {
		super();
		this.id = p.getId();
		this.centerDTO = new ClinicalCenterDTO(p.getClinicalCenter());
		this.medicalRecordDTO = new MedicalRecordDTO(p.getRecord());
		this.username = p.getUsername();
		this.password = p.getPassword();
		this.firstName = p.getFirstName();
		this.lastName = p.getLastName();
		this.email = p.getEmail();
		this.adress = p.getAdress();
		this.city = p.getCity();
		this.country = p.getCountry();
		this.mobileNumber = p.getMobileNumber();
		this.jmbg = p.getJmbg();
	}
	
	
}
