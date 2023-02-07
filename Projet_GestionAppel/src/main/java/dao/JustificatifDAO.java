package dao;

import java.util.ArrayList;



import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import antlr.collections.List;
import metier.Justificatif;
import metier.Users;

public class JustificatifDAO extends DAO<Justificatif> {


	public JustificatifDAO() {
		super.setEntity(Justificatif.class);
	}
	
	
	/*
	 * 	Méthode qui permet de retourner la liste des justificatifs 
	 */
	public ArrayList<Justificatif>loadJustificatif () {


		ArrayList<Justificatif> listeJustif = new ArrayList<>();
		String hql = "select j " +
				"from Justificatif j,Users u" +
				"where j.id = u.id ";
		
		try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
			Transaction transaction=session.beginTransaction();
			Query<Justificatif>query = session.createQuery(hql);
		
			if (!query.getResultList().isEmpty()) {
				listeJustif =(ArrayList<Justificatif>) query.list();

			}
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return listeJustif;

}

	

}
