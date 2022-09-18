package com.medaSolutions.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Embeddable
public class AbsencePK implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	
	private int etudiantId;
	
	private int moduleId;

	public AbsencePK() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AbsencePK(int etudiantId, int moduleId) {
		super();
		this.etudiantId = etudiantId;
		this.moduleId = moduleId;
	}

	public AbsencePK(int id, int etudiantId, int moduleId) {
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

	@Override
	public int hashCode() {
		return Objects.hash(etudiantId, moduleId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbsencePK other = (AbsencePK) obj;
		return etudiantId == other.etudiantId && moduleId == other.moduleId;
	}

	
	
}
