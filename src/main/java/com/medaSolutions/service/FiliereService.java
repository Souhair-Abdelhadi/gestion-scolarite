package com.medaSolutions.service;

import java.util.HashMap;
import java.util.Optional;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medaSolutions.entities.Filiere;
import com.medaSolutions.entities.Module;
import com.medaSolutions.entities.pojo.FilierePojo;
import com.medaSolutions.repository.CompteRepository;
import com.medaSolutions.repository.EtudiantRepository;
import com.medaSolutions.repository.FiliereRepo;
import com.medaSolutions.repository.ModuleRepo;
import com.medaSolutions.repository.RoleRepository;

@RestController
public class FiliereService {

	@Autowired
	private FiliereRepo filiereRepo;
	
	@Autowired
	private CompteRepository compteRepo;
	
	@Autowired
	private ModuleRepo moduleRepo;
	
	@RolesAllowed(value = {"ROLE_SECRETAIRE"})
	@RequestMapping(value = "/filieres")
	public ResponseEntity<Object> getFilieres(){
				try {
					return ResponseEntity.status(HttpStatus.OK).body(filiereRepo.findAll());
				} catch (Exception e) {
					// TODO: handle exception
					HashMap<String, Object> errors = new HashMap<>();
					errors.putIfAbsent("message", e.getLocalizedMessage());
					return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errors);
					
				}
		
	}
	
	@RolesAllowed(value = {"ROLE_SECRETAIRE"})
	@PostMapping(value = "/addFiliere")
	public ResponseEntity<Object> addFilieres(@RequestBody @Valid Filiere filiere, BindingResult bindRes){
				try {
					if(bindRes.hasErrors()) {
						HashMap<String, Object> errors = new HashMap<>();
						errors.put("error", true);
						for(FieldError fe:bindRes.getFieldErrors()) {
							errors.put(fe.getField(), fe.getDefaultMessage());
						}
						return ResponseEntity.status(400).body(errors);
					}
					Optional<Filiere> filiere_exist = filiereRepo.findByNom(filiere.getNom());
					if(filiere_exist.isPresent()) {
						return ResponseEntity.status(HttpStatus.CONFLICT).body(filiere_exist.get());
					}
					
					return ResponseEntity.status(HttpStatus.OK).body(filiereRepo.save(filiere));
				} catch (Exception e) {
					// TODO: handle exception
					HashMap<String, Object> errors = new HashMap<>();
					errors.putIfAbsent("message", e.getLocalizedMessage());
					return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errors);
					
				}
		
	}
	
	@RolesAllowed(value = {"ROLE_SECRETAIRE"})
	@PostMapping(value = "/addModuleToFiliere")
	public ResponseEntity<Object> addModuleToFiliere(@RequestBody FilierePojo filierePojo, BindingResult bindRes){
				try {
					if(bindRes.hasErrors()) {
						HashMap<String, Object> errors = new HashMap<>();
						errors.put("error", true);
						for(FieldError fe:bindRes.getFieldErrors()) {
							errors.put(fe.getField(), fe.getDefaultMessage());
						}
						return ResponseEntity.status(400).body(errors);
					}
					
					Optional<Filiere> fl = filiereRepo.findById(filierePojo.getId()) ;
					
					Optional<Module> mdl = moduleRepo.findById(filierePojo.getIdModule());
					
					if(fl.isPresent() && mdl.isPresent()) {
						fl.get().getModules().add(mdl.get());
						fl.get().setModules(fl.get().getModules());
						return ResponseEntity.status(HttpStatus.OK).body(filiereRepo.save(fl.get()));
					}
					
					HashMap<String, Object> errors = new HashMap<>();
					errors.putIfAbsent("message", "Either Filiere id or Module id does not exist !!");
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
				} catch (Exception e) {
					// TODO: handle exception
					HashMap<String, Object> errors = new HashMap<>();
					errors.putIfAbsent("message", e.getLocalizedMessage());
					return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errors);
					
				}
		
	}
	
	
	
	
}
