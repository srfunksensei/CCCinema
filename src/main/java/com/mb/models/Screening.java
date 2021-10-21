package com.mb.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "SCREENING", 
		uniqueConstraints = @UniqueConstraint(columnNames = { "movie_id", "auditorium_id", "start" }))
@Getter
public class Screening {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "id")
	private String id;

	private Timestamp start;

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
}
