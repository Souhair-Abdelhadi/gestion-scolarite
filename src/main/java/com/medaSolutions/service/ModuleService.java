package com.medaSolutions.service;

import java.util.HashMap;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.medaSolutions.entities.Compte;
import com.medaSolutions.entities.Module;
import com.medaSolutions.repository.ModuleRepo;

@RestController
public class ModuleService {

	@Autowired
	private ModuleRepo moduleRepo;
	
	
	@RolesAllowed("ROLE_SECRETAIRE")
	@GetMapping(value = "/modules")
	public List<Module> getModules(){
		return moduleRepo.findAll();
	}
	
	@RolesAllowed("ROLE_SECRETAIRE")
	@PostMapping(value = "/addModule")
	public Object addCompte(@RequestBody @Valid Module module,BindingResult bindRes){
		
		try {
			if(bindRes.hasErrors()) {
				HashMap<String, Object> errors = new HashMap<>();
				errors.put("error", true);
				for(FieldError fe:bindRes.getFieldErrors()) {
					errors.put(fe.getField(), fe.getDefaultMessage());
				}
				return errors;
			}
			return moduleRepo.save(module);
		} catch (Exception e) {
			// TODO: handle exception
			HashMap<String, Object> errors = new HashMap<>();
			errors.put("error", true);
			errors.put("message", e);
			return errors;
		}
		
	}
	
	@RolesAllowed(value = {"ROLE_SECRETAIRE"})
	@PutMapping(value = "/updateModule")
	public Object updateCompte(@RequestBody @Valid Module module,BindingResult bindRes){
		
		try {
			if(bindRes.hasErrors()) {
				HashMap<String, Object> errors = new HashMap<>();
				errors.put("error", true);
				for(FieldError fe:bindRes.getFieldErrors()) {
					errors.put(fe.getField(), fe.getDefaultMessage());
				}
				return errors;
			}
			return moduleRepo.save(module);
		} catch (Exception e) {
			// TODO: handle exception
			HashMap<String, Object> errors = new HashMap<>();
			errors.put("error", true);
			errors.put("message", e.getMessage());
			return errors;
		}
		
	}
	
	
}
