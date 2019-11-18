package model;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ClinicalCenterAdministrator {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public ClinicalCenter getClinicalCenter() {
		return clinicalCenter;
	}
	public void setClinicalCenter(ClinicalCenter clinicalCenter) {
		this.clinicalCenter = clinicalCenter;
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
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private ClinicalCenter clinicalCenter;
	
	@Column(name = "username", nullable = false)
	private String username;
	@Column(name = "password", nullable = false)
	private String password;
	
	
	
	
}
