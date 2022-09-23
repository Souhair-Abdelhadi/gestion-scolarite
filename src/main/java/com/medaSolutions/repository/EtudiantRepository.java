package com.medaSolutions.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.medaSolutions.entities.Etudiant;

public interface EtudiantRepository extends JpaRepository<Etudiant, Integer>  {

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM etudiants WHERE id = :id",nativeQuery = true)
	int deleteEtudiantById(@Param("id") int id);
	
	
	@Query(value = "SELECT * FROM etudiants e WHERE e.cin = :cin",nativeQuery = true)
	Optional<Etudiant> findEtudiantByCin(@Param("cin") String cin);
}
