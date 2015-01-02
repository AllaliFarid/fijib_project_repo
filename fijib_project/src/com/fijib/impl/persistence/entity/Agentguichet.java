package com.fijib.impl.persistence.entity;

// Generated Aug 21, 2014 11:10:22 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * Agentguichet generated by hbm2java
 */
@Entity
@Table(name = "agentguichet", catalog = "db_fijib")
public class Agentguichet implements java.io.Serializable {

	private String cin;
	private Utilisateur utilisateur;
	private Agence agence;
	private Date dateRecrutement;
	private String email;
	private String firstName;
	private String lastName;
	private Set<EnvoiNonInscrit> envoiNonInscrits = new HashSet<EnvoiNonInscrit>(
			0);
	private Set<Retrait> retraits = new HashSet<Retrait>(0);
	private Set<EnvoiInscrit> envoiInscrits = new HashSet<EnvoiInscrit>(0);

	public Agentguichet() {
	}

	public Agentguichet(Utilisateur utilisateur, Agence agence) {
		this.utilisateur = utilisateur;
		this.agence = agence;
	}

	public Agentguichet(Utilisateur utilisateur, Agence agence,
			Date dateRecrutement, String email, String firstName,
			String lastName, Set<EnvoiNonInscrit> envoiNonInscrits,
			Set<Retrait> retraits, Set<EnvoiInscrit> envoiInscrits) {
		this.utilisateur = utilisateur;
		this.agence = agence;
		this.dateRecrutement = dateRecrutement;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.envoiNonInscrits = envoiNonInscrits;
		this.retraits = retraits;
		this.envoiInscrits = envoiInscrits;
	}

	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "utilisateur"))
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "CIN", unique = true, nullable = false, length = 254)
	public String getCin() {
		return this.cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public Utilisateur getUtilisateur() {
		return this.utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "AgenceId" )
	public Agence getAgence() {
		return this.agence;
	}

	public void setAgence(Agence agence) {
		this.agence = agence;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DateRecrutement", length = 19)
	public Date getDateRecrutement() {
		return this.dateRecrutement;
	}

	public void setDateRecrutement(Date dateRecrutement) {
		this.dateRecrutement = dateRecrutement;
	}

	@Column(name = "Email", length = 254)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "FirstName", length = 254)
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "LastName", length = 254)
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "agentguichet")
	public Set<EnvoiNonInscrit> getEnvoiNonInscrits() {
		return this.envoiNonInscrits;
	}

	public void setEnvoiNonInscrits(Set<EnvoiNonInscrit> envoiNonInscrits) {
		this.envoiNonInscrits = envoiNonInscrits;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "agentguichet")
	public Set<Retrait> getRetraits() {
		return this.retraits;
	}

	public void setRetraits(Set<Retrait> retraits) {
		this.retraits = retraits;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "agentguichet")
	public Set<EnvoiInscrit> getEnvoiInscrits() {
		return this.envoiInscrits;
	}

	public void setEnvoiInscrits(Set<EnvoiInscrit> envoiInscrits) {
		this.envoiInscrits = envoiInscrits;
	}

}