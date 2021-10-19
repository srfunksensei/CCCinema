package com.mb.models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "AUDITORIUM")
public class Auditorium {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String name;
	
	private int seatsNum;
	
	@OneToMany(mappedBy = "auditorium", cascade = CascadeType.ALL)
	private Set<Screening> screenings;
	
	@OneToMany(mappedBy = "auditorium", cascade = CascadeType.ALL)
	private Set<Seat> seats;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSeatsNum() {
		return seatsNum;
	}

	public void setSeatsNum(int seatsNum) {
		this.seatsNum = seatsNum;
	}

	public Set<Screening> getScreenings() {
		return screenings;
	}

	public void setScreenings(Set<Screening> screenings) {
		this.screenings = screenings;
	}

	public Set<Seat> getSeats() {
		return seats;
	}

	public void setSeats(Set<Seat> seats) {
		this.seats = seats;
	}
}
