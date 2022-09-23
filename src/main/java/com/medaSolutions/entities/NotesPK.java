package com.medaSolutions.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class NotesPK implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	
	private int etudiantId;
	
	private int moduleId;

	public NotesPK() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NotesPK(int id, int etudiantId, int moduleId) {
		super();
		this.id = id;
		this.etudiantId = etudiantId;
		this.moduleId = moduleId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEtudiantId() {
		return etudiantId;
	}

	public void setEtudiantId(int etudiantId) {
		this.etudiantId = etudiantId;
	}

	public int getModuleId() {
		return moduleId;
	}

	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(etudiantId, id, moduleId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NotesPK other = (NotesPK) obj;
		return etudiantId == other.etudiantId && id == other.id && moduleId == other.moduleId;
	}

	
	
	
}
