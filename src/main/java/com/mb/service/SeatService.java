package com.mb.service;

import com.mb.dto.AvailableSeatsForScreening;

public interface SeatService {
	
	AvailableSeatsForScreening getSeats(long screeningId);
	
	String bookSeat(long screeningId, String seat, String username);

}
