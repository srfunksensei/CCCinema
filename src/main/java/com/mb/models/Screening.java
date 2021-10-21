package com.mb.models;

import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

	@NotNull
	private Timestamp start;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "movie_id")
	private Movie movie;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "auditorium_id")
	private Auditorium auditorium;

	@NotNull
	@OneToMany(mappedBy = "screening", cascade = CascadeType.ALL)
	private Set<Reservation> reservations;

	@NotNull
	@OneToMany(mappedBy = "screening", cascade = CascadeType.ALL)
	private Set<SeatReserved> seatsReserved;
}
