package dao;

import enumtype.AppelEtat;
import metier.Users;

public class EtudiantPresence {
	private Users u;
	private AppelEtat status;
	
	public EtudiantPresence(Users u, AppelEtat status) {
		super();
		this.u = u;
		this.status = status;
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
	
	
	
}
