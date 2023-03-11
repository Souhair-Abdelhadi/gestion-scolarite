package com.medaSolutions.entities.pojo;

import java.sql.Date;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EtudiantPojo {
	
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
	
	@Size(min = 8,max = 16)
	private String mdp;
	
	@NotNull
	@Size(min = 8,max = 15)
	private String cin;
	
	@NotNull
	@Size(min = 8,max = 15)
	private String cne;
	
	@NotNull
	@Size(min = 8,max = 15)
	private String immatriculation;
	
	@NotNull
	@Size(min = 10,max = 10)
	private String tel;
	
	@NotNull
	@Email
	private String email;
	
	@NotNull
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date dateNaissance;
	
	private int filiereId;
	
	
	public EtudiantPojo(@NotNull @Size(min = 5, max = 30) String nom, @NotNull @Size(min = 5, max = 30) String prenom,
			@NotNull @Size(min = 5, max = 30) String niveau,  @Size(min = 8, max = 16) String mdp,
			@NotNull @Size(min = 8, max = 15) String cin, @NotNull @Size(min = 8, max = 15) String cne,
			@NotNull @Size(min = 8, max = 15) String immatriculation, @NotNull @Size(min = 10, max = 10) String tel,
			@NotNull @Email String email, @NotNull Date dateNaissance) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.niveau = niveau;
		this.mdp = mdp;
		this.cin = cin;
		this.cne = cne;
		this.immatriculation = immatriculation;
		this.tel = tel;
		this.email = email;
		this.dateNaissance = dateNaissance;
	}





	public EtudiantPojo(int id, @NotNull @Size(min = 5, max = 30) String nom,
			@NotNull @Size(min = 5, max = 30) String prenom, @NotNull @Size(min = 5, max = 30) String niveau,
			 @Size(min = 8, max = 16) String mdp, @NotNull @Size(min = 8, max = 15) String cin,
			@NotNull @Size(min = 8, max = 15) String cne, @NotNull @Size(min = 8, max = 15) String immatriculation,
			@NotNull @Size(min = 10, max = 10) String tel, @NotNull @Email String email, @NotNull Date dateNaissance) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.niveau = niveau;
		this.mdp = mdp;
		this.cin = cin;
		this.cne = cne;
		this.immatriculation = immatriculation;
		this.tel = tel;
		this.email = email;
		this.dateNaissance = dateNaissance;
	}



	public EtudiantPojo(@NotNull @Size(min = 5, max = 30) String nom, @NotNull @Size(min = 5, max = 30) String prenom,
			 @Size(min = 8, max = 16) String mdp, @NotNull @Size(min = 8, max = 15) String cin,
			@NotNull @Size(min = 8, max = 15) String cne, @NotNull @Size(min = 8, max = 15) String immatriculation,
			@NotNull @Size(min = 8, max = 15) String tel, @NotNull @Size(min = 8, max = 15) String email,
			@NotNull Date dateNaissance) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.mdp = mdp;
		this.cin = cin;
		this.cne = cne;
		this.immatriculation = immatriculation;
		this.tel = tel;
		this.email = email;
		this.dateNaissance = dateNaissance;
	}





	public EtudiantPojo(int id, @NotNull @Size(min = 5, max = 30) String nom,
			@NotNull @Size(min = 5, max = 30) String prenom, @NotNull @Size(min = 3, max = 10) String niveau,
			@NotNull @Size(min = 8, max = 15) String cin, @NotNull @Size(min = 8, max = 15) String cne,
			@NotNull @Size(min = 8, max = 15) String immatriculation, @NotNull @Size(min = 10, max = 10) String tel,
			@NotNull @Email String email, @NotNull Date dateNaissance) {
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
	}





	public EtudiantPojo(int id, @NotNull @Size(min = 5, max = 30) String nom,
			@NotNull @Size(min = 5, max = 30) String prenom, @NotNull @Size(min = 3, max = 10) String niveau,
			@NotNull @Size(min = 8, max = 15) String cin, @NotNull @Size(min = 8, max = 15) String cne,
			@NotNull @Size(min = 8, max = 15) String immatriculation, @NotNull @Size(min = 10, max = 10) String tel,
			@NotNull @Email String email, @NotNull Date dateNaissance, int filiereId) {
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
		this.filiereId = filiereId;
	}

	
	


//	public int getId() {
//		return id;
//	}
//
//
//
//	public void setId(int id) {
//		this.id = id;
//	}
//
//
//
//	public String getNom() {
//		return nom;
//	}
//
//
//
//	public void setNom(String nom) {
//		this.nom = nom;
//	}
//
//
//
//	public String getPrenom() {
//		return prenom;
//	}
//
//
//
//	public void setPrenom(String prenom) {
//		this.prenom = prenom;
//	}
//
//
//
//	public String getMdp() {
//		return mdp;
//	}
//
//
//
//	public void setMdp(String mdp) {
//		this.mdp = mdp;
//	}
//
//
//
//	public String getCin() {
//		return cin;
//	}
//
//
//
//	public void setCin(String cin) {
//		this.cin = cin;
//	}
//
//
//
//	public String getCne() {
//		return cne;
//	}
//
//
//
//	public void setCne(String cne) {
//		this.cne = cne;
//	}
//
//
//
//	public String getImmatriculation() {
//		return immatriculation;
//	}
//
//
//
//	public void setImmatriculation(String immatriculation) {
//		this.immatriculation = immatriculation;
//	}
//
//
//
//	public String getTel() {
//		return tel;
//	}
//
//
//
//	public void setTel(String tel) {
//		this.tel = tel;
//	}
//
//
//
//	public String getEmail() {
//		return email;
//	}
//
//
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//
//
//	public Date getDateNaissance() {
//		return dateNaissance;
//	}
//
//
//
//	public void setDateNaissance(Date dateNaissance) {
//		this.dateNaissance = dateNaissance;
//	}
//
//
//	public String getNiveau() {
//		return niveau;
//	}
//
//
//	public void setNiveau(String niveau) {
//		this.niveau = niveau;
//	}
//
//

	
	
	

}
