package com.dto;

import com.model.HolidayRequest;

import java.util.Date;

public class HolidayRequestDTO {
	private long id;
	private String dateStart;
	private String dateEnd;
	private String username;

	public HolidayRequestDTO() {
	}

	public long getId() {
		return id;
	}

	public String getDateStart() {
		return dateStart;
	}

	public String getDateEnd() {
		return dateEnd;
	}

	public String getUsername() {
		return username;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}

	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "HolidayRequestDTO{" +
				"id=" + id +
				", dateStart='" + dateStart + '\'' +
				", dateEnd='" + dateEnd + '\'' +
				", username='" + username + '\'' +
				'}';
	}
}
