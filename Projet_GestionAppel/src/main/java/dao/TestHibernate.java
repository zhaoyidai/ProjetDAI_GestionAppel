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
import org.hibernate.resource.transaction.spi.TransactionStatus;

import enumtype.AppelEtat;
import enumtype.FicheAppelEtat;
import enumtype.Statut;
import metier.Assister;
import metier.AssisterId;
import metier.Cours;

import metier.Seance;
import metier.Users;

import dao.HibernateUtil;
import dao.TestHibernate;

/**
 * Classe de test pour Hibernate.
 */
public class TestHibernate
{
	private static final SimpleDateFormat DF = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	private static final SimpleDateFormat DFDATE = new SimpleDateFormat("dd-MM-yyyy");
	
	
	public static void createUsers() throws ParseException {
		//Ouverture d'une session hibernate
		try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()){
			//Ouverture de transaction
			Transaction t = session.beginTransaction();
			
			Users e1 = new Users(1 , "Test", "Chartelain", "etudiant@gmail.com", "123456789", Statut.ETUDIANT , "IPM" , 123456789 , DFDATE.parse("02-02-2021") , 12 , "test");
			Users e2 = new Users(2 , "Test", "Chartelain", "enseignant@gmail.com", "123456789", Statut.ENSEIGNANT , null , 123456789 , DFDATE.parse("02-02-2021") , 12, "test");
			Users e3 = new Users(3 , "Test", "Chartelain", "scolarite@gmail.com", "123456789", Statut.SCOLARITE , null , 123456789 , DFDATE.parse("02-02-2021") , 12, "test");
			session.save(e1);
			session.save(e2);
			session.save(e3);
			
			Cours c1 = new Cours(1, "Anglais");
			session.save(c1);
			Seance s1 = new Seance(1 , 3 , DFDATE.parse("02-02-2021"), DF.parse("02-02-2021 08:00:00") , FicheAppelEtat.EN_ATTENTE,e2);
			Seance s2 = new Seance(1 , 3 , DFDATE.parse("02-02-2021"), DF.parse("02-02-2021 14:00:00") , FicheAppelEtat.EN_ATTENTE,e2);
//			
			
			
			session.save(s1);
			session.save(s2);

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
//	users et status presence
	public static List<EtudiantPresence> loadEtudiant(int id) {
		List<EtudiantPresence> etudiants=new ArrayList<>();
		String hql = "select new dao.EtudiantPresence(u,a.status) " +
                "from metier.Seance s,metier.Users u,metier.Assister a " +
                "where s.idS = :id " +
                "and s.idS = a.seance.idS and a.users.id=u.id ";
		
		try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
        	Transaction transaction=session.beginTransaction();
            Query<EtudiantPresence>query = session.createQuery(hql);
            query.setParameter("id", id);
            
            if (!query.getResultList().isEmpty()) {
            	etudiants=query.list();
            	for(EtudiantPresence s:query.list()) {
            		System.out.println(s.getU().getEmail()+" status : "+s.getStatus());
            	}

            	}
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
		return etudiants;		
			

			
		
				
	}
	
	public static List<Users> loadEtudiantparticip(int id) {
		List<Users> etudiants=new ArrayList<>();
		String hql = "select u " +
                "from Seance s,Cours c " +
                " join c.usersParticipes u where s.idS = :id and s.coursSeance.idC = c.idC";
		
		try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
			Transaction t = session.getTransaction();
	        if (!TransactionStatus.ACTIVE.equals(t.getStatus())) {
	            t = session.beginTransaction();}
            Query<Users>query = session.createQuery(hql);
            query.setParameter("id", id);
            
            if (!query.getResultList().isEmpty()) {
            	etudiants=query.list();
            	for(Users s:query.list()) {
            		System.out.println(s.getId());
            	}

            	}
//            t.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
		return etudiants;		
			

			
		
				
	}
	
	/**
	 * Programme de test.
	 * @throws ParseException 
	 */
	public static void main (String[] args) throws ParseException
		{
//		System.out.println("test");
//		TestHibernate.loadSeanceMap(3);
//		TestHibernate.createUsers();
//		TestHibernate.loadSeance(3);
//		for(Users u:UsersDao.listEtudiant()) {
//			System.out.println(u);
//		}
		TestHibernate.loadEtudiant(1);
//		System.out.println("test");
//		List<Users> us=UsersDao.listEtudiant(1);
//		for(Users u:us) {
//			System.out.println(u.getPhoto());
//		}
//		afficheEtu(1);
//		TestHibernate.insertAssister(2);
//		loadEtudiantparticip(2);
//		TestHibernate.validerFiche(2);
		}

		
	public static boolean affichestatus(int id) {
		try(Session session=HibernateUtil.getSessionFactory().getCurrentSession()){
			Transaction t = session.getTransaction();
	        if (!TransactionStatus.ACTIVE.equals(t.getStatus())) {
	            t = session.beginTransaction();}
			
			Seance e=session.get(Seance.class, id);
			
//			t.commit();
			if(e.getStatutFicheAppel().toString()=="EN_ATTENTE") {
				return true;
			}else {
				return false;
			}
			
			}
		
	}


	public static void insertAssister(int id) {
		try(Session session=HibernateUtil.getSessionFactory().getCurrentSession()){
			List<Users> etus=new ArrayList<>();
			Transaction t = session.getTransaction();
	        if (!TransactionStatus.ACTIVE.equals(t.getStatus())) {
	            t = session.beginTransaction();}
			
			Seance s=session.get(Seance.class, id);
//			session.save(s);
			String hql = "select u " +
	                "from Seance s,Cours c " +
	                " join c.usersParticipes u where s.idS = :id and s.coursSeance.idC = c.idC";
			Query<Users>query = session.createQuery(hql);
            query.setParameter("id", id);
            
            if (!query.getResultList().isEmpty()) {
            	etus=query.list();
            	}
//			List<Users> etus=TestHibernate.loadEtudiantparticip(id);
			System.out.println("size : "+etus.size());
			for(Users us:etus) {
				System.out.println(us.getEmail());
				AssisterId ai=new AssisterId(us.getId(),id);
//				System.out.println(ai.getCodeSeance());
				Assister a=new Assister(ai,AppelEtat.PRESENCE,us,s);
//				System.out.println(ai.getCodeSeance());
//				session.save(a);
//				
//				session.save(ai);
				s.addAssister(a,us);
//				System.out.println(ai.getCodeSeance());
				us.addAssister(a,s);
				
			}
			t.commit();
			}
			
	}


	public static void validerFiche(int ids) {
		try(Session session=HibernateUtil.getSessionFactory().getCurrentSession()){
			List<Users> etus=new ArrayList<>();
			Transaction t = session.getTransaction();
			if (!TransactionStatus.ACTIVE.equals(t.getStatus())) {
	            t = session.beginTransaction();}
			Seance s=session.get(Seance.class, ids);
			session.save(s);
			s.setStatutFicheAppel(FicheAppelEtat.VALIDER);
			session.update(s);
			t.commit();
			}
	}



	


	/**
	 * Programme de test.
	 * @throws ParseException
	 */
	

} /*----- Fin de la classe TestHibernate -----*/
