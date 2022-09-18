package com.medaSolutions.conf;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.medaSolutions.repository.CompteRepository;
import com.medaSolutions.security.jwt.JwtTokenFilter;
import com.medaSolutions.security.jwt.JwtTokenUtil;

//@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = false,securedEnabled = false,jsr250Enabled = true)
public class Conf extends WebSecurityConfigurerAdapter {

	@Autowired
	private CompteRepository compteRepo;
	
	@Autowired
	private JwtTokenFilter jwtTokenfilter;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(username -> compteRepo.findByCin(username)
				.orElseThrow(()-> new UsernameNotFoundException("Compte avec le cin "+username+" n'exsite pas"))
				);
	}
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManager();
	}

		
	@Override
	protected void configure(HttpSecurity http) throws Exception{
					
		http.csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.authorizeRequests()
//		.anyRequest().permitAll();
		.antMatchers("/login").permitAll()
//		.antMatchers("/comptes").permitAll()
		.anyRequest().authenticated();
//		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.exceptionHandling().authenticationEntryPoint((request,response,ex)->{
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED,ex.getMessage());
			System.out.println(ex.getMessage());
		});
		
//		http.authorizeRequests().antMatchers("/login").permitAll()
//		.anyRequest().authenticated();
		
		http.addFilterBefore(jwtTokenfilter, UsernamePasswordAuthenticationFilter.class); 
		
	}
	
}
