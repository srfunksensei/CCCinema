package com.mb.service;

import com.mb.dto.ScreeningSeats;
import com.mb.dto.ReserveDto;
import com.mb.dto.SeatReservationResultDto;

public interface ISeatService {
	
	ScreeningSeats getSeats(final String screeningId);
	
	SeatReservationResultDto bookSeat(final String screeningId, final ReserveDto reservation);

}
