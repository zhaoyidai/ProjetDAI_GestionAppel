package metier;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

import enumtype.FicheAppelEtat;

@Entity(name="Seance")
public class Seance implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idSeance")
	private int idS;
	
	@Column(name="DureeSeance")
	private int dureeS;
	
	@Column(name="DateSeance")
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date dateSeance;

	@Column(name="HeureDebut")
	@Temporal(javax.persistence.TemporalType.TIME)
	private Date heureDebut;
	 
	@Enumerated(EnumType.STRING)
	@Column(name="StatutFicheAppel")
	private FicheAppelEtat StatutFicheAppel;
	
	/**
	 * Relations.
	 */
	
	/*----- DONNER -----*/
	@ManyToOne(fetch =  FetchType.EAGER)
	@JoinColumn(name="CodeUsers")
	private Users usersSeance;

	
	public Users getUsersSeance() {
		return usersSeance;
	}

	public void setUsersSeance(Users usersSeance) {
		this.usersSeance = usersSeance;
	}

	/*----- APPARTENIR -----*/
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="CodeCours")
	private Cours coursSeance;

	public Cours getCoursSeance() {
		return coursSeance;
	}

	public void setCoursSeance(Cours coursSeance) {
		this.coursSeance = coursSeance;
	}
	
	/*----- ASSISTER -----*/
	@OneToMany(mappedBy = "seance", cascade = CascadeType.ALL)
	@MapKeyJoinColumn(name = "CodeUsers", updatable = false, insertable = false)
	private Map<Users,Assister> assister = new HashMap(0);
	
	/**
	 * Constructeurs.
	 */
	public Seance(int idS, int dureeS, Date dateSeance, Date heureDebut, FicheAppelEtat statutFicheAppel,Users u) {
		super();
		this.idS = idS;
		this.dureeS = dureeS;
		this.dateSeance = dateSeance;
		this.heureDebut = heureDebut;
		this.usersSeance=u;
		StatutFicheAppel = statutFicheAppel;
	}
		
	public void addAssister(Assister a,Users s) {
		this.assister.put(s, a);
	}
	public Map<Users, Assister> getAssister() {
		return assister;
	}

	public void setAssister(Map<Users, Assister> assister) {
		this.assister = assister;
	}

	public Seance() {}
	public int getIdS() {
		return idS;
	}

	public void setIdS(int idS) {
		this.idS = idS;
	}

	public int getDureeS() {
		return dureeS;
	}

	public void setDureeS(int dureeS) {
		this.dureeS = dureeS;
	}

	public Date getDateSeance() {
		return dateSeance;
	}

	public void setDateSeance(Date dateSeance) {
		this.dateSeance = dateSeance;
	}

	public Date getHeureDebut() {
		return heureDebut;
	}

	public void setHeureDebut(Date heureDebut) {
		this.heureDebut = heureDebut;
	}

	public FicheAppelEtat getStatutFicheAppel() {
		return StatutFicheAppel;
	}

	public void setStatutFicheAppel(FicheAppelEtat statutFicheAppel) {
		StatutFicheAppel = statutFicheAppel;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idS);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Seance other = (Seance) obj;
		return idS == other.idS;
	}

	@Override
	public String toString() {
		return "Seance [idS=" + idS + ", dureeS=" + dureeS + ", dateSeance=" + dateSeance + ", heureDebut=" + heureDebut
				+ ", StatutFicheAppel=" + StatutFicheAppel + ", usersSeance=" + usersSeance + ", coursSeance="
				+ coursSeance + ", assister=" + assister + "]";
	}


	
	
}
