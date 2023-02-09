package dao;

import java.util.ArrayList;
import java.util.List;

public class CoursSeances {
	private int idCours;
	private String nomCours;
	List<Integer> listeSeanceid=new ArrayList<>();
	public CoursSeances(int idCours, String nomCours) {
		super();
		this.idCours = idCours;
		this.nomCours = nomCours;
		this.listeSeanceid=TestHibernate.listeSeance(idCours);
	}
	
	
}
