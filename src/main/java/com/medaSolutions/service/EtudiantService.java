package com.medaSolutions.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medaSolutions.entities.Compte;
import com.medaSolutions.entities.Etudiant;
import com.medaSolutions.entities.Role;
import com.medaSolutions.entities.pojo.EtudiantPojo;
import com.medaSolutions.repository.CompteRepository;
import com.medaSolutions.repository.EtudiantRepository;
import com.medaSolutions.repository.FiliereRepo;
import com.medaSolutions.repository.RoleRepository;

@RestController
public class EtudiantService {

	
	@Autowired
	private EtudiantRepository etudiantRepository;
	
	@Autowired
	private CompteRepository compteRepo;
	
	@Autowired
	private FiliereRepo filiereRepo;
	
	@Autowired
	private RoleRepository roleRepo;
	
	@RolesAllowed(value = {"ROLE_SECRETAIRE"})
	@RequestMapping(value = "/etudiants")
	public ResponseEntity<Object> getEtudiants(){
				try {
					return ResponseEntity.status(HttpStatus.OK).body(etudiantRepository.findAll());
				} catch (Exception e) {
					// TODO: handle exception
					HashMap<String, Object> errors = new HashMap<>();
					errors.putIfAbsent("message", e.getLocalizedMessage());
					return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errors);
					
				}
		
	}
	
	@RolesAllowed(value = {"ROLE_SECRETAIRE"})
	@RequestMapping(value = "/etudiant/{id}")
	public ResponseEntity<Object> getEtudiant(@PathVariable int id) {
		try {
			if(id == 0 ) {
				HashMap<String, Object> errors = new HashMap<>();
				errors.put("error", true);
				errors.put("message", "Etudiant id is required ");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
			}
			Optional<Etudiant> etud = etudiantRepository.findById(id);
			if(etud.isPresent()) {
				return ResponseEntity.status(HttpStatus.OK).body(etudiantRepository.findById(id));
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
	
	@RolesAllowed(value = {"ROLE_SECRETAIRE"})
	@PostMapping(value = "/addEtudiant")
	public ResponseEntity<Object> addEtudiant(@RequestBody @Valid EtudiantPojo etudiantPojo,BindingResult bindRes){
		
		try {
			if(bindRes.hasErrors()) {
				HashMap<String, Object> errors = new HashMap<>();
				errors.put("error", true);
				for(FieldError fe:bindRes.getFieldErrors()) {
					errors.put(fe.getField(), fe.getDefaultMessage());
				}
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
			}
			Optional<Compte> compte = compteRepo.findByCin(etudiantPojo.getCin());
			if(compte.isPresent()) {
				HashMap<String, Object> errors = new HashMap<>();
				errors.put("error", true);
				errors.put("message", "Can't add student because already a Compte exist with the same cin ");
				return ResponseEntity.status(HttpStatus.CONFLICT).body(errors);
			}
			System.out.println("filiere name : "+filiereRepo.findById(etudiantPojo.getFiliereId()).get().getNom());
			Etudiant etudiant = new Etudiant(etudiantPojo.getNom(),etudiantPojo.getPrenom(),etudiantPojo.getNiveau() , etudiantPojo.getCin(),
				etudiantPojo.getCne(), etudiantPojo.getImmatriculation(), etudiantPojo.getTel(), etudiantPojo.getEmail(),
				etudiantPojo.getDateNaissance(),filiereRepo.findById(etudiantPojo.getFiliereId()).get());
			etudiant = etudiantRepository.save(etudiant);
			Set<Role> role = new HashSet<>();
			role.add(roleRepo.findByName("Role_ETUDIANT").get());
			BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
			compteRepo.save(new Compte(etudiantPojo.getCin(),bc.encode(etudiantPojo.getMdp()) , true, etudiant, null, null, role));
			//etudiant.setCompte(cmpt);
			return ResponseEntity.status(HttpStatus.OK).body(etudiant);
		} catch (Exception e) {
			// TODO: handle exception
			HashMap<String, Object> errors = new HashMap<>();
			errors.put("error", true);
			errors.put("message", e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errors);
		}
		
	}
	
	@RolesAllowed(value = {"ROLE_SECRETAIRE"})
	@PutMapping(value = "/updateEtudiant")
	public ResponseEntity<Object> updateEtudiant(@RequestBody @Valid EtudiantPojo etudiantPojo ,BindingResult bindRes){
		
		//need to check if the Etudiant exist or not if not don't admit the changes
		try {
			if(bindRes.hasErrors()) {
				HashMap<String, Object> errors = new HashMap<>();
				errors.put("error", true);
				for(FieldError fe:bindRes.getFieldErrors()) {
					errors.put(fe.getField(), fe.getDefaultMessage());
				}
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
			}
			Optional<Etudiant> etud = etudiantRepository.findById(etudiantPojo.getId());
			if(etud.isPresent()) {
				HashMap<String, Object> mp = new HashMap<>();
				Etudiant etudiant1 = new Etudiant(etudiantPojo.getId(),etudiantPojo.getNom(),etudiantPojo.getPrenom(),etudiantPojo.getNiveau() , etudiantPojo.getCin(),
						etudiantPojo.getCne(), etudiantPojo.getImmatriculation(), etudiantPojo.getTel(), etudiantPojo.getEmail(),
						etudiantPojo.getDateNaissance(),filiereRepo.findById(etudiantPojo.getFiliereId()).get());
				Etudiant et = etudiantRepository.save(etudiant1);
				return ResponseEntity.status(HttpStatus.OK).body(et);
			}
			else {
				HashMap<String, Object> errors = new HashMap<>();
				errors.put("error", true);
				errors.put("message", "No Eudiant found with the id "+etudiantPojo.getId());
				return ResponseEntity.status(HttpStatus.OK).body(errors);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			HashMap<String, Object> errors = new HashMap<>();
			errors.put("error", true);
			errors.put("message", e.getMessage());
			errors.put("id", etudiantPojo.getId());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errors);
		}
		
	}
	
	@RolesAllowed(value = {"ROLE_SECRETAIRE"})
	@DeleteMapping(value = "/deleteEtudiant/{id}")
	public ResponseEntity<Object> deleteEtudiant(@PathVariable int id){
		
		try {
			Optional<Etudiant> etud = etudiantRepository.findById(id);
			HashMap<String, Object> res = new HashMap<>();
			if(etud.isPresent()) {
				
				etudiantRepository.deleteEtudiantById(id);
				return ResponseEntity.status(HttpStatus.OK).body(res.put("message"," Etudiant with the id : "+ id+" deleted"));
			}
			res.put("error", true);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res.put("message","No Etudiant was found with the id "+id));
		} catch (Exception e) {
			// TODO: handle exception
			HashMap<String, Object> errors = new HashMap<>();
			errors.put("error", true);
			errors.put("message", e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errors);
		}
		
	}
}
