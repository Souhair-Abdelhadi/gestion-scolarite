package com.medaSolutions.entities.pojo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EtudiantPojo {
	
	private int id;
	
	@NotNull
	@Size(max = 18,min = 8)
	private String cin;
	
	@NotNull
	@Size(max = 16,min = 8)
	private String mdp;
	
	@NotNull
	@Size(max = 15,min = 4)
	private String nom;
	
	@NotNull
	@Size(max = 15,min = 4)
	private String prenom;
	
	public EtudiantPojo() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public EtudiantPojo(@NotNull @Size(max = 18, min = 8) String cin, @NotNull @Size(max = 16, min = 8) String mdp,
			@NotNull @Size(max = 15, min = 8) String nom, @NotNull @Size(max = 15, min = 8) String prenom) {
		super();
		this.cin = cin;
		this.mdp = mdp;
		this.nom = nom;
		this.prenom = prenom;
	}

	

	public EtudiantPojo(int id, @NotNull @Size(max = 18, min = 8) String cin,
			@NotNull @Size(max = 16, min = 8) String mdp, @NotNull @Size(max = 15, min = 8) String nom,
			@NotNull @Size(max = 15, min = 8) String prenom) {
		super();
		this.id = id;
		this.cin = cin;
		this.mdp = mdp;
		this.nom = nom;
		this.prenom = prenom;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	
	

}
