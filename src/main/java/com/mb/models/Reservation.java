package com.mb.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "RESERVATION")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reservation {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "id")
	private String id;

	@NotBlank
	private String username;
	
	private boolean reserved;
	
	private boolean paid;

	@NotNull
	@OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL)
	private final Set<SeatReserved> seatsReserved = new HashSet<>();

	@NotNull
	@ManyToOne
	@JoinColumn(name = "screening_id")
	private Screening screening;

	public Set<SeatReserved> getSeatsReserved() {
		return Collections.unmodifiableSet(seatsReserved);
	}

	public boolean addSeatReserved(final SeatReserved seatReserved) {
		return seatsReserved.add(seatReserved);
	}
	
}
