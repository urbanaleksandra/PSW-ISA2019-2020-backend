package com.dto;

import com.model.Appointment;

public class AppointmentDTO {
  
	private Long id;
	
	private DoctorDTO doctor;
	//private HospitalRoomDTO hospitalRoom;
	//private MedicalRecordDTO medicalRecord;
	//private RecipeDTO recipe;
	
	
	private long date;
	
	private String description;

	private String type ;

	private long duration;

	public long getDate() {
		return date;
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
}
