package com.dto;

import com.model.Appointment;

public class AppointmentDTO {
  
	private Long id;
	
	private DoctorDTO doctor;
	//private HospitalRoomDTO hospitalRoom;
	//private MedicalRecordDTO medicalRecord;
	//private RecipeDTO recipe;


	private String date;
	
	private String description;

	private String type ;

	private long duration;

	private Long roomID;

	private String patient;

	private String doctorUsername;
  
  private String info;

	private ClinicDTO clinic;

	public ClinicDTO getClinicDTO() {
		return clinic;
	}

	public void setClinicDTO(ClinicDTO clinicDTO) {
		this.clinic = clinicDTO;
  }
		
	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;

	}

	public String getDoctorUsername() {
		return doctorUsername;
	}

	public void setDoctorUsername(String doctorUsername) {
		this.doctorUsername = doctorUsername;
	}

	public String getDate() {
		return date;
	}

	public Long getRoomID() {
		return roomID;
	}

	public void setRoomID(Long roomID) {
		this.roomID = roomID;
	}

	public AppointmentDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public AppointmentDTO(Appointment appointment) {
		super();
		this.date=appointment.getDate();
		this.description=appointment.getDescription();
		this.type=appointment.getType();
		this.duration=appointment.getDuration();
		this.patient=appointment.getPatient();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPatient() {
		return patient;
	}

	public void setPatient(String patient) {
		this.patient = patient;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public DoctorDTO getDoctor() {
		return doctor;
	}

	public void setDoctor(DoctorDTO doctor) {
		this.doctor = doctor;

	}
}
