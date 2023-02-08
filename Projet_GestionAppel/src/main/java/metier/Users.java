package metier;

import javax.persistence.*;

import enumtype.Statut;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Entity(name="Users")
public class Users implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="CodeU")
	private int id;

	@Column(nullable = false, name="Prenom")
	private String prenom;

	@Column(nullable = false , name="Nom")
	private String nom;

	@Column(nullable = false , name="Email")
	private String email;

	@Column(nullable = false, name="Password")
	private String password;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, name="Statut")
	private Statut statut;

	@Column(name="Formation")
	private String formation;

	@Column(name="NumTel")
	private int numTel;

	@Column(name="DateNaiss")
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date dateNaiss;

	@Column(name="NumBureau")
	private int numBureau;

	@Column(name="UrlPhoto")
	private String photo;

	/**
	 * Relations.
	 */

	/*----- DEPOSER -----*/
	@OneToMany(mappedBy="usersJustificatif",cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private Set<Justificatif>justificatifs = new HashSet<>();

	public Set<Justificatif> getJustificatifs() {
		return justificatifs;
	}

	public void setJustificatifs(Set<Justificatif> justificatifs) {
		this.justificatifs = justificatifs;
	}


	/*----- DONNER -----*/
	@OneToMany(mappedBy="usersSeance",cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private Set<Seance>seances = new HashSet<>();

	public Set<Seance> getSeances() {
		return seances;
	}

	public void setSeances(Set<Seance> seances) {
		this.seances = seances;
	}


	/*----- ENSEIGNER -----*/
	@ManyToMany
	@JoinTable(name = "Enseigner",
	joinColumns = @JoinColumn(name = "CodeUsers"),
	inverseJoinColumns = @JoinColumn(name = "CodeCours"))
	private Set<Cours>lescoursEnseignes = new HashSet(0);

	/*----- PARTICIPER -----*/
	@ManyToMany
	@JoinTable(name = "Participer",
	joinColumns = @JoinColumn(name = "CodeUsers"),
	inverseJoinColumns = @JoinColumn(name = "CodeCours"))
	private Set<Cours>lescoursParticipes = new HashSet(0);

	/*----- ASSISTER -----*/
	@OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
	@MapKeyJoinColumn(name = "CodeSeance", updatable = false, insertable = false)
	private Map<Seance,Assister> assister = new HashMap(0);

	/**
	 * Constructeurs.
	 */


	
	public int getId() {
		return id;
	}

	public Users() {
		super();
	}

	public Users(int id, String prenom, String nom, String email, String password, Statut statut, String formation,
			int numTel, Date dateNaiss, int numBureau, String photo) {
		super();
		this.id = id;
		this.prenom = prenom;
		this.nom = nom;
		this.email = email;
		this.password = password;
		this.statut = statut;
		this.formation = formation;
		this.numTel = numTel;
		this.dateNaiss = dateNaiss;
		this.numBureau = numBureau;
		this.photo = photo;
	}

	/**
	 * Methodes.
	 */
	
	public void addAssister(Assister a,Seance s) {
		this.assister.put(s, a);
	}
	public Map<Seance, Assister> getAssister() {
		return assister;
	}

	public void setAssister(Map<Seance, Assister> assister) {
		this.assister = assister;
	}

	public void addJustificatif (Justificatif j)
	{
		this.justificatifs.add(j);
	}

	public void suppJustificatif (Justificatif j)
	{
		this.justificatifs.remove(j);
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Statut getStatut() {
		return statut;
	}

	public void setStatut(Statut statut) {
		this.statut = statut;
	}

	public String getFormation() {
		return formation;
	}

	public void setFormation(String formation) {
		this.formation = formation;
	}

	public int getNumTel() {
		return numTel;
	}

	public void setNumTel(int numTel) {
		this.numTel = numTel;
	}

	public Date getDateNaiss() {
		return dateNaiss;
	}

	public void setDateNaiss(Date dateNaiss) {
		this.dateNaiss = dateNaiss;
	}

	public int getNumBureau() {
		return numBureau;
	}

	public void setNumBureau(int numBureau) {
		this.numBureau = numBureau;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}


	@Override
	public int hashCode() {
		return Objects.hash(id);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Users other = (Users) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", prenom=" + prenom + ", nom=" + nom + ", email=" + email + ", password=" + password
				+ ", statut=" + statut + ", formation=" + formation + ", numTel=" + numTel + ", dateNaiss=" + dateNaiss;
	}







}