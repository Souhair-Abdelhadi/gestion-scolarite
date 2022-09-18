package com.medaSolutions.entities;

public class CompteRole {

	private String cin;
	private String roleName;
	
	
	public CompteRole() {
		super();
		// TODO Auto-generated constructor stub
	}


	public CompteRole(String cin, String roleName) {
		super();
		this.cin = cin;
		this.roleName = roleName;
	}


	public String getCin() {
		return cin;
	}


	public void setCin(String cin) {
		this.cin = cin;
	}


	public String getRoleName() {
		return roleName;
	}


	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	
	
	
}
