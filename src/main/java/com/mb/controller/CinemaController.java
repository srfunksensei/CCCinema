package com.mb.controller;

import com.mb.dto.ReserveDto;
import com.mb.dto.ScreeningDto;
import com.mb.dto.ScreeningSeatsDto;
import com.mb.dto.SeatReservationResultDto;
import com.mb.service.IScreeningService;
import com.mb.service.ISeatService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/api/screening")
public class CinemaController {

	private final IScreeningService screeningService;
	private final ISeatService seatService;

	public CinemaController(@Qualifier("screeningServiceMapStruct") final IScreeningService screeningService,
							@Qualifier("seatServiceMapStruct") final ISeatService seatService) {
		this.screeningService = screeningService;
		this.seatService = seatService;
	}

	@GetMapping("/upcoming")
	public ResponseEntity<List<ScreeningDto>> getUpcomingMovies() {
		return ResponseEntity.ok(screeningService.getUpcoming(new Timestamp(System.currentTimeMillis())));
	}
	
	@GetMapping("/{screening_id}/seats")
	public ResponseEntity<ScreeningSeatsDto> getSeatsForScreening(@PathVariable("screening_id") final String screeningId){
		return ResponseEntity.ok(seatService.getSeats(screeningId));
	}
	
	@PostMapping("/{screening_id}/seats")
	public ResponseEntity<SeatReservationResultDto> bookSeat(@PathVariable("screening_id") final String screeningId, @RequestBody final ReserveDto reservation){
		return ResponseEntity.ok(seatService.bookSeat(screeningId, reservation));
	}
}
