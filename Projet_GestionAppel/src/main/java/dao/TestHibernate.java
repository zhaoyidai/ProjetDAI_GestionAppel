package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import metier.Users;

/**
 * Classe de test pour Hibernate.
 */
public class TestHibernate
{
	
	
	public static void createUsers() {
		//Ouverture d'une session hibernate
		try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()){
			//Ouverture de transaction
			Transaction t = session.beginTransaction();
			
			Users e1 = new Users(1 , "David", "Chartelain", "dad@gmail.com", "1234");
			Users e2 = new Users(2 , "Test", "Chartelain", "dad@gmail.com", "1234");
			
			session.save(e1);
			session.save(e2);			
			
			t.commit(); //Ferme la transaction et la session
		}
	}

	
	/**
	 * Programme de test.
	 */
	public static void main (String[] args)
		{
		TestHibernate.createUsers();
		}

} /*----- Fin de la classe TestHibernate -----*/
