package com.medaSolutions.entities.pojo;

import java.sql.Date;

import javax.validation.constraints.NotNull;

public class AbsencePojo {

	private int id;
	
//	@NotNull
	private int etudiant_id;
	
//	@NotNull
	private int module_id;
	
//	@NotNull
	private Date date_absence;
	
	private String justification;
		
	public AbsencePojo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AbsencePojo(int id, String justification) {
		super();
		this.id = id;
		this.justification = justification;
	}

	public AbsencePojo(int id, Date date_absence) {
		super();
		this.id = id;
		this.date_absence = date_absence;
	}

	public AbsencePojo(int id,  Date date_absence, String justification) {
		super();
		this.id = id;
		this.date_absence = date_absence;
		this.justification = justification;
	}
	

	public AbsencePojo( int etudiant_id,  int module_id,  Date date_absence,
			String justification) {
		super();
		this.etudiant_id = etudiant_id;
		this.module_id = module_id;
		this.date_absence = date_absence;
		this.justification = justification;
	}

	public AbsencePojo( int etudiant_id,  int module_id,  Date date_absence) {
		super();
		this.etudiant_id = etudiant_id;
		this.module_id = module_id;
		this.date_absence = date_absence;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public int getEtudiant_id() {
		return etudiant_id;
	}

	public void setEtudiant_id(int etudiant_id) {
		this.etudiant_id = etudiant_id;
	}

	public int getModule_id() {
		return module_id;
	}

	public void setModule_id(int module_id) {
		this.module_id = module_id;
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
