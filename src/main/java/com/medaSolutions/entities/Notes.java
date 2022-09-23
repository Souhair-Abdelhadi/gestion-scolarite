package com.medaSolutions.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(
		uniqueConstraints = {
				@UniqueConstraint(columnNames = "id")
		}
		)
public class Notes {

	@EmbeddedId
	private NotesPK id;
	
	@NotNull
	@Column(nullable = true)
	private float note;
	
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

	public Notes() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Notes(NotesPK id, @NotNull float note) {
		super();
		this.id = id;
		this.note = note;
	}

	public Notes(NotesPK id, Etudiant etudiant, Module module, @NotNull float note) {
		super();
		this.id = id;
		this.note = note;
		this.etudiant = etudiant;
		this.module = module;
	}

	public NotesPK getId() {
		return id;
	}

	public void setId(NotesPK id) {
		this.id = id;
	}

	

	public float getNote_ex() {
		return note;
	}

	public void setNote_ex(float note) {
		this.note = note;
	}
	
	
	
}
