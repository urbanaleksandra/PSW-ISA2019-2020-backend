package dto;

import model.Surgery;

public class SurgeryDTO {

	private Long id;
	private DoctorDTO doctorDTO;
	private HospitalRoomDTO hospitalRoomDTO;
	private MedicalRecordDTO medicalRecordDTO;
	private long date;
	private String description;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public DoctorDTO getDoctorDTO() {
		return doctorDTO;
	}
	public void setDoctorDTO(DoctorDTO doctorDTO) {
		this.doctorDTO = doctorDTO;
	}
	public HospitalRoomDTO getHospitalRoomDTO() {
		return hospitalRoomDTO;
	}
	public void setHospitalRoomDTO(HospitalRoomDTO hospitalRoomDTO) {
		this.hospitalRoomDTO = hospitalRoomDTO;
	}
	public MedicalRecordDTO getMedicalRecordDTO() {
		return medicalRecordDTO;
	}
	public void setMedicalRecordDTO(MedicalRecordDTO medicalRecordDTO) {
		this.medicalRecordDTO = medicalRecordDTO;
	}
	public long getDate() {
		return date;
	}
	public void setDate(long date) {
		this.date = date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public SurgeryDTO(Long id, DoctorDTO doctorDTO, HospitalRoomDTO hospitalRoomDTO, MedicalRecordDTO medicalRecordDTO,
			long date, String description) {
		super();
		this.id = id;
		this.doctorDTO = doctorDTO;
		this.hospitalRoomDTO = hospitalRoomDTO;
		this.medicalRecordDTO = medicalRecordDTO;
		this.date = date;
		this.description = description;
	}
	public SurgeryDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public SurgeryDTO(Surgery surgery) {
		super();
		this.id = surgery.getId();
		this.doctorDTO = new DoctorDTO(surgery.getDoctor());
		this.hospitalRoomDTO =new HospitalRoomDTO(surgery.getHospitalRoom());
		this.medicalRecordDTO =new  MedicalRecordDTO(surgery.getMedicalRecord());
		this.date = surgery.getDate();
		this.description = surgery.getDescription();
	}
	
}
