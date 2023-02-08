package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import metier.Justificatif;
import metier.Seance;

public class SeanceDAO extends DAO<Seance>{
	
	public SeanceDAO() {
		super.setEntity(Seance.class);
	}

    // méthode permettant d'avoir la liste des seances a laquelle l'utilisateur est absent.
    public List<Seance> listAbsencesEtudiant(int id){
    	List<Seance> seances = new ArrayList<>();
    	String hql = "select s from Seance s , Assister a, Users u where a.seance.idS = s.idS and a.users.id = :id and a.status like 'ABSENCE' and u.id = s.usersSeance.id and u.statut like 'ETUDIANT'";

        try (Session session = getSession()) {
        	Transaction transaction=getTransaction(session);
            Query<Seance>query = session.createQuery(hql);
            query.setParameter("id", id);
            if (!query.getResultList().isEmpty()) {
            	seances=query.list();
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return seances;
    }
}
