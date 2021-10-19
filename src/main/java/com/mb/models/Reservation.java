package com.mb.models;

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

@Entity
@Table(name = "RESERVATION")
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String username;
	
	private boolean reserved;
	
	private boolean paid;

	@OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL)
	private Set<SeatReserved> seatsReserved;
	
	@ManyToOne
	@JoinColumn(name = "screening_id")
	private Screening screening;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isReserved() {
		return reserved;
	}

	public void setReserved(boolean reserved) {
		this.reserved = reserved;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

	public Set<SeatReserved> getSeatsReserved() {
		return seatsReserved;
	}

	public void setSeatsReserved(Set<SeatReserved> seatsReserved) {
		this.seatsReserved = seatsReserved;
	}

	public Screening getScreening() {
		return screening;
	}

	public void setScreening(Screening screening) {
		this.screening = screening;
	}
}
