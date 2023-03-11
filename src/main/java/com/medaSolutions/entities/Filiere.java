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
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;




@Entity
public class Filiere {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@NotNull
	private String nom;
	
	@NotNull
	private float fraisInscription;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name = "filiere_modules",
			joinColumns = @JoinColumn(name = "filiere_id"),
			inverseJoinColumns = @JoinColumn(name = "module_id")
			)
	private Set<Module> modules = new HashSet<>();
	
	@OneToMany(mappedBy = "filiere")
	@JsonManagedReference
	private Set<Etudiant> etudiant = new HashSet<>();

	

	public Filiere() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Filiere(@NotNull String nom, @NotNull float fraisInscription) {
		super();
		this.nom = nom;
		this.fraisInscription = fraisInscription;
	}

	public Filiere(int id, @NotNull String nom, @NotNull float fraisInscription) {
		super();
		this.id = id;
		this.nom = nom;
		this.fraisInscription = fraisInscription;
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

	public float getFraisInscription() {
		return fraisInscription;
	}

	public void setFraisInscription(float fraisInscription) {
		this.fraisInscription = fraisInscription;
	}

	public Set<Module> getModules() {
		return modules;
	}

	public void setModules(Set<Module> modules) {
		this.modules = modules;
	}

	public Set<Etudiant> getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(Set<Etudiant> etudiant) {
		this.etudiant = etudiant;
	}
	
	
	
	
	
}
