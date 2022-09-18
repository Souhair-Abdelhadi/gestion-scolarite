package com.medaSolutions.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medaSolutions.entities.Compte;

public interface CompteRepository extends JpaRepository<Compte, Integer> {

	Optional<Compte> findByCin(String cin);
	boolean existsByCin(String cin);
}
