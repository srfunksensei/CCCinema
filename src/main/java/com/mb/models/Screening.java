package com.mb.models;

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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Auditorium getAuditorium() {
		return auditorium;
	}

	public void setAuditorium(Auditorium auditorium) {
		this.auditorium = auditorium;
	}

	public Set<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(Set<Reservation> reservations) {
		this.reservations = reservations;
	}

	public Set<SeatReserved> getSeatsReserved() {
		return seatsReserved;
	}

	public void setSeatsReserved(Set<SeatReserved> seatsReserved) {
		this.seatsReserved = seatsReserved;
	}
}
