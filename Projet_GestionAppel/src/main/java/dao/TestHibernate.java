package dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import metier.Justificatif;
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
			Seance s1 = new Seance(1 , 3 , DFDATE.parse("02-02-2021"), DF.parse("02-02-2021 08:00:00") , FicheAppelEtat.EN_ATTENTE,e2,c1);
			Seance s2 = new Seance(2 , 3 , DFDATE.parse("02-02-2021"), DF.parse("02-02-2021 14:00:00") , FicheAppelEtat.EN_ATTENTE,e2, c1);
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
		String hql = "select new dao.EtudiantPresence(u,a.status,s.dateSeance) " +
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
	private static final SimpleDateFormat DFHEURE = new SimpleDateFormat("HH:mm:ss");
	public static String dateFin(Date d,int duree) {
		Calendar timed=Calendar.getInstance();
		timed.setTime(d);
		timed.add((Calendar.MINUTE), duree);
		Date date = timed.getTime(); 
		return DFHEURE.format(date);
	}
	
	public static String dateofweek(int i) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.clear();
		cal.set(Calendar.WEEK_OF_YEAR, i);
		cal.set(Calendar.YEAR,2023 );
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
//	    Integer correction = 1-cal.get(GregorianCalendar.DAY_OF_WEEK);
//	    cal.add(Calendar.DATE, correction);
		Date d=cal.getTime();
	    return d.toString();
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
//		TestHibernate.loadEtudiant(1);
//		System.out.println("test");
//		List<Users> us=UsersDao.listEtudiant(1);
//		for(Users u:us) {
//			System.out.println(u.getPhoto());
//		}
//		afficheEtu(1);
//		TestHibernate.insertAssister(2);
//		loadEtudiantparticip(2);
//		TestHibernate.validerFiche(2);
//		TestHibernate.changerStatusEtu(3, 3, AppelEtat.ABSENCE);
//		System.out.println(TestHibernate.dateofweek(5));
//		try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
//			Transaction t = session.getTransaction();
//			Seance s1=session.get(Seance.class, 1);
//			
//			}
		
//		for(Cours c:TestHibernate.allCours()) {
//			System.out.print(c.getNomC()+" :[ ");
//			
//			for(int i:TestHibernate.listeSeance(c.getIdC())) {
//				System.out.print(i+" ");
//			}
//		}
//		System.out.print("\n ]");
		
		
//		for(Users u:TestHibernate.listeAssister()) {
//			for(List<Integer> lis:TestHibernate.listStaus(u.getId())) {
//				System.out.println(lis);
//			}
//		}
		
//		TestHibernate.getCours(1);
//		TestHibernate.etuSeancelistAbs(12);
		TestHibernate.updateJustifRefus(8);
		
