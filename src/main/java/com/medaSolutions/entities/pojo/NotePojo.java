package com.medaSolutions.entities.pojo;

import java.sql.Date;

import javax.validation.constraints.NotNull;

public class NotePojo {

	private int id;
	
	private int etudiant_id;
	
	private int module_id;
	
	private float note;

	public NotePojo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NotePojo(@NotNull int etudiant_id, @NotNull int module_id, float note) {
		super();
		this.etudiant_id = etudiant_id;
		this.module_id = module_id;
		this.note = note;
	}

	

	public NotePojo(@NotNull float note) {
		super();
		this.note = note;
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

	public float getNote() {
		return note;
	}

	public void setNote(float note) {
		this.note = note;
	}


	
	
	
}
