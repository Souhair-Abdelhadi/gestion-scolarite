package com.medaSolutions.authentication;

import org.hibernate.validator.constraints.Length;

public class AuthRequest {
	
	@Length(min = 8,max = 15)
	private String cin;
	
	@Length(min = 8,max = 15)
	private String mdp;

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	
	
	
}
