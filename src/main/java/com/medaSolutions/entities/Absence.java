package com.medaSolutions.entities;

import java.sql.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "Absences",
		uniqueConstraints = {
				@UniqueConstraint(columnNames = "id")
		}
		)
public class Absence {
	
	@EmbeddedId
	private AbsencePK id;
	
	
	@ManyToOne
	@MapsId("etudiantId")
	@JoinColumn(name = "etudiant_id")
	@JsonBackReference
	private Etudiant etudiant;
	
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("moduleId")
	@JoinColumn(name = "module_id")
	private Module module;
	
	
	private Date date_absence;
	
	@Column(nullable = true)
	private String justification;

	public Absence() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Absence(AbsencePK absensePK, Etudiant etudiant, Module module, Date date_absence) {
		super();
		this.id = absensePK;
		this.etudiant = etudiant;
		this.module = module;
		this.date_absence = date_absence;
	}

	public Absence(AbsencePK absensePK, Etudiant etudiant, Module module, Date date_absence, String justification) {
		super();
		this.id = absensePK;
		this.etudiant = etudiant;
		this.module = module;
		this.date_absence = date_absence;
		this.justification = justification;
	}

	public AbsencePK getId() {
		return id;
	}

	public void setId(AbsencePK absensePK) {
		this.id = absensePK;
	}

	public Etudiant getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	public Date getDate_absence() {
		return date_absence;
	}

	public void setDate_absence(Date date_absence) {
		this.date_absence = date_absence;
	}

	public String getJustification() {
		return justification;
	}

	public void setJustification(String justification) {
		this.justification = justification;
	}

	
	
	
}
