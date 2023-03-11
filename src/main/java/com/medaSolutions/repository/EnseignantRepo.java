package com.medaSolutions.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.medaSolutions.entities.Professeur;

public interface EnseignantRepo extends JpaRepository<Professeur, Integer> {
	
	Optional<Professeur> findByCin(String cin);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM enseignants WHERE id = :id",nativeQuery = true)
	int deleteEnseignantById(@Param("id") int id);
}
