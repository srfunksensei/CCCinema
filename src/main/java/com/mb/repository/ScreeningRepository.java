package com.mb.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mb.models.Screening;

@Repository
public interface ScreeningRepository extends JpaRepository<Screening, Long> {

	List<Screening> findByStartAfter(Date start);
}
