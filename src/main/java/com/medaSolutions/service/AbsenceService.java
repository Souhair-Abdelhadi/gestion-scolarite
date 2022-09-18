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

import com.medaSolutions.entities.Absence;
import com.medaSolutions.entities.AbsencePK;
import com.medaSolutions.entities.Affectations;
import com.medaSolutions.entities.Etudiant;
import com.medaSolutions.entities.Module;
import com.medaSolutions.entities.pojo.AbsencePojo;
import com.medaSolutions.entities.pojo.AffectationPojo;
import com.medaSolutions.entities.sequence.AbsenceSequence;
import com.medaSolutions.repository.AbsenceRepo;
import com.medaSolutions.repository.EtudiantRepository;
import com.medaSolutions.repository.ModuleRepo;
import com.medaSolutions.repository.sequences.AbsenceSequenceRepo;

@RestController
public class AbsenceService {

	@Autowired
	private ModuleRepo moduleRepo;
	
	@Autowired
	private AbsenceRepo absenceRepo;
	
	@Autowired
	private EtudiantRepository etudiantRepo;
	
	@Autowired
	private AbsenceSequenceRepo absSeqRepo;
	
	
	@RolesAllowed("ROLE_SECRETAIRE")
	@PostMapping(value = "/addAbsence")
	public ResponseEntity<Object> addAbsence(@RequestBody @Valid AbsencePojo absencePojo ) {
		
		try {
			System.out.println("id etud : "+ absencePojo.getEtudiant_id()+ " id module : "+absencePojo.getModule_id());
			Optional<Etudiant> etud =  etudiantRepo.findById(absencePojo.getEtudiant_id());
			
			Optional<Module> module = moduleRepo.findById(absencePojo.getModule_id());
			
			if(etud.isPresent() && module.isPresent()) {
				
				AbsenceSequence absSeq = absSeqRepo.save(new AbsenceSequence(Date.valueOf(LocalDate.now())));
				
				Absence abs = new Absence(new AbsencePK(absSeq.getId(),absencePojo.getEtudiant_id(), absencePojo.getModule_id()),
						etud.get(), module.get(), absencePojo.getDate_absence());
				return ResponseEntity.status(HttpStatus.OK).body(absenceRepo.save(abs));
			}
			else {
				HashMap<String, Object> errors = new HashMap<>();
				errors.put("error", true);
				errors.put("message", "Either Etudiant id or Module id does not exist");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			HashMap<String, Object> errors = new HashMap<>();
			errors.put("error", true);
			errors.put("message", e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errors);
		}
		
	}
	
	@RolesAllowed("ROLE_SECRETAIRE")
	@PutMapping(value = "/updateAbsence")
	public ResponseEntity<Object> updateAffectation(@RequestBody @Valid AbsencePojo absencePojo) {
			
		try {
			HashMap<String, Object> errors = new HashMap<>();
			System.out.println("id absence : "+ absencePojo.getId());
			
			Optional<Absence> abs = absenceRepo.findAbsenceById(absencePojo.getId());
			if(abs.isPresent()) {
				Absence updatedAbs = new Absence(abs.get().getId(), abs.get().getEtudiant(),
						abs.get().getModule(), absencePojo.getDate_absence() != null ? absencePojo.getDate_absence() : abs.get().getDate_absence()
						,absencePojo.getJustification() != null ? absencePojo.getJustification() : abs.get().getJustification() );
				return ResponseEntity.status(HttpStatus.OK).body(absenceRepo.save(updatedAbs));
			}
			errors.put("error", true);
			errors.put("message", "there is no absence with the given id");
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
	
	
	@RolesAllowed("ROLE_SECRETAIRE")
	@DeleteMapping(value = "/deleteAbsence/{id}")
	public ResponseEntity<Object> deleteAffectation(@PathVariable int id) {	
		
		try {
			HashMap<String, Object> errors = new HashMap<>();
			System.out.println("id absence : "+ id);
			Optional<Absence> abs = absenceRepo.findAbsenceById(id);
			if(abs.isPresent()) {
				int res = absenceRepo.deleteAbsenceById(id);
				if(res != 0) {
					errors.put("message","Record with id "+ id + " deleted");
					return ResponseEntity.status(HttpStatus.OK).body(errors);
				}
			}
			errors.put("message","Record with id "+ id + " not found");
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
