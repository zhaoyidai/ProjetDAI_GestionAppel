package dao;

import java.util.Date;

import enumtype.AppelEtat;
import metier.Users;

public class EtudiantPresence {
	private Users u;
	private AppelEtat status;
	private Date dateSeance;
	
	public EtudiantPresence(Users u, AppelEtat status, Date dateSeance) {
		super();
		this.u = u;
		this.status = status;
		this.dateSeance = dateSeance;
	}
	public EtudiantPresence() {}
	public Users getU() {
		return u;
	}
	public void setU(Users u) {
		this.u = u;
	}
	public AppelEtat getStatus() {
		return status;
	}
	public void setStatus(AppelEtat status) {
		this.status = status;
	}
	public Date getDateSeance() {
		return dateSeance;
	}
	public void setDateSeance(Date dateSeance) {
		this.dateSeance = dateSeance;
	}
	
	
	
	
}
