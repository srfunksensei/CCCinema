package com.mb.service;

import com.mb.dto.AvailableSeatsForScreening;

public interface SeatService {
	
	AvailableSeatsForScreening getSeats(final String screeningId);
	
	String bookSeat(final String screeningId, final String seat, final String username);

}
