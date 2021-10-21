package com.mb.models;

import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "RESERVATION")
@Getter
@Builder
public class Reservation {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "id")
	private final String id;

	@NotBlank
	private final String username;
	
	private final boolean reserved;
	
	private final boolean paid;

	@NotNull
	@OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL)
	private final Set<SeatReserved> seatsReserved;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "screening_id")
	private final Screening screening;
	
}
