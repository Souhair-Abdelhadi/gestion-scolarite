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
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.medaSolutions.entities.Etudiant;
import com.medaSolutions.entities.Notes;
import com.medaSolutions.entities.NotesPK;
import com.medaSolutions.entities.pojo.NotePojo;
import com.medaSolutions.entities.sequence.NoteSequence;
import com.medaSolutions.repository.EtudiantRepository;
import com.medaSolutions.repository.ModuleRepo;
import com.medaSolutions.repository.NoteRepo;
import com.medaSolutions.repository.sequences.NoteSequenceRepo;
import com.medaSolutions.entities.Module;
@RestController
public class NoteService {

	
	@Autowired
	private NoteRepo noteRepo;
	
	@Autowired
	private EtudiantRepository etudiantRepo;
	
	@Autowired
	private ModuleRepo moduleRepo;
	
	@Autowired
	private NoteSequenceRepo noteSeqRepo;
	
	@RolesAllowed(value = {"ROLE_SECRETAIRE","ROLE_PROFESSEUR"})
	@GetMapping(value = "/notes/{id}")
	public ResponseEntity<Object> getNote(@PathVariable int id){
		try {
			if( id == 0) {
				HashMap<String, Object> errors = new HashMap<>();
				errors.put("error", true);
				errors.put("message", "Note id is required");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
			}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(noteRepo.findNoteById(id));

		} catch (Exception e) {
			// TODO: handle exception
			HashMap<String, Object> errors = new HashMap<>();
			errors.put("error", true);
			errors.put("message", e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errors);

		}
	}
	
	
	@RolesAllowed(value = "ROLE_PROFESSEUR")
	@PostMapping(value = "/addNote")
	public ResponseEntity<Object> addNote(@RequestBody @Valid NotePojo notePojo,BindingResult bindingRes){
		try {
			if( bindingRes.hasErrors()) {
				HashMap<String, Object> errors = new HashMap<>();
				errors.put("error", true);
				for(FieldError fe:bindingRes.getFieldErrors()) {
					errors.put(fe.getField(), fe.getDefaultMessage());
				}
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
			}
			
			Optional<Etudiant> etud = etudiantRepo.findById(notePojo.getEtudiant_id());
			Optional<Module> module = moduleRepo.findById(notePojo.getModule_id());
			
			if(etud.isPresent() && module.isPresent()) {
				Optional<Notes> noteExist = noteRepo.findNoteByEtudianIdtAndModuleId(notePojo.getEtudiant_id(), notePojo.getModule_id());
				if(!noteExist.isPresent()) {
					NoteSequence noteSeq = new NoteSequence(Date.valueOf(LocalDate.now()));
					int seq = noteSeqRepo.save(noteSeq).getId();
					System.out.println("id : "+notePojo.getEtudiant_id()+" id : "+notePojo.getModule_id());
					Notes note = new Notes(new NotesPK(seq, notePojo.getEtudiant_id(), notePojo.getModule_id()),
							etud.get(),module.get(),notePojo.getNote());
					return ResponseEntity.status(HttpStatus.OK).body(noteRepo.save(note));
				}
				HashMap<String, Object> errors = new HashMap<>();
				errors.put("error", true);
				errors.put("message", "A Note already exist for Etudiant with that Note ");
				return ResponseEntity.status(HttpStatus.CONFLICT).body(errors);
			}
			
			HashMap<String, Object> errors = new HashMap<>();
			errors.put("error", true);
			errors.put("message", "Either Etudiant or Module does not exist");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);

		} catch (Exception e) {
			// TODO: handle exception
			HashMap<String, Object> errors = new HashMap<>();
			errors.put("error", true);
			errors.put("message", e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errors);

		}
	}
	
	
	@RolesAllowed(value = {"ROLE_PROFESSEUR"})
	@PutMapping(value = "/updateNote/{id}")
	public ResponseEntity<Object> updateNote(@PathVariable int id,@RequestBody @Valid NotePojo notePojo){
		try {
			HashMap<String, Object> errors = new HashMap<>();
			if( id <= 0) {
				errors.put("error", true);
				errors.put("message","Note id is Required");

				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
			}
			if( notePojo.getNote() < 0 || notePojo.getNote() > 20) {
				errors.put("error", true);
				errors.put("message","Note can't be outside the range [0,20]");

				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
			}
			Optional<Notes> note_o = noteRepo.findNoteById(id);
			if(note_o.isPresent()) {
				int res = noteRepo.updateNoteById(id, notePojo.getNote());
				errors.put("message","note Updated");

				return ResponseEntity.status(HttpStatus.OK).body(errors);
			}
			errors.put("error", true);
			errors.put("message","No Note found with this given id");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);

		} catch (Exception e) {
			// TODO: handle exception
			HashMap<String, Object> errors = new HashMap<>();
			errors.put("error", true);
			errors.put("message", e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errors);

		}
	}
	
	@RolesAllowed(value = {"ROLE_SECRETAIRE","ROLE_PROFESSEUR"})
	@PutMapping(value = "/deleteNote/{id}")
	public ResponseEntity<Object> deleteNote(@PathVariable int id){
		try {
			HashMap<String, Object> errors = new HashMap<>();
			if( id == 0) {
				errors.put("error", true);
				errors.put("message","Note id is Required");

				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
			}

			Optional<Notes> note_o = noteRepo.findNoteById(id);
			if(note_o.isPresent()) {
				int res = noteRepo.deleteNoteById(id);
				errors.put("message","Note deleted");

				return ResponseEntity.status(HttpStatus.OK).body(errors);
			}
			errors.put("error", true);
			errors.put("message","No Note found with this given id");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);

		} catch (Exception e) {
			// TODO: handle exception
			HashMap<String, Object> errors = new HashMap<>();
			errors.put("error", true);
			errors.put("message", e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errors);

		}
	}
	
}
