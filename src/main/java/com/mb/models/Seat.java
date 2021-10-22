package com.mb.models;

import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "SEAT")
@Getter
public class Seat {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "id")
	private String id;

	@NotBlank
	@Column(name = "line")
	private String row;

	@NotBlank
	private String num;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "auditorium_id")
	private Auditorium auditorium;

	@NotNull
	@OneToMany(mappedBy = "seat", cascade = CascadeType.ALL)
	private Set<SeatReserved> seatsReserved;

}
