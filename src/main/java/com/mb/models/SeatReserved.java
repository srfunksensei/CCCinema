package com.mb.models;

import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "SEAT_RESERVED")
@Getter
@Builder
public class SeatReserved {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "id")
	private final String id;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "seat_id")
	private final Seat seat;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "reservation_id")
	private final Reservation reservation;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "screening_id")
	private final Screening screening;
}
