package com.mb.repository;

import com.mb.models.Screening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface ScreeningRepository extends JpaRepository<Screening, String> {

	List<Screening> findByStartingTimeAfter(final Timestamp start);
}
