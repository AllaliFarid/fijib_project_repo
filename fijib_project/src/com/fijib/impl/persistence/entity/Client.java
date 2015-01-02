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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * Client generated by hbm2java
 */
@Entity
@Table(name = "client", catalog = "db_fijib")
public class Client implements java.io.Serializable {

	private String cin;
	private Utilisateur utilisateur;
	private String adresseClient;
	private String codePostale;
	private Date dateNaissance;
	private String firstName;
	private String lastName;
	private String numCompteBanc;
	private String tel;
	private String ville;
	private Set<EnvoiInscrit> envoiInscritsForCliCinEnv = new HashSet<EnvoiInscrit>(
			0);
	private Set<Listeprefere> listepreferesForCinBene = new HashSet<Listeprefere>(
			0);
	private Set<EnvoiInscrit> envoiInscritsForCinBene = new HashSet<EnvoiInscrit>(
			0);
	private Set<Listeprefere> listepreferesForCliCinEnv = new HashSet<Listeprefere>(
			0);

	public Client() {
	}

	public Client(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Client(  String adresseClient,
			String codePostale, Date dateNaissance, String firstName,
			String lastName, String numCompteBanc, String tel, String ville 
			  ) {
	 
		this.adresseClient = adresseClient;
		this.codePostale = codePostale;
		this.dateNaissance = dateNaissance;
		this.firstName = firstName;
		this.lastName = lastName;
		this.numCompteBanc = numCompteBanc;
		this.tel = tel;
		this.ville = ville;
	 
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

	@Column(name = "AdresseClient", length = 254)
	public String getAdresseClient() {
		return this.adresseClient;
	}

	public void setAdresseClient(String adresseClient) {
		this.adresseClient = adresseClient;
	}

	@Column(name = "CodePostale", length = 254)
	public String getCodePostale() {
		return this.codePostale;
	}

	public void setCodePostale(String codePostale) {
		this.codePostale = codePostale;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DateNaissance", length = 19)
	public Date getDateNaissance() {
		return this.dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
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

	@Column(name = "NumCompteBanc", length = 254)
	public String getNumCompteBanc() {
		return this.numCompteBanc;
	}

	public void setNumCompteBanc(String numCompteBanc) {
		this.numCompteBanc = numCompteBanc;
	}

	@Column(name = "Tel", length = 254)
	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Column(name = "Ville", length = 254)
	public String getVille() {
		return this.ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "clientByCliCinEnv")
	public Set<EnvoiInscrit> getEnvoiInscritsForCliCinEnv() {
		return this.envoiInscritsForCliCinEnv;
	}

	public void setEnvoiInscritsForCliCinEnv(
			Set<EnvoiInscrit> envoiInscritsForCliCinEnv) {
		this.envoiInscritsForCliCinEnv = envoiInscritsForCliCinEnv;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "clientByCinBene")
	public Set<Listeprefere> getListepreferesForCinBene() {
		return this.listepreferesForCinBene;
	}

	public void setListepreferesForCinBene(
			Set<Listeprefere> listepreferesForCinBene) {
		this.listepreferesForCinBene = listepreferesForCinBene;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "clientByCinBene")
	public Set<EnvoiInscrit> getEnvoiInscritsForCinBene() {
		return this.envoiInscritsForCinBene;
	}

	public void setEnvoiInscritsForCinBene(
			Set<EnvoiInscrit> envoiInscritsForCinBene) {
		this.envoiInscritsForCinBene = envoiInscritsForCinBene;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "clientByCliCinEnv")
	public Set<Listeprefere> getListepreferesForCliCinEnv() {
		return this.listepreferesForCliCinEnv;
	}

	public void setListepreferesForCliCinEnv(
			Set<Listeprefere> listepreferesForCliCinEnv) {
		this.listepreferesForCliCinEnv = listepreferesForCliCinEnv;
	}

}
