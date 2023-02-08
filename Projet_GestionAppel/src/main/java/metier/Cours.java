package metier;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

@Entity(name="Cours")
public class Cours implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idCours")
	private int idC;
	
	@Column(name="NomCours")
	private String nomC;
	
	/**
	 * Relations.
	 */
	
	/*----- APPARTENIR -----*/
	@OneToMany(mappedBy="coursSeance",cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private Set<Seance>seance = new HashSet<>();

	public Set<Seance> getSeance() {
		return seance;
	}

	public void setSeance(Set<Seance> seance) {
		this.seance = seance;
	}
	
	
	/*----- ENSEIGNER -----*/
	@ManyToMany(mappedBy = "lescoursEnseignes")
	private Set<Users> usersEnseignes = new HashSet(0);
	
	/*----- PARTICPER -----*/
	@ManyToMany(mappedBy = "lescoursParticipes")
	private Set<Users>usersParticipes = new HashSet(0);

	
	/**
	 * Constructeurs.
	 */
	public Cours(int idC, String nomC) {
		
		this.idC = idC;
		this.nomC = nomC;
	}
	public Cours() {}
	public int getIdC() {
		return idC;
	}

	public void setIdC(int idC) {
		this.idC = idC;
	}

	public String getNomC() {
		return nomC;
	}

	public void setNomC(String nomC) {
		this.nomC = nomC;
	}

	public Set<Users> getUsersEnseignes() {
		return usersEnseignes;
	}

	public void setUsersEnseignes(Set<Users> usersEnseignes) {
		this.usersEnseignes = usersEnseignes;
	}

	public Set<Users> getUsersParticipes() {
		return usersParticipes;
	}

	public void setUsersParticipes(Set<Users> usersParticipes) {
		this.usersParticipes = usersParticipes;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idC);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cours other = (Cours) obj;
		return idC == other.idC;
	}

	@Override
	public String toString() {
		return "Cours [idC=" + idC + ", nomC=" + nomC + ", seance=" + seance + ", usersEnseignes=" + usersEnseignes
				+ ", usersParticipes=" + usersParticipes + "]";
	}
	
	
	public void addSeance(Seance s) {
		this.seance.add(s);
	}
	
}
