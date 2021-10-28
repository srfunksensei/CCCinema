package com.mb.dto;

import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class ScreeningSeatsDto {
	
	private String screeningId;
	private Set<SeatDto> seats;
}
