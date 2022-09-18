package com.medaSolutions.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medaSolutions.entities.Compte;
import com.medaSolutions.entities.Enseignant;
import com.medaSolutions.entities.Etudiant;
import com.medaSolutions.entities.Role;
import com.medaSolutions.entities.pojo.EnseignantPojo;
import com.medaSolutions.repository.CompteRepository;
import com.medaSolutions.repository.EnseignantRepo;
import com.medaSolutions.repository.RoleRepository;

@RestController
public class EnseignantService {

	@Autowired
	private EnseignantRepo enseignantRepo;
	
	@Autowired
	private CompteRepository compteRepo;
	
	@Autowired
	private RoleRepository roleRepo;
	
	@RolesAllowed("ROLE_SECRETAIRE")
	@GetMapping(value = "/enseignants")
	public ResponseEntity<Object> getEnseignants(){
		
		try {
			return ResponseEntity.status(HttpStatus.OK).body(enseignantRepo.findAll());
		} catch (Exception e) {
			// TODO: handle exception
			HashMap<String, Object> errors = new HashMap<>();
			errors.putIfAbsent("message", e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errors);
			
		}
	}
	
	@RolesAllowed(value = {"ROLE_SECRETAIRE"})
	@RequestMapping(value = "/enseignant/{id}")
	public ResponseEntity<Object> getEtudiant(@PathVariable int id) {
		try {
			if(id == 0 ) {
				HashMap<String, Object> errors = new HashMap<>();
				errors.put("error", true);
				errors.put("message", "Enseignant id is required ");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
			}
			Optional<Enseignant> ens = enseignantRepo.findById(id);
			if(ens.isPresent()) {
				return ResponseEntity.status(HttpStatus.OK).body(ens.get());
			}
			HashMap<String, Object> errors = new HashMap<>();
			errors.put("error", true);
			errors.put("message", "No Etudiant found with the given id ");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
		} catch (Exception e) {
			// TODO: handle exception
			HashMap<String, Object> errors = new HashMap<>();
			errors.put("error", true);
			errors.put("message", e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errors);
		}
	}
	
	
	@RolesAllowed("ROLE_SECRETAIRE")
	@PostMapping(value = "/addEnseignant")
	public ResponseEntity<Object> addEnseignant(@RequestBody @Valid EnseignantPojo enseignantPojo,BindingResult bindRes){
		
		try {
			if(bindRes.hasErrors()) {
				HashMap<String, Object> errors = new HashMap<>();
				errors.put("error", true);
				for(FieldError fe:bindRes.getFieldErrors()) {
					errors.put(fe.getField(), fe.getDefaultMessage());
				}
				return ResponseEntity.status(400).body(errors);
			}
			Optional<Enseignant> o_ens = enseignantRepo.findByCin(enseignantPojo.getCin());
			if(o_ens.isPresent()) {
				HashMap<String, Object> errors = new HashMap<>();
				errors.put("error", true);
				errors.put("message","A Enseignant already exist with the same cin" );
				return ResponseEntity.status(HttpStatus.CONFLICT).body(errors);
			}
			Enseignant ens = new Enseignant(enseignantPojo.getNom(), enseignantPojo.getPrenom(),enseignantPojo.getCin(),
					enseignantPojo.getDate_nais(), enseignantPojo.getDate_emb(), enseignantPojo.getEmail(),enseignantPojo.getTel());
				Enseignant res = enseignantRepo.save(ens);
			BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
			Compte compte = new Compte(enseignantPojo.getCin(),bc.encode(enseignantPojo.getMdp()),true,null,null,ens,new HashSet<Role>());
			compte.addRole(roleRepo.findByName("ROLE_ENSEIGNANT").get());
			compteRepo.save(compte);
			return ResponseEntity.status(HttpStatus.OK).body(res) ;
		} catch (Exception e) {
			// TODO: handle exception
			HashMap<String, Object> errors = new HashMap<>();
			errors.put("error", true);
			errors.put("message", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errors);
		}
		
	}
	
	@RolesAllowed("ROLE_SECRETAIRE")
	@PutMapping(value = "/updateEnseignant/{id}")
	public ResponseEntity<Object> updateEnseignant(@PathVariable int id,@RequestBody @Valid Enseignant enseignant,BindingResult bindRes){
		
		try {
			if(bindRes.hasErrors()) {
				HashMap<String, Object> errors = new HashMap<>();
				errors.put("error", true);
				for(FieldError fe:bindRes.getFieldErrors()) {
					errors.put(fe.getField(), fe.getDefaultMessage());
				}
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
			}
			Optional<Enseignant> ens = enseignantRepo.findById(id);
			if(ens.isPresent()) {
				enseignant.setId(id);
				return ResponseEntity.status(HttpStatus.OK).body(enseignantRepo.save(enseignant));
			}
			else {
				HashMap<String, Object> errors = new HashMap<>();
				errors.put("error", true);
				errors.put("message", "No Eudiant found with the id "+id);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			HashMap<String, Object> errors = new HashMap<>();
			errors.put("error", true);
			errors.put("message", e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errors);
		}
		
	}
	
	@RolesAllowed(value = {"ROLE_SECRETAIRE"})
	@DeleteMapping(value = "/deleteEnseignant/{id}")
	public ResponseEntity<Object> deleteEnseignant(@PathVariable int id){
		
		try {
			Optional<Enseignant> ens = enseignantRepo.findById(id);
			HashMap<String, Object> res = new HashMap<>();
			if(ens.isPresent()) {
				
				enseignantRepo.deleteEnseignantById(id);
				return ResponseEntity.status(HttpStatus.OK).body(res.put("message"," Enseignant with the id : "+ id+" deleted"));
			}
			res.put("error", true);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res.put("message","No Enseignant was found with the id "+id));
		} catch (Exception e) {
			// TODO: handle exception
			HashMap<String, Object> errors = new HashMap<>();
			errors.put("error", true);
			errors.put("message", e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errors);
		}
		
	}
}