//		TestHibernate.testlist(1);
		}

	public static boolean affichestatusvalide(int id) {
		try(Session session=HibernateUtil.getSessionFactory().getCurrentSession()){
			Transaction t = session.getTransaction();
	        if (!TransactionStatus.ACTIVE.equals(t.getStatus())) {
	            t = session.beginTransaction();}
			
			Seance e=session.get(Seance.class, id);
			
//			t.commit();
			if(e.getStatutFicheAppel().toString()=="VALIDER") {
				return true;
			}else {
				return false;
			}
			
			}
		
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
	
	public static void updatePhotoProfil(String chemin,int user){
		try(Session session=HibernateUtil.getSessionFactory().getCurrentSession()){
			
			Transaction t = session.getTransaction();
			if (!TransactionStatus.ACTIVE.equals(t.getStatus())) {
	            t = session.beginTransaction();}
			Users u=session.get(Users.class, user);
			u.setPhoto(chemin);
			session.update(u);
			t.commit();
	    } catch (Exception e) {
            e.printStackTrace();
        }
  }
	
	
	public static void updateJustifRefus(int codeJ){
		try(Session session=HibernateUtil.getSessionFactory().getCurrentSession()){
			
			Transaction t = session.getTransaction();
			if (!TransactionStatus.ACTIVE.equals(t.getStatus())) {
	            t = session.beginTransaction();}
			Justificatif u=session.get(Justificatif.class, codeJ);
			u.setValidation(true);
			session.update(u);
			t.commit();
	    } catch (Exception e) {
            e.printStackTrace();
        }
  }
	
	public static void updateJustifica(int idu,int idSeance,int codej) {
		try(Session session=HibernateUtil.getSessionFactory().getCurrentSession()){
			
			Transaction t = session.getTransaction();
			if (!TransactionStatus.ACTIVE.equals(t.getStatus())) {
	            t = session.beginTransaction();}
			AssisterId aid=new AssisterId(idu,idSeance);
			Assister a=session.get(Assister.class, aid);
			a.setCodeJ(codej);
			a.setStatus(AppelEtat.ABSENCE_JUSTIFIE);
			session.update(a);
			t.commit();
			}
		
		
		
	}
	
	
	public static List<Seance> etuSeancelistAbs(int idu) {
		List<Seance> seances=new ArrayList<>();
		String hql="Select s from Seance s,Users u,Assister a where u.id = :id "
					+ "and s.idS = a.seance.idS and a.users.id=u.id and a.status='ABSENCE'";
		try(Session session=HibernateUtil.getSessionFactory().getCurrentSession()){
			
			Transaction t = session.getTransaction();
			if (!TransactionStatus.ACTIVE.equals(t.getStatus())) {
	            t = session.beginTransaction();}
			Query<Seance>query = session.createQuery(hql);
	        query.setParameter("id", idu);
	        
	        if (!query.getResultList().isEmpty()) {
	        	
	        
			seances=query.list();
			
			for(Seance test:seances) {
				System.out.println(test.getIdS());
			}
			return seances;
			}
		}
		return null;
	}
	
	
	 public static List<Users> listEtudiant(int id,boolean premier){
	    	List<Users> etudiants = new ArrayList<>();
//	    	String hql = "select u.id, u.nom, u.prenom, u.formation,  " +
//	    				 "case when u.photo is null then 'img/person-icon.png' else u.photo end " +
//	    				 "from Seance s,Users u,Assister a " +
//	    				 "where s.idS = :id " +
//	    				 "and s.idS = a.seance.idS and a.users.id=u.id ";
	    	String hql="";
	    	if(premier) {
	    		
	    	
	    	hql = "select u " +
					 "from Seance s,Users u,Assister a " +
					 "where s.idS = :id " +
					 "and s.idS = a.seance.idS and a.users.id=u.id ";}
	    	else {
	    		hql="select u " +
	                    "from Seance s,Cours c " +
	                    " join c.usersParticipes u where s.idS = :id and s.coursSeance.idC = c.idC";
	    	}

	    	try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
	        	Transaction transaction=session.getTransaction();
	        	
		        if (!TransactionStatus.ACTIVE.equals(transaction.getStatus())) {
		        	transaction = session.beginTransaction();}
	            Query<Users>query = session.createQuery(hql);
	            query.setParameter("id", id);
	            if (!query.getResultList().isEmpty()) {
	            	etudiants=query.list();
	            }
//	            transaction.commit();
	            for(Users u:etudiants) {
	            	Pattern pattern = Pattern.compile("img", Pattern.CASE_INSENSITIVE);
	                Matcher matcher = pattern.matcher(u.getPhoto());
	                boolean matchFound = matcher.find();
	                if(!matchFound) {
	                  
	            		u.setPhoto("img/person-icon.png");
//	            		System.out.println("test");
	            	}
	            	System.out.println(u.getPhoto());
	            }
	            return etudiants;
	        } catch (Exception e) {
	            System.out.println("~~Error~~"+e.getMessage());
	        }
	        return null;
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
//			System.out.println("size : "+etus.size());
			for(Users us:etus) {
//				System.out.println(us.getEmail());
				AssisterId ai=new AssisterId(us.getId(),id);
//				System.out.println(ai.getCodeSeance());
				Assister a=new Assister(ai,AppelEtat.PRESENCE,us,s,0);
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
	//mettre status de Seance en enregistrer
	public static void initAssister(int ids) {
		try(Session session=HibernateUtil.getSessionFactory().getCurrentSession()){
			List<Users> etus=new ArrayList<>();
			Transaction t = session.getTransaction();
			if (!TransactionStatus.ACTIVE.equals(t.getStatus())) {
	            t = session.beginTransaction();}
			Seance s=session.get(Seance.class, ids);
			session.save(s);
			s.setStatutFicheAppel(FicheAppelEtat.ENREGISTRER);
			session.update(s);			
			t.commit();
			}
	}
	
	public static void changerStatusEtu(int idu,int ids,AppelEtat a) {
		try(Session session=HibernateUtil.getSessionFactory().getCurrentSession()){
			Transaction t = session.getTransaction();
			if (!TransactionStatus.ACTIVE.equals(t.getStatus())) {
	            t = session.beginTransaction();}
			Users u=session.get(Users.class, idu);
			AssisterId ai=new AssisterId(idu,ids);
			Assister assi=session.get(Assister.class, ai);
			assi.setStatus(a);
			session.update(assi);
			t.commit();
			}
	}

	public static List<Cours> allCours() {
		List<Cours> cours = new ArrayList<>();
		String hql = "select c " +
				"from Cours c";
		try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
			Transaction transaction=session.beginTransaction();
			Query<Cours>query = session.createQuery(hql);
			

			if (!query.getResultList().isEmpty()) {
				cours=query.list();

				return cours;
	            	}
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        
				return null;
		
	}
	public static List<Integer> listeSeance(int idC){
		try(Session session=HibernateUtil.getSessionFactory().getCurrentSession()){
			List<Integer> etus=new ArrayList<>();
			Transaction t = session.getTransaction();
			if (!TransactionStatus.ACTIVE.equals(t.getStatus())) {
	            t = session.beginTransaction();}
			Cours c=session.get(Cours.class, idC);
			for(Seance s:c.getSeance()) {
				if(s.getStatutFicheAppel()==FicheAppelEtat.VALIDER) {
					etus.add(s.getIdS());
				}
				
			}
			return etus;
			
			}
	}
	
//	liste des tous les etudiant
	public static List<Users> listeAssister() {
		String hql = "select u " +
				"from Users u "
				+ "where u.statut = 'ETUDIANT'";
		try(Session session=HibernateUtil.getSessionFactory().getCurrentSession()){
			List<Users> etus=new ArrayList<>();
//			List<Integer> abs=new ArrayList<>();
//			List<Integer> pres=new ArrayList<>();
//			List<Integer> retas=new ArrayList<>();
			Transaction t = session.getTransaction();
			if (!TransactionStatus.ACTIVE.equals(t.getStatus())) {
	            t = session.beginTransaction();}
			Query<Users>query = session.createQuery(hql);
			

			if (!query.getResultList().isEmpty()) {
				etus=query.list();

				return etus;
	            	}
			
			
			return null;
			}
	}
	
	public static List<List> listStaus(int idu) {
		try(Session session=HibernateUtil.getSessionFactory().getCurrentSession()){
			List<List> assis=new ArrayList<>();
			List<Integer> abs=new ArrayList<>();
			List<Integer> pres=new ArrayList<>();
			List<Integer> retas=new ArrayList<>();
			Transaction t = session.getTransaction();
			if (!TransactionStatus.ACTIVE.equals(t.getStatus())) {
	            t = session.beginTransaction();}
			Users u=session.get(Users.class, idu);
			List<Seance> seances=new ArrayList<>();
			String hql="Select s "+
						"from Seance s,Users u,Assister a "
						+ "where u.id = :id "
						+ "and s.idS = a.seance.idS and a.users.id=u.id and s.StatutFicheAppel='VALIDER'";
			Query<Seance>query = session.createQuery(hql);
            query.setParameter("id", idu);
            
            if (!query.getResultList().isEmpty()) {
            	seances=query.list();
            	}
			for(Seance s:seances) {
				AssisterId asi=new AssisterId(idu,s.getIdS());
				Assister a=session.get(Assister.class, asi);
				if(a.getStatus()==AppelEtat.ABSENCE || a.getStatus()==AppelEtat.ABSENCE_JUSTIFIE) {
					abs.add(s.getIdS());
				}else if(a.getStatus()==AppelEtat.RETARD) {
					retas.add(s.getIdS());
				}else {
					pres.add(s.getIdS());
				}
			}
			assis.add(pres);
			assis.add(retas);
			assis.add(abs);
			
			return assis;
			
			}
	}
	
	public static void testlist(int idu) {
		List<Seance> seances=new ArrayList<>();
		String hql="Select s from Seance s,Users u,Assister a where u.id = :id "
					+ "and s.idS = a.seance.idS and a.users.id=u.id";
		try(Session session=HibernateUtil.getSessionFactory().getCurrentSession()){
			
			Transaction t = session.getTransaction();
			if (!TransactionStatus.ACTIVE.equals(t.getStatus())) {
	            t = session.beginTransaction();}
			Query<Seance>query = session.createQuery(hql);
	        query.setParameter("id", idu);
	        
	        if (!query.getResultList().isEmpty()) {
	        	seances=query.list();
	        	}
			for(Seance s:seances) {
				System.out.println(s.getIdS());
			}
		}
	}
	
	public static List<Integer> getCours(int idu) {
//		lescoursParticipes
		try(Session session=HibernateUtil.getSessionFactory().getCurrentSession()){
			
			Transaction t = session.getTransaction();
			if (!TransactionStatus.ACTIVE.equals(t.getStatus())) {
	            t = session.beginTransaction();}
			Users u=session.get(Users.class, idu);
			List<Integer> cours=new ArrayList<>();
			for(Cours c:u.getLescoursParticipes()) {
				cours.add(c.getIdC());
				
			}
			return cours;
			}
	}
	
	
	public static List<Cours> getEtuCours(int idu) {
//		lescoursParticipes
		try(Session session=HibernateUtil.getSessionFactory().getCurrentSession()){
			
			Transaction t = session.getTransaction();
			if (!TransactionStatus.ACTIVE.equals(t.getStatus())) {
	            t = session.beginTransaction();}
			Users u=session.get(Users.class, idu);
			List<Cours> cours=new ArrayList<>();
			for(Cours c:u.getLescoursParticipes()) {
				cours.add(c);
				
			}
			return cours;
			}
	}
	
	public static Users getUser(int id) {
		try(Session session=HibernateUtil.getSessionFactory().getCurrentSession()){
			
			Transaction t = session.getTransaction();
			if (!TransactionStatus.ACTIVE.equals(t.getStatus())) {
	            t = session.beginTransaction();}
			return session.get(Users.class, id);
			}
	}
	
	public static List<List> listStaus(int idu,List<Integer> seances) {
		List<List> assis=new ArrayList<>();
		List<Integer> abs=new ArrayList<>();
		List<Integer> pres=new ArrayList<>();
		List<Integer> retas=new ArrayList<>();
		try(Session session=HibernateUtil.getSessionFactory().getCurrentSession()){
			
			Transaction t = session.getTransaction();
			if (!TransactionStatus.ACTIVE.equals(t.getStatus())) {
	            t = session.beginTransaction();}
			for(int ids:seances) {
				
				AssisterId asi=new AssisterId(idu,ids);
				Assister a=session.get(Assister.class, asi);
				if(a.getStatus()==AppelEtat.ABSENCE || a.getStatus()==AppelEtat.ABSENCE_JUSTIFIE) {
					abs.add(ids);
				}else if(a.getStatus()==AppelEtat.RETARD) {
					retas.add(ids);
				}else {
					pres.add(ids);
				}
			}
			assis.add(pres);
			assis.add(retas);
			assis.add(abs);
			
			return assis;
		}
	}
	
	/**
	 * Programme de test.
	 * @throws ParseException
	 */
	

} /*----- Fin de la classe TestHibernate -----*/
