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
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private ClinicalCenter clinicalCenter;
	
	@Column(name = "username", nullable = false)
	private String username;
	@Column(name = "password", nullable = false)
	private String password;
	
	
	
	
}
