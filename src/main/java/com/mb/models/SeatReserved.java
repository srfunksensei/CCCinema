package com.mb.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "SEAT_RESERVED")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeatReserved {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "id")
	private String id;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "seat_id")
	private Seat seat;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "reservation_id")
	private Reservation reservation;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "screening_id")
	private Screening screening;
}
