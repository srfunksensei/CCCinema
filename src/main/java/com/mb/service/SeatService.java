package com.mb.service;

import com.mb.dto.AvailableSeatsForScreening;
import com.mb.dto.ReserveDto;

public interface SeatService {
	
	AvailableSeatsForScreening getSeats(final String screeningId);
	
	String bookSeat(final String screeningId, final ReserveDto reservation);

}
