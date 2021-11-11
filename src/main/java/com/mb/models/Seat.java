package com.mb.models;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "SEAT")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Seat {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "id")
	private String id;

	@NotBlank
	@Column(name = "row_num")
	private String row;

	@NotBlank
	@Column(name = "seat_num")
	private String num;

	@Setter
	@NotNull
	@ManyToOne
	@JoinColumn(name = "auditorium_id")
	private Auditorium auditorium;

	@NotNull
	@OneToMany(mappedBy = "seat", cascade = CascadeType.ALL)
	private final Set<SeatReserved> seatsReserved = new HashSet<>();

	public Set<SeatReserved> getSeatsReserved() {
		return Collections.unmodifiableSet(seatsReserved);
	}

	public boolean addSeatReserved(final SeatReserved seatReserved) {
		return seatsReserved.add(seatReserved);
	}
}
