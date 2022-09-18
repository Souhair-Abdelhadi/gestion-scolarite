package com.medaSolutions.authentication;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.medaSolutions.entities.Compte;
import com.medaSolutions.security.jwt.JwtTokenUtil;

@RestController
public class AuthApi {

	@Autowired
	AuthenticationManager authManager;
	
	@Autowired
	JwtTokenUtil jwtTokenUtil; 

	@PostMapping(value="/login")
	public ResponseEntity<?> login(@RequestBody @Valid AuthRequest request){
		try {
			Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getCin(),
					request.getMdp()));
			Compte compte = (Compte) authentication.getPrincipal();
			AuthResponse response = new AuthResponse(compte.getCin(),jwtTokenUtil.generateAccessToken(compte));
			return ResponseEntity.ok(response);
		} catch (BadCredentialsException e) {
			// TODO: handle exception 
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}
	
	
}
