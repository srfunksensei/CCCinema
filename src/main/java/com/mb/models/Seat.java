package com.mb.models;

import lombok.Getter;
import lombok.Setter;

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
@Getter
@Setter
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

}
