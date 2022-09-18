package com.medaSolutions.security.jwt;

import java.io.IOException;
import java.rmi.ServerException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.medaSolutions.entities.Compte;
import com.medaSolutions.entities.Role;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;


@Component
public class JwtTokenFilter extends OncePerRequestFilter {

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response,
			FilterChain filterChain) throws IOException, ServletException{
		
		if(!hasAuthorizationHeader(request)) {
			filterChain.doFilter(request, response);
			return;
		}
		String accessToken = getAccessToken(request);
		System.out.println("access token : "+accessToken);
		
		if(!jwtTokenUtil.validateAccessToken(accessToken)) {
			filterChain.doFilter(request, response);
			System.out.println("stopped at validate acces token");
			return;
		}
		
		setAuthenticationToken(accessToken,request);
		filterChain.doFilter(request, response);
	}
	
	
	private void setAuthenticationToken(String accessToken, HttpServletRequest request) {
		UserDetails compteDetails = getUserDetails(accessToken);
		System.out.println("role : "+compteDetails.getAuthorities());
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(compteDetails,null,compteDetails.getAuthorities());
		authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	private UserDetails getUserDetails(String accessToken) {
		Compte compteDetails = new Compte();
		Claims claims = jwtTokenUtil.parseClaims(accessToken);
		String subject = (String) claims.get(Claims.SUBJECT);
	    String roles = (String) claims.get("roles");
	     System.out.println("user details : "+ roles);
	    roles = roles.replace("[", "").replace("]", "");
	    String[] roleNames = roles.split(",");
	     
	    for (String roleName : roleNames) {
	        compteDetails.addRole(new Role(roleName));;
	    }
		
		//String[] subjectArray = jwtTokenUtil.getSubject(accessToken).split(",");
	    String[] jwtSybject = subject.split(",");
		compteDetails.setId(Integer.parseInt(jwtSybject[0]));
		compteDetails.setCin(jwtSybject[1]);
		return compteDetails;
	}

	private boolean hasAuthorizationHeader(HttpServletRequest request) {
		String header = request.getHeader("Authorization");
		if(ObjectUtils.isEmpty(header) || !header.startsWith("Bearer")) {
			return false;
		}
		return true;
	}

	private String getAccessToken(HttpServletRequest request) {
		String header = request.getHeader("Authorization");
		String token = header.split(" ")[1];
		return token.trim();
	}
}
