package com.mb.controller;

import com.mb.dto.ReserveDto;
import com.mb.dto.ScreeningDto;
import com.mb.dto.ScreeningSeatsDto;
import com.mb.dto.SeatReservationResultDto;
import com.mb.service.IScreeningService;
import com.mb.service.ISeatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.Timestamp;
import java.util.List;

public abstract class CinemaController {

    protected IScreeningService screeningService;
    protected ISeatService seatService;

    @GetMapping("/upcoming")
    public ResponseEntity<List<ScreeningDto>> getUpcomingMovies() {
        return ResponseBuilder.ok(screeningService.getUpcoming(new Timestamp(System.currentTimeMillis())));
    }

    @GetMapping("/{screening_id}/seats")
    public ResponseEntity<ScreeningSeatsDto> getSeatsForScreening(@PathVariable("screening_id") final String screeningId){
        return ResponseBuilder.ok(seatService.getSeats(screeningId));
    }

    @PostMapping("/{screening_id}/seats")
    public ResponseEntity<SeatReservationResultDto> bookSeat(@PathVariable("screening_id") final String screeningId, @RequestBody final ReserveDto reservation){
        return ResponseBuilder.ok(seatService.bookSeat(screeningId, reservation));
    }
}
