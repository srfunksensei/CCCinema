package com.mb.dto;

import lombok.*;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class ScreeningDto {
	
	private String title;
	private String director;
	private String description;
	private int duration;
	private String auditorium;
	private String screeningId;
	private Timestamp startingTime;
}
