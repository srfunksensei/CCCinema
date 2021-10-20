package com.mb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mb.models.SeatReserved;

@Repository
public interface SeatReservedRepository extends JpaRepository<SeatReserved, String> {

}
