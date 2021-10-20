package com.mb.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "MOVIE")
@Getter
@Setter
public class Movie {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "id")
	private String id;
	
	private String title;
	
	private String director;
	
	private String cast;
	
	private String description;
	
	private int duration;
	
	@OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
	private Set<Screening> screenings;

}
