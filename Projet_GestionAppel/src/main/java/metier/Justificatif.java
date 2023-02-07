package metier;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

import enumtype.Statut;

@Entity(name="Justificatif")
public class Justificatif implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="CodeJ")
	private int CodeJust;

	@Column(name="Validation")
	private boolean validation;
	
	@Column(name="Url")
	private String url;

	@Column(name="DateDebut")
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date dateDeb;

	@Column(name="DateFin")
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date dateFin;



	
	/**
	 * Relations.
	 */	
	
	/*----- DEPOSER -----*/
	@ManyToOne(fetch =  FetchType.EAGER)
	@JoinColumn(name="CodeUsers")
	private Users usersJustificatif;

	public Users getUsersJustificatif() {
		return usersJustificatif;
	}

	public void setUsersJustificatif(Users usersJustificatif) {
		this.usersJustificatif = usersJustificatif;
	}
	
	
	public Justificatif() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructeurs.
	 */
	public Justificatif(int codeJust, boolean validation, String url, Date dateDeb, Date dateFin,
			Users usersJustificatif) {
		super();
		CodeJust = codeJust;
		this.validation = validation;
		this.url = url;
		this.dateDeb = dateDeb;
		this.dateFin = dateFin;
		this.usersJustificatif = usersJustificatif;
	}
	
	
	public int getCodeJust() {
		return CodeJust;
	}

	public void setCodeJust(int codeJust) {
		CodeJust = codeJust;
	}

	public boolean isValidation() {
		return validation;
	}

	public void setValidation(boolean validation) {
		this.validation = validation;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getDateDeb() {
		return dateDeb;
	}

	public void setDateDeb(Date dateDeb) {
		this.dateDeb = dateDeb;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	@Override
	public int hashCode() {
		return Objects.hash(CodeJust);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Justificatif other = (Justificatif) obj;
		return CodeJust == other.CodeJust;
	}

	@Override
	public String toString() {
		return "Justificatif [CodeJust=" + CodeJust + ", validation=" + validation + ", url=" + url + ", dateDeb="
				+ dateDeb + ", dateFin=" + dateFin + ", usersJustificatif=" + usersJustificatif + "]";
	}
	
	  



}
