package com.medaSolutions.entities;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(
		name = "modules",
		uniqueConstraints = {
				@UniqueConstraint(columnNames = "nom")
		}
		)
public class Module {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NonNull
	@Column(nullable = false,length = 30)
	@Size(min = 5,max = 25)
	private String nom;
	
	@NonNull
	@Column(nullable = false,length = 20)
	private int duree;
	
	@NonNull
	@Column(nullable = false,length = 10)
	private int coef;
	
	@JsonIgnore
	@OneToMany(mappedBy = "module",fetch = FetchType.LAZY)
	public Set<Affectations> affectations = new HashSet<Affectations>();
	
	
	@JsonIgnore
	@OneToMany(mappedBy = "module",fetch = FetchType.LAZY)
	public Set<Absence> absences = new HashSet<Absence>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "module",fetch = FetchType.LAZY)
	public Set<Notes> notes = new HashSet<Notes>();

	public Module() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Module(@Size(min = 5, max = 25) String nom, int duree, int coef) {
		super();
		this.nom = nom;
		this.duree = duree;
		this.coef = coef;
	}

	public Module(int id, @Size(min = 5, max = 25) String nom, int duree, int coef) {
		super();
		this.id = id;
		this.nom = nom;
		this.duree = duree;
		this.coef = coef;
	}

	
	
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public int getCoef() {
		return coef;
	}

	public void setCoef(int coef) {
		this.coef = coef;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Set<Affectations> getAffectations() {
		return affectations;
	}

	public void setAffectations(Set<Affectations> affectations) {
		this.affectations = affectations;
	}

	@Override
	public int hashCode() {
		return Objects.hash(coef, duree, nom);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Module other = (Module) obj;
		return coef == other.coef && duree == other.duree && Objects.equals(nom, other.nom);
	}
	
	

}
