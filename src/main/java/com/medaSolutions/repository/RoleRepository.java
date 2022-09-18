package com.medaSolutions.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medaSolutions.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	
	Optional<Role> findByName(String name);

}
