package com.medaSolutions.security.jwt;

import java.util.Date;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.medaSolutions.entities.Compte;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenUtil {

	private static final long  EXPIRE_DURATION = 24 * 60 * 60 * 1000;
	private static final Logger logger = LoggerFactory.getLogger(JwtTokenUtil.class);
	
	@Value("${app.jwt.secret}")
	private String secretKey;
	
	public String generateAccessToken(Compte compte) {
		return Jwts.builder()
				.setSubject(compte.getId()+","+compte.getCin())
				.claim("roles", compte.getRoles().toString())
				.setIssuer("Meda Solutions")
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis()+EXPIRE_DURATION))
				.signWith(SignatureAlgorithm.HS512, secretKey)
				.compact();
				
	}
	
	
	public boolean validateAccessToken(String token) {
		try {
			Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
			System.out.println("access token verified");
			return true;
		} catch (ExpiredJwtException ex) {
			logger.error("JWT expired",ex);
		}
		catch (IllegalArgumentException ex) {
			logger.error("Token is null, empty or has only whitespaces",ex);
		}
		catch (MalformedJwtException ex) {
			logger.error("JWT is invalid",ex);
		}
		catch (UnsupportedJwtException ex) {
			logger.error("JWT is not supported",ex);
		}
		catch (SignatureException ex) {
			logger.error("Signature validation failed",ex);
		}
		return false;
	}
	
	public Claims parseClaims(String token) {
		return Jwts.parser()
				.setSigningKey(secretKey)
				.parseClaimsJws(token)
				.getBody();
	}
	
	public String getSubject(String token) {
		return parseClaims(token).getSubject();
	}
	
	
}