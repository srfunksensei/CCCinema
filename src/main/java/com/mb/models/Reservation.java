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
@Table(name = "RESERVATION")
@Getter
@Setter
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
	
}
