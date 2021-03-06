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
@Table(name = "MOVIE")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movie {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "id")
	private String id;

	@NotBlank
	private String title;

	@NotBlank
	private String director;

	@NotBlank
	private String actors;

	@NotBlank
	private String description;
	
	private int duration;

	@NotNull
	@OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
	private final Set<Screening> screenings = new HashSet<>();

	public Set<Screening> getScreenings() {
		return Collections.unmodifiableSet(screenings);
	}

	public boolean addScreening(final Screening screening) {
		return screenings.add(screening);
	}
}
