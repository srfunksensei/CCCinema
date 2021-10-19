package com.mb.dto;

import java.util.List;

public class AvailableSeatsForScreening {
	
	private long screeningId;
	
	private List<SeatDto> availableSeats;
	
	public AvailableSeatsForScreening(long screeningId, List<SeatDto> availableSeats){
		this.screeningId = screeningId;
		this.availableSeats = availableSeats;
	}

	public long getScreeningId() {
		return screeningId;
	}

	public void setScreeningId(long screeningId) {
		this.screeningId = screeningId;
	}

	public List<SeatDto> getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(List<SeatDto> availableSeats) {
		this.availableSeats = availableSeats;
	}
}
