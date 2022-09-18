package com.medaSolutions.entities.pojo;

import java.sql.Date;

import javax.validation.constraints.NotNull;

public class AffectationPojo {

	private int id;
	
//	@NotNull
	private int idEnseignant;
	
//	@NotNull
	private int idModule;
	
//	@NotNull
	private Date date_affectation;
		
	public AffectationPojo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AffectationPojo(int id,  Date date_affectation) {
		super();
		this.id = id;
		this.date_affectation = date_affectation;
	}
	
	

	public AffectationPojo(int id,  int idEnseignant,  int idModule,  Date date_affectation) {
		super();
		this.id = id;
		this.idEnseignant = idEnseignant;
		this.idModule = idModule;
		this.date_affectation = date_affectation;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdEnseignant() {
		return idEnseignant;
	}

	public void setIdEnseignant(int idEnseignant) {
		this.idEnseignant = idEnseignant;
	}

	public int getIdModule() {
		return idModule;
	}

	public void setIdModule(int idModule) {
		this.idModule = idModule;
	}

	public Date getDate_affectation() {
		return date_affectation;
	}

	public void setDate_affectation(Date date_affectation) {
		this.date_affectation = date_affectation;
	}

	
	
	
	
	
}
