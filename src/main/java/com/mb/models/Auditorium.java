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
@Table(name = "AUDITORIUM")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Auditorium {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "id")
	private String id;

	@NotBlank
	private String name;
	
	private int seatsNum;

	@NotNull
	@OneToMany(mappedBy = "auditorium", cascade = CascadeType.ALL)
	private Set<Screening> screenings;

	@NotNull
	@OneToMany(mappedBy = "auditorium", cascade = CascadeType.ALL)
	private Set<Seat> seats;

	public boolean addScreening(@NotNull final Screening screening) {
		return screenings.add(screening);
	}

	public boolean addSeats(@NotNull final Set<Seat> seats) {
		seatsNum += seats.size();
		return this.seats.addAll(seats);
	}
}
