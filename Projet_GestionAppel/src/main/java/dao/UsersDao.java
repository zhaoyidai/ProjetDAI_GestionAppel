package dao;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.catalina.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import metier.Seance;
import metier.Users;

public class UsersDao extends DAO<Users> {

    public UsersDao() {
        super.setEntity(Users.class);
    }
   

    public static Users loginUtilisateur(String email, String pwd) {
        Users users = null;
    	String hql = "select u " +
                "from Users u " +
                "where u.email = :email " +
                "and u.password = :password ";
        try (Session session = UsersDao.getSession()) {
        	Transaction transaction=UsersDao.getTransaction(session);
            Query<Users>query = session.createQuery(hql);
            query.setParameter("email", email);
            query.setParameter("password", pwd);
            if (!query.getResultList().isEmpty()) {
            	users = query.uniqueResult();
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public boolean emailExiste(String email) {
        String hql = "select u from Utilisateur u where u.email = :email";
        try (Session session = getSession()){
            getTransaction(session);
            Query<Users> query = session.createQuery(hql, Users.class);
            query.setParameter("email", email);
            if (!query.getResultList().isEmpty()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    
//	  public void updatePhotoProfil(String chemin , int user) {
//		  String hql = "update Users u set u.photo=chemin' where u.id=:user";
//	        try (Session session = getSession()){
//	            getTransaction(session);
//	            Query query = session.createQuery(hql);
//	            query.setParameter("chemin", chemin);
//	            query.setParameter("user", user);
//	            query.executeUpdate();
//	            session.clear();
//	            session.close();
//	        }
//	    }
    
	  public static void updatePhotoProfil(String chemin,int user){
		  	Session sess=null;
		  	try {
			  String hql = "update Users u set u.photo = :chemin' where u.id = :user";
			  Query query = sess.createQuery(hql);
			  query.setString("chemin", chemin);
	          query.setInteger("user", user);
	          int rowCount = query.executeUpdate();
		    } catch (Exception e) {
	            e.printStackTrace();
	        }
	  }

//    // m??thode permettant d'avoir la liste des ??tudiants
//    public List<Users> listEtudiant(){
//    	List<Users> etudiants = new ArrayList<>();
//    	String hql = "select u " +
//                "from Users u " +
//                "where u.statut LIKE 'ETUDIANT' ";
//        try (Session session = getSession()) {
//        	Transaction transaction=getTransaction(session);
//            Query<Users>query = session.createQuery(hql);
//            if (!query.getResultList().isEmpty()) {
//            	etudiants=query.list();
//            }
//            transaction.commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return etudiants;
//    }


    // m??thode permettant d'avoir la liste des seances a laquelle l'utilisateur est absent.
    public List<Seance> listAbsencesEtudiant(String id){
    	List<Seance> seances = new ArrayList<>();
    	String hql = "select s , c " +
                "from Users u , Seance s , Cours c, Assister a" +
                "where u.id = a.users "+
                "and s.idS = a.seance "+
                "and c.idC = s.coursSeance"+
                "and a.statut LIKE 'ABSENCE' "+
                "and u.id = :id ";
        try (Session session = getSession()) {
        	Transaction transaction=getTransaction(session);
            Query<Seance>query = session.createQuery(hql);
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
