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
	@OneToMany(mappedBy="coursSeance",cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private Set<Seance>seance = new HashSet<>();

	public Set<Seance> getSeance() {
		return seance;
	}

	public void setSeance(Set<Seance> seance) {
		this.seance = seance;
	}
	
	
	/*----- ENSEIGNER -----*/
	@ManyToMany(mappedBy = "lescoursEnseignés")
	private Set<Users> usersEnseignés = new HashSet(0);
	
	/*----- PARTICPER -----*/
	@ManyToMany(mappedBy = "lescoursParticipés")
	private Set<Users>usersParticipés = new HashSet(0);

	
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
		return "Cours [idC=" + idC + ", nomC=" + nomC + ", seance=" + seance + ", usersEnseignés=" + usersEnseignés
				+ ", usersParticipés=" + usersParticipés + "]";
	}

	public Set<Users> getUsersEnseignés() {
		return usersEnseignés;
	}

	public void setUsersEnseignés(Set<Users> usersEnseignés) {
		this.usersEnseignés = usersEnseignés;
	}

	public Set<Users> getUsersParticipés() {
		return usersParticipés;
	}

	public void setUsersParticipés(Set<Users> usersParticipés) {
		this.usersParticipés = usersParticipés;
	}
	
	
}
