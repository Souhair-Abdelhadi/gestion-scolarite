package com.medaSolutions.entities.pojo;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FilierePojo {

	@NotNull
	private int id;
	
	@NotNull
	private int idModule;
	
	
}
