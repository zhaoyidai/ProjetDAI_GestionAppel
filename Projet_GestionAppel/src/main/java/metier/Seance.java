package metier;

import java.io.Serializable;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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



	public Seance() {}


	public Seance(int idS, int dureeS, Date dateSeance, Date heureDebut, FicheAppelEtat statutFicheAppel,
			Users usersSeance, Cours coursSeance) {
		super();
		this.idS = idS;
		this.dureeS = dureeS;
		this.dateSeance = dateSeance;
		this.heureDebut = heureDebut;
		StatutFicheAppel = statutFicheAppel;
		this.usersSeance = usersSeance;
		this.coursSeance = coursSeance;
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
	
	

	public static SimpleDateFormat getDfdate() {
		return DFDATE;
	}

	public static SimpleDateFormat getDfheure() {
		return DFHEURE;
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
	
// le nb de semaine
	public static int datetest(Date date) {

		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setFirstDayOfWeek(GregorianCalendar.MONDAY);
		calendar.setMinimalDaysInFirstWeek(4);
		calendar.setTime(date);
		return calendar.get(GregorianCalendar.WEEK_OF_YEAR);
	}
	private static final SimpleDateFormat DFDATE = new SimpleDateFormat("dd-MM-yyyy");
	private static final SimpleDateFormat DFHEURE = new SimpleDateFormat("HH:mm:ss");
	public static String dateofweek(int i) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.clear();
		cal.set(Calendar.WEEK_OF_YEAR, i);
		cal.set(Calendar.YEAR,2023 );
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
//	    Integer correction = 1-cal.get(GregorianCalendar.DAY_OF_WEEK);
//	    cal.add(Calendar.DATE, correction);
		Date d=cal.getTime();
		return(DFDATE.format(d));
//	    return d.toString();
	}
	
	public static int jour(Date d) {
		Calendar calendar = Calendar.getInstance(); 
		calendar.setTime(d);
		
		return calendar.get(Calendar.DAY_OF_WEEK); 
	}
	
	public static String dateFin(Date d,int duree) {
		Calendar timed=Calendar.getInstance();
		timed.setTime(d);
		timed.add((Calendar.MINUTE), duree);
		Date date = timed.getTime(); 
		return DFHEURE.format(date);
	}
}
