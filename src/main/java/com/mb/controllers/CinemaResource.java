package com.mb.controllers;

import com.mb.dto.ScreeningSeats;
import com.mb.dto.ReserveDto;
import com.mb.dto.ScreeningDto;
import com.mb.dto.SeatReservationResultDto;
import com.mb.service.IScreeningService;
import com.mb.service.ISeatService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@AllArgsConstructor
public class CinemaResource {

	private final IScreeningService screeningService;
	private final ISeatService seatService;
	
	@GetMapping("/")
	public String index() {
		return "Welcome to Cinema!";
	}

	@GetMapping(value = "/upcoming")
	public ResponseEntity<List<ScreeningDto>> getUpcomingMovies() {
		return ResponseEntity.ok(screeningService.getUpcoming(new Timestamp(System.currentTimeMillis())));
	}
	
	@GetMapping(value = "/{screening_id}/seats")
	public ResponseEntity<ScreeningSeats> getSeatsForScreening(@PathVariable("screening_id") final String screeningId){
		return ResponseEntity.ok(seatService.getSeats(screeningId));
	}
	
	@PostMapping(value = "/{screening_id}/{seat}")
	public ResponseEntity<SeatReservationResultDto> bookSeat(@PathVariable("screening_id") final String screeningId, @RequestBody final ReserveDto reservation){
		return ResponseEntity.ok(seatService.bookSeat(screeningId, reservation));
	}
}
