package com.mb.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SEAT_RESERVED")
public class SeatReserved {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "seat_id")
	private Seat seat;
	
	@ManyToOne
	@JoinColumn(name = "reservation_id")
	private Reservation reservation;
	
	@ManyToOne
	@JoinColumn(name = "screening_id")
	private Screening screening;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Seat getSeat() {
		return seat;
	}

	public void setSeat(Seat seat) {
		this.seat = seat;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public Screening getScreening() {
		return screening;
	}

	public void setScreening(Screening screening) {
		this.screening = screening;
	}
}
