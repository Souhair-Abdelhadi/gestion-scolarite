package com.medaSolutions.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "Etudiants",
		uniqueConstraints = {
				@UniqueConstraint(columnNames = "cin")
		}
		)
public class Etudiant  {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@NotNull
	@Size(min = 5,max = 30)
	private String nom;
	
	@NotNull
	@Size(min = 5,max = 30)
	private String prenom;
	
	@NotNull
	@Size(min = 8,max = 15)
	private String cin;
	
//	@NotNull
//	@Size(min = 8,max = 16)
//	private String mdp;
		
	@OneToOne(mappedBy = "etudiant")
	@JsonIgnore
	private Compte compte;
	
	@OneToMany(mappedBy = "etudiant")
	@JsonManagedReference
	private Set<Notes> notes;
	
	@OneToMany(mappedBy = "etudiant")
	@JsonManagedReference
	private Set<Absence> absences = new HashSet<Absence>();
	
	
	public Etudiant() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Etudiant(int id, String nom, String prenom, String cin) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.cin = cin;
	}




	public Etudiant(@NotNull @Size(min = 5, max = 30) String nom, @NotNull @Size(min = 5, max = 30) String prenom,
			@NotNull @Size(min = 8, max = 15) String cin) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.cin = cin;
	}
	
	



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getNom() {
		return nom;
	}



	public void setNom(String nom) {
		this.nom = nom;
	}



	public String getPrenom() {
		return prenom;
	}



	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}



	public String getCin() {
		return cin;
	}



	public void setCin(String cin) {
		this.cin = cin;
	}

	public Compte getCompte() {
		return compte;
	}



	public void setCompte(Compte compte) {
		this.compte = compte;
	}



	public Set<Absence> getAbsences() {
		return absences;
	}



	public void setAbsences(Set<Absence> absences) {
		this.absences = absences;
	}



	public Set<Notes> getNotes() {
		return notes;
	}



	public void setNotes(Set<Notes> notes) {
		this.notes = notes;
	}



	
	
	
	
	
	
}
