package com.mb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mb.models.Seat;

@Repository
public interface SeatRepository extends JpaRepository<Seat, String> {

}
