package com.mb.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "SCREENING", 
		uniqueConstraints = @UniqueConstraint(columnNames = { "movie_id", "auditorium_id", "start" }))
@Getter
@Setter
public class Screening {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private Date start;

	@ManyToOne
	@JoinColumn(name = "movie_id")
	private Movie movie;

	@ManyToOne
	@JoinColumn(name = "auditorium_id")
	private Auditorium auditorium;

	@OneToMany(mappedBy = "screening", cascade = CascadeType.ALL)
	private Set<Reservation> reservations;

	@OneToMany(mappedBy = "screening", cascade = CascadeType.ALL)
	private Set<SeatReserved> seatsReserved;
}
