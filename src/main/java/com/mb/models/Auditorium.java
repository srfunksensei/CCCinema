package com.mb.models;

import lombok.Getter;
import lombok.Setter;

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
@Getter
@Setter
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
}
