package com.mb.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "RESERVATION")
@Getter
@Builder
public class Reservation {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "id")
	private String id;
	
	private String username;
	
	private boolean reserved;
	
	private boolean paid;

	@OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL)
	private Set<SeatReserved> seatsReserved;
	
	@ManyToOne
	@JoinColumn(name = "screening_id")
	private Screening screening;
	
}
