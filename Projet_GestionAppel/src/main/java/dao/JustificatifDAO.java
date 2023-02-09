package dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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
				"from Justificatif j,Users u " +
				"where j.usersJustificatif.id = u.id " +
				"and j.validation = 0 ";
		
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
	
	
	
	/*
	 * 	Méthode qui permet de retourner la liste des justificatifs 
	 */
	public ArrayList<Justificatif>updateAbsences () {


		ArrayList<Justificatif> listeJustif = new ArrayList<>();
		String hql = "select j " +
				"from Justificatif j,Users u " +
				"where j.usersJustificatif.id = u.id " +
				"and j.validation = 0 ";
		
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
	
	
	
	/*
	 * 	Méthode qui permet de retourner la liste des justificatifs 
	 */
	public void createJustificatif (Date dateDebut, Date dateFin, String Url, boolean validation, Users  users) {
		/*----- Ouverture de la session -----*/
		try (Session session = HibernateUtil.getSessionFactory().getCurrentSession())
			{
			/*----- Ouverture d'une transaction -----*/
			Transaction t = session.beginTransaction();
			System.out.println(Url);
			Justificatif j1 = new Justificatif(validation,Url,dateDebut,dateFin,users);
			session.save(j1);
			t.commit();
			session.close();
	}
	}
	
	
	
	 
}
