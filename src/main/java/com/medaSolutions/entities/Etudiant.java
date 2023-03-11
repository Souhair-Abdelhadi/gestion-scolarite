package com.medaSolutions.entities;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
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
	@Size(min = 3,max = 10)
	private String niveau;
	
	@NotNull
	@Size(min = 8,max = 15)
	@Column(unique = true)
	private String cin;
	
	@NotNull
	@Size(min = 8,max = 15)
	@Column(unique = true)
	private String cne;
	
	@NotNull
	@Size(min = 8,max = 15)
	@Column(unique = true)
	private String immatriculation;
	
	@NotNull
	@Size(min = 8,max = 15)
	@Column(unique = true)
	private String tel;
	
	@NotNull
	@Size(min = 10,max = 50)
	@Column(unique = true)
	private String email;
	
	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dateNaissance;
		
	@OneToOne(mappedBy = "etudiant")
	@JsonIgnore
	private Compte compte;
	
	@OneToMany(mappedBy = "etudiant")
	@JsonManagedReference
	private Set<Notes> notes = new HashSet<>();
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "filiere_id")
	@JsonBackReference
	private Filiere filiere;
	
	
	public Etudiant() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	

	public Etudiant(@NotNull @Size(min = 5, max = 30) String nom, @NotNull @Size(min = 5, max = 30) String prenom,
			@NotNull @Size(min = 5, max = 30) String niveau, @NotNull @Size(min = 8, max = 15) String cin,
			@NotNull @Size(min = 8, max = 15) String cne, @NotNull @Size(min = 8, max = 15) String immatriculation,
			@NotNull @Size(min = 8, max = 15) String tel, @NotNull @Size(min = 8, max = 15) String email,
			@NotNull Date dateNaissance) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.niveau = niveau;
		this.cin = cin;
		this.cne = cne;
		this.immatriculation = immatriculation;
		this.tel = tel;
		this.email = email;
		this.dateNaissance = dateNaissance;
	}




	public Etudiant(@NotNull @Size(min = 5, max = 30) String nom, @NotNull @Size(min = 5, max = 30) String prenom,
			@NotNull @Size(min = 5, max = 30) String niveau, @NotNull @Size(min = 8, max = 15) String cin,
			@NotNull @Size(min = 8, max = 15) String cne, @NotNull @Size(min = 8, max = 15) String immatriculation,
			@NotNull @Size(min = 8, max = 15) String tel, @NotNull @Size(min = 8, max = 15) String email,
			@NotNull Date dateNaissance, Compte compte, Set<Notes> notes) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.niveau = niveau;
		this.cin = cin;
		this.cne = cne;
		this.immatriculation = immatriculation;
		this.tel = tel;
		this.email = email;
		this.dateNaissance = dateNaissance;
		this.compte = compte;
		this.notes = notes;
	}





	public Etudiant(int id, @NotNull @Size(min = 5, max = 30) String nom,
			@NotNull @Size(min = 5, max = 30) String prenom, @NotNull @Size(min = 5, max = 30) String niveau,
			@NotNull @Size(min = 8, max = 15) String cin, @NotNull @Size(min = 8, max = 15) String cne,
			@NotNull @Size(min = 8, max = 15) String immatriculation, @NotNull @Size(min = 8, max = 15) String tel,
			@NotNull @Size(min = 8, max = 15) String email, @NotNull Date dateNaissance, Compte compte,
			Set<Notes> notes) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.niveau = niveau;
		this.cin = cin;
		this.cne = cne;
		this.immatriculation = immatriculation;
		this.tel = tel;
		this.email = email;
		this.dateNaissance = dateNaissance;
		this.compte = compte;
		this.notes = notes;
	}


	public Etudiant(@NotNull @Size(min = 5, max = 30) String nom, @NotNull @Size(min = 5, max = 30) String prenom,
			@NotNull @Size(min = 3, max = 10) String niveau, @NotNull @Size(min = 8, max = 15) String cin,
			@NotNull @Size(min = 8, max = 15) String cne, @NotNull @Size(min = 8, max = 15) String immatriculation,
			@NotNull @Size(min = 8, max = 15) String tel, @NotNull @Size(min = 10, max = 50) String email,
			@NotNull Date dateNaissance, Filiere filiere) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.niveau = niveau;
		this.cin = cin;
		this.cne = cne;
		this.immatriculation = immatriculation;
		this.tel = tel;
		this.email = email;
		this.dateNaissance = dateNaissance;
		this.filiere = filiere;
	}
	
	public Etudiant(int id, @NotNull @Size(min = 5, max = 30) String nom,
			@NotNull @Size(min = 5, max = 30) String prenom, @NotNull @Size(min = 3, max = 10) String niveau,
			@NotNull @Size(min = 8, max = 15) String cin, @NotNull @Size(min = 8, max = 15) String cne,
			@NotNull @Size(min = 8, max = 15) String immatriculation, @NotNull @Size(min = 8, max = 15) String tel,
			@NotNull @Size(min = 10, max = 50) String email, @NotNull Date dateNaissance,
			Filiere filiere) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.niveau = niveau;
		this.cin = cin;
		this.cne = cne;
		this.immatriculation = immatriculation;
		this.tel = tel;
		this.email = email;
		this.dateNaissance = dateNaissance;
		this.filiere = filiere;
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

	public String getCne() {
		return cne;
	}



	public void setCne(String cne) {
		this.cne = cne;
	}



	public String getImmatriculation() {
		return immatriculation;
	}



	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
	}



	public String getTel() {
		return tel;
	}



	public void setTel(String tel) {
		this.tel = tel;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public Date getDateNaissance() {
		return dateNaissance;
	}



	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}



	public Set<Notes> getNotes() {
		return notes;
	}



	public void setNotes(Set<Notes> notes) {
		this.notes = notes;
	}



	public String getNiveau() {
		return niveau;
	}



	public void setNiveau(String niveau) {
		this.niveau = niveau;
	}




	public Filiere getFiliere() {
		return filiere;
	}




	public void setFiliere(Filiere filiere) {
		this.filiere = filiere;
	}
		
	
	
}
