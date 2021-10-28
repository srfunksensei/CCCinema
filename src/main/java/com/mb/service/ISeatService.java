package com.mb.service;

import com.mb.dto.ScreeningSeatsDto;
import com.mb.dto.ReserveDto;
import com.mb.dto.SeatReservationResultDto;

public interface ISeatService {
	
	ScreeningSeatsDto getSeats(final String screeningId);
	
	SeatReservationResultDto bookSeat(final String screeningId, final ReserveDto reservation);

}
