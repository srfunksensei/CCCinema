package com.mb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ScreeningDto {
	
	private String title;
	private String director;
	private String description;
	private int duration;
	private String auditorium;
	private String screeningId;
	private Timestamp start;
}
