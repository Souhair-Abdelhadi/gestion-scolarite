package com.medaSolutions.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.medaSolutions.entities.Module;

public interface ModuleRepo extends JpaRepository<Module,Integer> {
	
}
