package com.medaSolutions.repository;

import java.sql.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.medaSolutions.entities.Absence;
import com.medaSolutions.entities.AbsencePK;


public interface AbsenceRepo extends JpaRepository<Absence, AbsencePK> {

	@Query(value = "SELECT * FROM absences a WHERE a.id = :id",nativeQuery = true)
	Optional<Absence> findAbsenceById(@Param("id") int id);
	
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM absences WHERE id = :id",nativeQuery = true)
	int deleteAbsenceById(@Param("id") int id);
	
}
