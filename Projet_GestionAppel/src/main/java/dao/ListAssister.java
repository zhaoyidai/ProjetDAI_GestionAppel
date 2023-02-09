package dao;

import java.util.List;

import metier.Cours;

public class ListAssister {
private int idU;
private List<Integer> cours;
private List<Integer> presences;
private List<Integer> retards;
private List<Integer> absences;
public ListAssister(int idU) {
	super();
	this.idU = idU;
	this.cours=TestHibernate.getCours(idU);
	this.presences = TestHibernate.listStaus(idU).get(0);
	this.retards = TestHibernate.listStaus(idU).get(1);
	this.absences = TestHibernate.listStaus(idU).get(2);
}


}
