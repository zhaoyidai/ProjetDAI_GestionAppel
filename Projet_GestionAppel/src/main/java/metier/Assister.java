package metier;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import enumtype.AppelEtat;

@Entity(name = "Assister")
public class Assister {

	/**
	 * Propriétés.
	 */
	@EmbeddedId
	private AssisterId id;

	@Enumerated(EnumType.STRING)
	@Column(name = "Status")
	private AppelEtat status;
	
	/**
	 * Relations.
	 */

	/*----- Assister vers users -----*/
	@ManyToOne
	@JoinColumn(name = "CodeUsers", insertable = false, updatable = false)	// "insertable" et "updatable" sont à "false" pour éviter la persistance.
	private Users users;												// Dans ce cas, le propriétaire de la relation est la collection côté @OneToMany.

	/*----- Assister vers seance -----*/
	@ManyToOne
    @JoinColumn(name = "CodeSeance", insertable = false, updatable = false)
	private Seance seance;

	public Assister(AssisterId id, AppelEtat status, Users users, Seance seance) {
		super();
		this.id = id;
		this.status = status;
		this.users = users;
		this.seance = seance;
	}

	public AssisterId getId() {
		return id;
	}

	public void setId(AssisterId id) {
		this.id = id;
	}

	public AppelEtat isStatus() {
		return status;
	}

	public void setStatus(AppelEtat status) {
		this.status = status;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Seance getSeance() {
		return seance;
	}

	public void setSeance(Seance seance) {
		this.seance = seance;
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
		Assister other = (Assister) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Assister [id=" + id + ", status=" + status + ", users=" + users + ", seance=" + seance + "]";
	}


}
