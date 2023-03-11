package com.medaSolutions.entities;

import java.util.Collection;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.lang.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "comptes",
	uniqueConstraints = {
			@UniqueConstraint(columnNames = "cin")
	}
	)
public class Compte implements UserDetails {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	@Size(min = 8,max = 15)
	@Column(nullable = false,length = 255,unique = true)
	private String cin;
	
	@NotNull
	@Size(min = 8,max = 255)
	private String mdp;
	
	private boolean activated;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "etudiant_id",referencedColumnName = "id")
	@Nullable
	private Etudiant etudiant;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "secretaire_id",referencedColumnName = "id")
	@Nullable
	private Secretaire secretaire;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "professeur_id",referencedColumnName = "id")
	@Nullable
	private Professeur professeur;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name = "compte_role",
			joinColumns = @JoinColumn(name = "compte_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id")
			)
	private Set<Role> roles = new HashSet<>();

	
	public Compte() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Compte(@NotNull @Size(min = 8, max = 15) String cin, @NotNull @Size(min = 8, max = 255) String mdp,
			boolean activated, Etudiant etudiant, Secretaire secretaire, Professeur professeur, Set<Role> roles) {
		super();
		this.cin = cin;
		this.mdp = mdp;
		this.activated = activated;
		this.etudiant = etudiant;
		this.secretaire = secretaire;
		this.professeur = professeur;
		this.roles = roles;
	}

	public Compte(int id, @NotNull @Size(min = 8, max = 15) String cin, @NotNull @Size(min = 8, max = 255) String mdp,
			boolean activated, Etudiant etudiant, Secretaire secretaire, Professeur professeur, Set<Role> roles) {
		super();
		this.id = id;
		this.cin = cin;
		this.mdp = mdp;
		this.activated = activated;
		this.etudiant = etudiant;
		this.secretaire = secretaire;
		this.professeur = professeur;
		this.roles = roles;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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
	
	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}
	

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	public void addRole(Role role) {
		roles.add(role);
	}

	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            for (Role role : roles) {
				authorities.add(new SimpleGrantedAuthority(role.getName()));
			}
        
        return authorities;
    }

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.mdp;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.cin;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return this.activated;
	}



	public Etudiant getEtudiant() {
		return etudiant;
	}



	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}



	public Secretaire getSecretaire() {
		return secretaire;
	}



	public void setSecretaire(Secretaire secretaire) {
		this.secretaire = secretaire;
	}


	public Professeur getProfesseur() {
		return professeur;
	}


	public void setProfesseur(Professeur professeur) {
		this.professeur = professeur;
	}



	

	
	
	
}
