package com.medaSolutions.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.medaSolutions.entities.Compte;
import com.medaSolutions.entities.CompteRole;
import com.medaSolutions.repository.CompteRepository;
import com.medaSolutions.repository.RoleRepository;
import com.medaSolutions.entities.Role;


@RestController
public class CompteService {

	@Autowired
	private CompteRepository compteRepository;
	
	@Autowired
	private RoleRepository roleRepo;
	
	@RolesAllowed("ROLE_SECRETAIRE")
	@GetMapping(value = "/comptes")
	public ResponseEntity<Object> getComptes(){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(compteRepository.findAll());
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.OK).body(e.getLocalizedMessage());
		}
	}
	
	
	@RolesAllowed(value = {"ROLE_SECRETAIRE"})
	@PutMapping(value = "/updateCompte")
	public ResponseEntity<Object> updateCompte(@RequestBody @Valid Compte compte,BindingResult bindRes){
		
		try {
			if(bindRes.hasErrors()) {
				HashMap<String, Object> errors = new HashMap<>();
				errors.put("error", true);
				for(FieldError fe:bindRes.getFieldErrors()) {
					errors.put(fe.getField(), fe.getDefaultMessage());
				}
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
			}
			return ResponseEntity.status(HttpStatus.OK).body(compteRepository.save(compte));
		} catch (Exception e) {
			// TODO: handle exception
			HashMap<String, Object> errors = new HashMap<>();
			errors.put("error", true);
			errors.put("message", e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errors);
		}
		
	}
	
	
	@RolesAllowed(value = {"ROLE_SECRETAIRE"})
	@PostMapping(value="/addRoleToCompte" )
	public Compte addRoleToUser( @RequestBody CompteRole compteRole) {
		
		Optional<Compte> u = compteRepository.findByCin(compteRole.getCin());
		Optional<Role> r = roleRepo.findByName(compteRole.getRoleName());
		if(u.isPresent() && r.isPresent()) {
			u.get().getRoles().add(r.get());
			compteRepository.save(u.get());
		}
		return u.get();

	}
	
	
	
	
}
