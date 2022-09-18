package com.medaSolutions.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Optional;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.medaSolutions.entities.AffectationPK;
import com.medaSolutions.entities.Affectations;
import com.medaSolutions.entities.Enseignant;
import com.medaSolutions.entities.Module;
import com.medaSolutions.entities.pojo.AffectationPojo;
import com.medaSolutions.entities.sequence.AffectationSequence;
import com.medaSolutions.repository.AffectationRepo;
import com.medaSolutions.repository.EnseignantRepo;
import com.medaSolutions.repository.ModuleRepo;
import com.medaSolutions.repository.sequences.AffectationSequenceRepo;

@RestController
public class AffectationService {

	@Autowired
	private AffectationRepo affectationRepo;
	
	@Autowired
	private EnseignantRepo ensRepo;
	
	@Autowired
	private ModuleRepo moduleRepo;
	
	@Autowired
	private AffectationSequenceRepo affctSeqRepo;
	
	
	
	@RolesAllowed("ROLE_SECRETAIRE")
	@PostMapping(value = "/affectModuleEnseignant")
	public ResponseEntity<Object> addAffectation(@RequestBody @Valid AffectationPojo affectationPojo) {
					
		try {
			
			//need to check if already the professor had the module or not, if so don't admit the post 
			
			HashMap<String, Object> errors = new HashMap<>();
			System.out.println("id ens : "+ affectationPojo.getIdEnseignant()+ " id module : "+affectationPojo.getIdModule());
			Optional<Enseignant> ens =  ensRepo.findById(affectationPojo.getIdEnseignant());
			Optional<Module> module = moduleRepo.findById(affectationPojo.getIdModule());	
			if(ens.isPresent() && module.isPresent()) {
				AffectationSequence affectSeq = affctSeqRepo.save(new AffectationSequence(Date.valueOf(LocalDate.now())));
				Affectations affect = new Affectations(new AffectationPK(affectSeq.getId(),ens.get().getId(), module.get().getId()),
						ens.get(), module.get(), affectationPojo.getDate_affectation());
				return ResponseEntity.status(HttpStatus.OK).body(affectationRepo.save(affect));
				
			}
			errors.put("error", true);
			errors.put("message", "Either Enseignant and/or Module id's does not exist");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
		} catch (Exception e) {
			// TODO: handle exception
			HashMap<String, Object> errors = new HashMap<>();
			errors.put("error", true);
			errors.put("message", e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errors);

		}
		
	}
	
	
	
	@RolesAllowed("ROLE_SECRETAIRE")
	@PutMapping(value = "/updateAffectation")
	public ResponseEntity<Object> updateAffectation(@RequestBody AffectationPojo affectationPojo) {
						
		try {
			HashMap<String, Object> errors = new HashMap<>();
			System.out.println("id affectation : "+ affectationPojo.getId());
			Optional<Affectations> affect = affectationRepo.findAffectationsById(affectationPojo.getId());
			if(affect.isPresent()) {
				Affectations updatedAffect = new Affectations(affect.get().getId(), affect.get().getEnseignant(),
						affect.get().getModule(), affectationPojo.getDate_affectation());
				return ResponseEntity.status(HttpStatus.OK).body(affectationRepo.save(updatedAffect));
				
			}
			errors.put("error", true);
			errors.put("message", "there is no affectation with the given id");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			HashMap<String, Object> errors = new HashMap<>();
			errors.put("error", true);
			errors.put("message", e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
		}
		
	}
	
	
	@RolesAllowed("ROLE_SECRETAIRE")
	@DeleteMapping(value = "/deleteAffectation/{id}")
	public ResponseEntity<Object> deleteAffectation(@PathVariable int id) {		
		
		try {
			HashMap<String, Object> errors = new HashMap<>();
			System.out.println("id affectation : "+ id);
			Optional<Affectations> affect = affectationRepo.findAffectationsById(id);		
			if(affect.isPresent()) {
				int res = affectationRepo.deleteAffectationById(id);
				if(res != 0) {
					errors.put("message","Record with id "+ id + " deleted");
					return ResponseEntity.status(HttpStatus.OK).body(errors);
				}
							
			}
			errors.put("error", true);
			errors.put("message", "there is no affectation with the given id");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
			
		} catch (Exception e) {
			// TODO: handle exception
			HashMap<String, Object> errors = new HashMap<>();
			System.out.println(e.getLocalizedMessage());
			errors.put("error", true);
			errors.put("message", e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errors);
		}
		
	}
	
	
}
