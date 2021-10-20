package com.mb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class AvailableSeatsForScreening {
	
	private long screeningId;
	private List<SeatDto> availableSeats;
}
