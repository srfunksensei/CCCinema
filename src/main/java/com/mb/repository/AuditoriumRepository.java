package com.mb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mb.models.Auditorium;

@Repository
public interface AuditoriumRepository extends JpaRepository<Auditorium, String> {

}
