package com.mb.dto;

import java.util.Date;

public class ScreeningDto {
	
	private String title;
	
	private String director;
	
	private String description;
	
	private int duration;
	
	private String auditorium;

	private long screeningId;
	
	private Date start;

	public long getScreeningId() {
		return screeningId;
	}

	public void setScreeningId(long screeningId) {
		this.screeningId = screeningId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public String getAuditorium() {
		return auditorium;
	}

	public void setAuditorium(String auditorium) {
		this.auditorium = auditorium;
	}
}
