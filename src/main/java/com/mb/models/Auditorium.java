package com.mb.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "AUDITORIUM")
@Getter
@Setter
public class Auditorium {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "id")
	private String id;
	
	private String name;
	
	private int seatsNum;
	
	@OneToMany(mappedBy = "auditorium", cascade = CascadeType.ALL)
	private Set<Screening> screenings;
	
	@OneToMany(mappedBy = "auditorium", cascade = CascadeType.ALL)
	private Set<Seat> seats;
}
