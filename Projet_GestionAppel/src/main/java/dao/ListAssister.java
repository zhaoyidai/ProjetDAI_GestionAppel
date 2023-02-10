package dao;

import java.util.List;

import metier.Cours;

public class ListAssister {
private int idU;
private String nomU;
private String prenomU;
private List<Integer> presences;
private List<Integer> retards;
private List<Integer> absences;
public ListAssister(int idU,String nom,String prenom,List<Integer> ids) {
	super();
	this.idU = idU;
	this.nomU=nom;
	this.prenomU=prenom;
	
	this.presences = TestHibernate.listStaus(idU).get(0);
	this.retards = TestHibernate.listStaus(idU).get(1);
	this.absences = TestHibernate.listStaus(idU).get(2);
}


}
