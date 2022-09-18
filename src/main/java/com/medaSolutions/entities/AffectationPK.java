package com.medaSolutions.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;


@Embeddable
public class AffectationPK implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	
	private int enseignantId;
	
	private int moduleId;

	public AffectationPK() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AffectationPK(int enseignantId, int moduleId) {
		super();
		this.enseignantId = enseignantId;
		this.moduleId = moduleId;
	}
	
	

	public AffectationPK(int id, int enseignantId, int moduleId) {
		super();
		this.id = id;
		this.enseignantId = enseignantId;
		this.moduleId = moduleId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEnseignantId() {
		return enseignantId;
	}

	public void setEnseignantId(int enseignantId) {
		this.enseignantId = enseignantId;
	}

	public int getModuleId() {
		return moduleId;
	}

	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(enseignantId, moduleId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AffectationPK other = (AffectationPK) obj;
		return enseignantId == other.enseignantId && moduleId == other.moduleId;
	}
	
	
	
	
}
