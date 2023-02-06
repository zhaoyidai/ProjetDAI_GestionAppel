package dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import enumtype.FicheAppelEtat;
import enumtype.Statut;
import metier.Cours;
import metier.Seance;
import metier.Users;

/**
 * Classe de test pour Hibernate.
 */
public class TestHibernate
{
	private static final SimpleDateFormat DF = new SimpleDateFormat("dd-MM-yyyy");
	
	
	public static void createUsers() throws ParseException {
		//Ouverture d'une session hibernate
		try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()){
			//Ouverture de transaction
			Transaction t = session.beginTransaction();
			
			Users e1 = new Users(1 , "Test", "Chartelain", "etudiant@gmail.com", "123456789", Statut.ETUDIANT , "IPM" , 123456789 , DF.parse("02-02-2021") , 12 , "test");
			Users e2 = new Users(2 , "Test", "Chartelain", "enseignant@gmail.com", "123456789", Statut.ENSEIGNANT , null , 123456789 , DF.parse("02-02-2021") , 12, "test");
			Users e3 = new Users(3 , "Test", "Chartelain", "scolarite@gmail.com", "123456789", Statut.SCOLARITE , null , 123456789 , DF.parse("02-02-2021") , 12, "test");

			
			Cours c1 = new Cours(1, "Anglais");
			
			Seance s1 = new Seance(1 , 5 , DF.parse("02-02-2021"), DF.parse("02-02-2021") , FicheAppelEtat.EN_ATTENTE);
			
			session.save(e1);
			session.save(e2);
			session.save(e3);
			session.save(c1);
			session.save(s1);

			t.commit(); //Ferme la transaction et la session
		}
	}
	
	public static List<Seance> loadSeance(int id) {
		
		
			List<Seance> seances = new ArrayList<>();
	    	String hql = "select s " +
	                "from Seance s,Users u " +
	                "where s.usersSeance.id = :id " +
	                "and s.usersSeance.id = u.id ";
	        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
	        	Transaction transaction=session.beginTransaction();
	            Query<Seance>query = session.createQuery(hql);
	            query.setParameter("id", id);
	            
	            if (!query.getResultList().isEmpty()) {
	            	seances=query.list();
//	            	for(Seance s:query.list()) {
//	            		System.out.println(s.getIdS()+" "+s.getDuréeS());
//	            	}

	            	}
	            transaction.commit();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        
			return seances;
			

			
			
					
		
	}

	
	/**
	 * Programme de test.
	 * @throws ParseException 
	 */
	public static void main (String[] args) throws ParseException
		{
//		TestHibernate.createUsers();
		TestHibernate.loadSeance(2);
		}

} /*----- Fin de la classe TestHibernate -----*/
