package com.medaSolutions.entities.sequence;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class AbsenceSequence {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private int id;
	
	
	private Date date;


	public AbsenceSequence() {
		super();
		// TODO Auto-generated constructor stub
	}


	public AbsenceSequence(int id, Date date) {
		super();
		this.id = id;
		this.date = date;
	}


	public AbsenceSequence(Date date) {
		super();
		this.date = date;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
	
}
