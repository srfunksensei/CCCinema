package com.mb.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class SeatDto {

	private String auditorium;
	private String row;
	private String num;
	private boolean reserved;
}
