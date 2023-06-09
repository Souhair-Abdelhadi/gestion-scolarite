package com.medaSolutions.entities.pojo;

import java.sql.Date;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NotePojo {

	private int id;
	
	@NotNull
	private int etudiant_id;
	
	@NotNull
	private int module_id;
	
	@NotNull
	private float note;

	public NotePojo(@NotNull float note) {
		super();
		this.note = note;
	}

	
	
}
