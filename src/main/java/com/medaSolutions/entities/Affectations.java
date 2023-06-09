package com.medaSolutions.entities;

import java.sql.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "affectations",
		uniqueConstraints = {
				@UniqueConstraint(columnNames = "id")
		}
		)
public class Affectations {

	@EmbeddedId
	private AffectationPK id;
	
	
	@ManyToOne
	@MapsId("professeurId")
	@JoinColumn(name = "professeur_id")
	@JsonBackReference
	private Professeur professeur;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("moduleId")
	@JoinColumn(name = "module_id")
	private Module module;

	private Date date_affectation;
	
	public Affectations() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Affectations(AffectationPK id, Professeur professeur, Module module, Date date_affectation) {
		super();
		this.id = id;
		this.professeur = professeur;
		this.module = module;
		this.date_affectation = date_affectation;
	}



	public AffectationPK getId() {
		return id;
	}

	public void setId(AffectationPK id) {
		this.id = id;
	}

	

	public Professeur getProfesseur() {
		return professeur;
	}



	public void setProfesseur(Professeur professeur) {
		this.professeur = professeur;
	}



	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}



	public Date getDate_affectation() {
		return date_affectation;
	}



	public void setDate_affectation(Date date_affectation) {
		this.date_affectation = date_affectation;
	}
	
	
	
	
	
	
}
