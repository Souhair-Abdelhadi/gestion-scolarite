package com.medaSolutions.entities.pojo;

import java.sql.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EnseignantPojo {

	
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
	
	@NotNull
	@Size(min = 8,max = 16)
	private String mdp;
	
	@NotNull
	private Date date_nais;
	
	@NotNull
	private Date date_emb;
	
	@NotNull
	@Email
	private String email;
	
	@NotNull
	@Size(min = 10,max = 14)
	private String tel;

	public EnseignantPojo(int id, @NotNull @Size(min = 5, max = 30) String nom,
			@NotNull @Size(min = 5, max = 30) String prenom, @NotNull @Size(min = 8, max = 15) String cin,
			@NotNull @Size(min = 8, max = 16) String mdp,
			@NotNull Date date_nais, @NotNull Date date_emb, @NotNull @Email String email,
			@NotNull @Size(min = 10, max = 14) String tel) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.cin = cin;
		this.mdp = mdp;
		this.date_nais = date_nais;
		this.date_emb = date_emb;
		this.email = email;
		this.tel = tel;
	}

	public EnseignantPojo(@NotNull @Size(min = 5, max = 30) String nom, @NotNull @Size(min = 5, max = 30) String prenom,
			@NotNull @Size(min = 8, max = 15) String cin, String mdp, @NotNull Date date_nais, @NotNull Date date_emb,
			@NotNull @Email String email, @NotNull @Size(min = 10, max = 14) String tel) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.cin = cin;
		this.mdp = mdp;
		this.date_nais = date_nais;
		this.date_emb = date_emb;
		this.email = email;
		this.tel = tel;
	}

	public EnseignantPojo(@NotNull @Size(min = 5, max = 30) String nom, @NotNull @Size(min = 5, max = 30) String prenom,
			@NotNull @Size(min = 8, max = 15) String cin, @NotNull Date date_nais, @NotNull Date date_emb,
			@NotNull @Email String email, @NotNull @Size(min = 10, max = 14) String tel) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.cin = cin;
		this.date_nais = date_nais;
		this.date_emb = date_emb;
		this.email = email;
		this.tel = tel;
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

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
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

	
	

	
	
	
}
