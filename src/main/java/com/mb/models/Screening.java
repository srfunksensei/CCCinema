package com.mb.models;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "SCREENING", 
		uniqueConstraints = @UniqueConstraint(columnNames = { "movie_id", "auditorium_id", "start" }))
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Screening {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "id")
	private String id;

	@Setter
	private Timestamp start;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "movie_id")
	private Movie movie;

	@Setter
	@ManyToOne
	@JoinColumn(name = "auditorium_id")
	private Auditorium auditorium;

	@NotNull
	@OneToMany(mappedBy = "screening", cascade = CascadeType.ALL)
	private final Set<Reservation> reservations = new HashSet<>();

	@NotNull
	@OneToMany(mappedBy = "screening", cascade = CascadeType.ALL)
	private final Set<SeatReserved> seatsReserved = new HashSet<>();

	public Set<Reservation> getReservations() {
		return Collections.unmodifiableSet(reservations);
	}

	public Set<Seat> getSeats() {
		return Collections.unmodifiableSet(auditorium.getSeats());
	}

	public Set<Seat> getReservedSeats() {
		return seatsReserved.stream()
				.map(SeatReserved::getSeat)
				.collect(Collectors.toSet());
	}

	public boolean addSeatReserved(final SeatReserved seatReserved) {
		return seatsReserved.add(seatReserved);
	}

	public boolean addReservations(final Reservation reservation) {
		return reservations.add(reservation);
	}
}
