package dao;

public class SeancetoJson {
private int idSeance;
private String nomCours;
private String heureDebut;
private String heureFin;
private String lien;
private int week;
private String date;
private int jour;
public SeancetoJson(int idSeance, String nomCours, String heureDebut, String heureFin, String lien, int week, String date,int jour) {
	super();
	this.idSeance = idSeance;
	this.nomCours = nomCours;
	this.heureDebut = heureDebut;
	this.heureFin=heureFin;
	this.lien = lien;
	this.week = week;
	this.date = date;
	this.jour=jour;
}



}
