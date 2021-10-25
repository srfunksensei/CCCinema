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
	private String cast;

	@NotBlank
	private String description;
	
	private int duration;

	@NotNull
	@OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
	private Set<Screening> screenings;

}
