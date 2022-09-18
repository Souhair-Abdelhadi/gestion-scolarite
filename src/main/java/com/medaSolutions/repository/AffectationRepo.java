package com.medaSolutions.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.medaSolutions.entities.AffectationPK;
import com.medaSolutions.entities.Affectations;

public interface AffectationRepo extends JpaRepository<Affectations, AffectationPK> {

	@Query(value = "SELECT * FROM affectations a WHERE a.id = :id ",nativeQuery = true)
	public Optional<Affectations> findAffectationsById(@Param("id") int id);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM affectations WHERE id = :id",nativeQuery = true)
	int deleteAffectationById(@Param("id") int id);
	
}
