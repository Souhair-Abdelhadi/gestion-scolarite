package com.medaSolutions.service;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medaSolutions.entities.Compte;
import com.medaSolutions.repository.CompteRepository;
import com.medaSolutions.repository.NoteRepo;
import com.medaSolutions.security.jwt.DecodeToken;
import com.medaSolutions.security.jwt.JwtTokenFilter;
import com.medaSolutions.security.jwt.JwtTokenUtil;

@RestController
public class GlobalService {

		
	@Autowired
	private CompteRepository compteRepo;
		
	@Autowired
	private NoteRepo noteRepo;
	
	@Autowired
	private JwtTokenUtil jwtU;
	
	@GetMapping(value = "/user/compte")
	public @ResponseBody Object getMyCompte(@RequestHeader(name = "Authorization") String token){
		try {
//			System.out.println("my id : "+jswtUtil.getCompteId(token));
			int compteId = jwtU.getCompteId(token);
			System.out.println("my compte id "+ compteId);
			if(compteId == 0 ) {
				HashMap<String, Object> errors = new HashMap<>();
				errors.put("error", true);
				errors.put("message", "Authorization token is required ");
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errors);
			}
			Optional<Compte> cmpt = compteRepo.findById(compteId);
			if(cmpt.isPresent()) {
				return ResponseEntity.status(HttpStatus.OK).body(cmpt);
			}
			HashMap<String, Object> errors = new HashMap<>();
			errors.put("error", true);
			errors.put("message", "compte does not exist");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			HashMap<String, Object> errors = new HashMap<>();
			errors.put("error", true);
			errors.put("message", e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errors);
		}
		
	}
	
	
	@RolesAllowed(value = "ROLE_ETUDIANT")
	@GetMapping(value = "/user/notes")
	public @ResponseBody Object getMyNotes(@RequestHeader(name = "Authorization") String token){
		try {
//			System.out.println("my id : "+jswtUtil.getCompteId(token));
			int compteId = jwtU.getCompteId(token);
			System.out.println("my compte id "+ compteId);
			if(compteId == 0 ) {
				HashMap<String, Object> errors = new HashMap<>();
				errors.put("error", true);
				errors.put("message", "Authorization token is required ");
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errors);
			}
			Optional<Compte> cmpt = compteRepo.findById(compteId);
			if(cmpt.isPresent()) {
				int etudiantId = cmpt.get().getEtudiant().getId();
				List<Map<String, Object>> ModulesNote = noteRepo.findEtudiantNoteByModule(etudiantId);
				return ResponseEntity.status(HttpStatus.OK).body(ModulesNote);
			}
			HashMap<String, Object> errors = new HashMap<>();
			errors.put("error", true);
			errors.put("message", "compte does not exist");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			HashMap<String, Object> errors = new HashMap<>();
			errors.put("error", true);
			errors.put("message", e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errors);
		}
		
	}
	
	
}
