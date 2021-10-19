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
@Table(name = "SEAT")
public class Seat {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String row;
	
	private int num;
	
	@ManyToOne
	@JoinColumn(name = "auditorium_id")
	private Auditorium auditorium;
	
	@OneToMany(mappedBy = "seat", cascade = CascadeType.ALL)
	private Set<SeatReserved> seatsReserved;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRow() {
		return row;
	}

	public void setRow(String row) {
		this.row = row;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public Auditorium getAuditorium() {
		return auditorium;
	}

	public void setAuditorium(Auditorium auditorium) {
		this.auditorium = auditorium;
	}

	public Set<SeatReserved> getSeatsReserved() {
		return seatsReserved;
	}

	public void setSeatsReserved(Set<SeatReserved> seatsReserved) {
		this.seatsReserved = seatsReserved;
	}
}
