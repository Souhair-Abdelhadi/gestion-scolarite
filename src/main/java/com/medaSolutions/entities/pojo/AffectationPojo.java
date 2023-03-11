package com.medaSolutions.entities.pojo;

import java.sql.Date;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AffectationPojo {

	private int id;
	
	@NotNull
	private int idProfesseur;
	
	@NotNull
	private int idModule;
	
	@NotNull
	private Date date_affectation;
		

}