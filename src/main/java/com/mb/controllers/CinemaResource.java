package com.mb.controllers;

import com.mb.dto.AvailableSeatsForScreening;
import com.mb.dto.ReserveDto;
import com.mb.dto.ScreeningDto;
import com.mb.service.ScreeningService;
import com.mb.service.SeatService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
public class CinemaResource {

	private final ScreeningService screeningService;
	private final SeatService seatService;
	
	@GetMapping("/")
	public String index() {
		return "Welcome to Cinema!";
	}

	@GetMapping(value = "/upcoming")
	public ResponseEntity<List<ScreeningDto>> getUpcomingMovies() {
		return ResponseEntity.ok(screeningService.getUpcoming(new Date()));
	}
	
	@GetMapping(value = "/{screening_id}/seats")
	public ResponseEntity<AvailableSeatsForScreening> getSeatsForScreening(@PathVariable("screening_id") final String screeningId){
		return ResponseEntity.ok(seatService.getSeats(screeningId));
	}
	
	@PostMapping(value = "/{screening_id}/{seat}")
	public ResponseEntity<String> bookSeat(@PathVariable("screening_id") final String screeningId, @RequestBody final ReserveDto reservation){
		return ResponseEntity.ok(seatService.bookSeat(screeningId, reservation));
	}
}
