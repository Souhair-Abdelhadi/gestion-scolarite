package com.medaSolutions.entities;

import java.sql.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "enseignants",
uniqueConstraints = {
		@UniqueConstraint(columnNames = "cin")
}
)
public class Enseignant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@NotNull
	@Size(min = 5,max = 30)
	@Column(nullable = false)
	private String nom;
	
	@NotNull
	@Size(min = 5,max = 30)
	@Column(nullable = false)
	private String prenom;
	
	@NotNull
	@Size(min = 8,max = 15)
	@Column(nullable = false)
	private String cin;
	
	@NotNull
	@Column(nullable = false)
	private Date date_nais;
	
	@NotNull
	@Column(nullable = false)
	private Date date_emb;
	
	@NotNull
	@Email
	@Column(nullable = false)
	private String email;
	
	@NotNull
	@Size(min = 10,max = 14)
	@Column(nullable = false,length = 14)
	private String tel;
	
	@OneToOne(mappedBy = "enseignant")
//	@JsonManagedReference
	@JsonIgnore
	private Compte compte;
	
	
	@OneToMany(mappedBy = "enseignant")
	@JsonManagedReference
	private Set<Affectations> affectations = new HashSet<Affectations>();
	
	
	public Enseignant() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Enseignant(@NotNull @Size(min = 5, max = 30) String nom, @NotNull @Size(min = 5, max = 30) String prenom,
			@NotNull @Size(min = 8, max = 15) String cin,
			@NotNull Date date_nais, @NotNull Date date_emb, @NotNull @Email String email,
			@NotNull @Email @Size(min = 10, max = 14) String tel) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.cin = cin;
		this.date_nais = date_nais;
		this.date_emb = date_emb;
		this.email = email;
		this.tel = tel;
	}
	
	

	public Enseignant(int id, @NotNull @Size(min = 5, max = 30) String nom,
				@NotNull @Size(min = 5, max = 30) String prenom, @NotNull @Size(min = 8, max = 15) String cin,
				@NotNull Date date_nais, @NotNull Date date_emb, @NotNull @Email String email,
				@NotNull @Size(min = 10, max = 14) String tel) {
			super();
			this.id = id;
			this.nom = nom;
			this.prenom = prenom;
			this.cin = cin;
			this.date_nais = date_nais;
			this.date_emb = date_emb;
			this.email = email;
			this.tel = tel;
		}

	public Enseignant(@NotNull @Size(min = 5, max = 30) String nom, @NotNull @Size(min = 5, max = 30) String prenom,
			@NotNull @Size(min = 8, max = 15) String cin,
			@NotNull Date date_nais, @NotNull Date date_emb, @NotNull @Email String email,
			@NotNull @Email @Size(min = 10, max = 14) String tel, Compte compte) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.cin = cin;
		this.date_nais = date_nais;
		this.date_emb = date_emb;
		this.email = email;
		this.tel = tel;
		this.compte = compte;
	}

	public Enseignant(int id, @NotNull @Size(min = 5, max = 30) String nom,
			@NotNull @Size(min = 5, max = 30) String prenom, @NotNull @Size(min = 8, max = 15) String cin,
			@NotNull Date date_nais, @NotNull Date date_emb,
			@NotNull @Email String email, @NotNull @Email @Size(min = 10, max = 14) String tel, Compte compte) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.cin = cin;
		this.date_nais = date_nais;
		this.date_emb = date_emb;
		this.email = email;
		this.tel = tel;
		this.compte = compte;
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

	public Date getDate_nais() {
		return date_nais;
	}

	public void setDate_nais(Date date_nais) {
		this.date_nais = date_nais;
	}

	public Date getDate_emb() {
		return date_emb;
	}

	public void setDate_emb(Date date_emb) {
		this.date_emb = date_emb;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Compte getCompte() {
		return compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
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

		
}
