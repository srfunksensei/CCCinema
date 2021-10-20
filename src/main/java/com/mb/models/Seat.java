package com.mb.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "SEAT")
@Getter
@Setter
public class Seat {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "id")
	private String id;

	private String row;
	
	private int num;
	
	@ManyToOne
	@JoinColumn(name = "auditorium_id")
	private Auditorium auditorium;
	
	@OneToMany(mappedBy = "seat", cascade = CascadeType.ALL)
	private Set<SeatReserved> seatsReserved;

}
