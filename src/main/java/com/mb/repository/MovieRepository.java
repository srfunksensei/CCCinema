package com.mb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mb.models.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, String> {

}
