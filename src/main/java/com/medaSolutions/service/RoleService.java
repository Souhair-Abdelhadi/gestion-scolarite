package com.medaSolutions.service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.medaSolutions.entities.Role;
import com.medaSolutions.repository.RoleRepository;

@RestController
public class RoleService {

	
	@Autowired
	private RoleRepository roleRepo;
	
	
	
	@PostMapping(value = "/addRole")
	public Role addRole(@RequestBody @Valid Role role ) {
		
		return roleRepo.save(role);
		
		
	}
	
	
	
	
}
