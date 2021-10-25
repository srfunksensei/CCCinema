package com.mb.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
	private Set<SeatReserved> seatsReserved;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "screening_id")
	private Screening screening;
	
}
