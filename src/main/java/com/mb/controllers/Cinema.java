package com.mb.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mb.dto.AvailableSeatsForScreening;
import com.mb.dto.ScreeningDto;
import com.mb.service.ScreeningService;
import com.mb.service.SeatService;

@RestController
public class Cinema {

	@Autowired
	private ScreeningService screeningService;
	
	@Autowired
	private SeatService seatService;
	
	@RequestMapping("/")
	public String index() {
		return "Welcome to Cinema!";
	}

	// Allows a user to view the upcoming movies
	@RequestMapping(value = "/upcoming", method = RequestMethod.GET)
	@ResponseBody
	public List<ScreeningDto> getUpcomingMovies() {
		return screeningService.getUpcoming(new Date());
	}
	
	// A way for the user to book a seat of their choice for a selected movie.
	@RequestMapping(value = "/{screening_id}/seats", method = RequestMethod.GET)
	@ResponseBody
	public AvailableSeatsForScreening getSeatsForScreening(@PathVariable("screening_id") long screeningId){
		return seatService.getSeats(screeningId);
	}
	
	@RequestMapping(value = "/{screening_id}/{seat}", method = RequestMethod.POST)
	@ResponseBody
	public String bookSeat(@PathVariable("screening_id") Long screeningId, @PathVariable("seat") String seat, @RequestBody String username){
		return seatService.bookSeat(screeningId, seat, username);
	}
}
