package com.medaSolutions.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.medaSolutions.entities.Compte;
import com.medaSolutions.entities.Filiere;

public interface FiliereRepo extends JpaRepository<Filiere, Integer> {

	
	Optional<Filiere> findByNom(String nom);
	boolean existsByNom(String nom);
	
	
}
