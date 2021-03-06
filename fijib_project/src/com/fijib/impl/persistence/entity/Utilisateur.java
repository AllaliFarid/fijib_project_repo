package com.fijib.impl.persistence.entity;

// Generated Aug 21, 2014 11:10:22 PM by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Utilisateur generated by hbm2java
 */
@Entity
@Table(name = "utilisateur", catalog = "db_fijib")
public class Utilisateur implements java.io.Serializable {

	private String cin;
	private String email;
	private String password;
	private String statut;
	private Agentguichet agentguichet;
	private Administrateur administrateur;
	private Set<Role> roles = new HashSet<Role>(0);
	private Client client;

	public Utilisateur() {
	}

	public Utilisateur(String cin) {
		this.cin = cin;
	}

	public Utilisateur(String cin, String email, String password,
			String statut ) {
		this.cin = cin;
		this.email = email;
		this.password = password;
		this.statut = statut;
	 
	}

	@Id
	@Column(name = "CIN", unique = true, nullable = false, length = 254)
	public String getCin() {
		return this.cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	@Column(name = "email", length = 254)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "Password", length = 254)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "statut", length = 254)
	public String getStatut() {
		return this.statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "utilisateur")
	public Agentguichet getAgentguichet() {
		return this.agentguichet;
	}

	public void setAgentguichet(Agentguichet agentguichet) {
		this.agentguichet = agentguichet;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "utilisateur")
	public Administrateur getAdministrateur() {
		return this.administrateur;
	}

	public void setAdministrateur(Administrateur administrateur) {
		this.administrateur = administrateur;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "utilisateur")
	public Set<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "utilisateur")
	public Client getClient() {
		return this.client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

}
