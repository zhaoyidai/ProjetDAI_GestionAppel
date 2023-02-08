package dao;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import metier.Users;

public class UsersDao extends DAO<Users> {

    public UsersDao() {
        super.setEntity(Users.class);
    }
   

    public Users loginUtilisateur(String email, String pwd) {
        Users users = null;
    	String hql = "select u " +
                "from Users u " +
                "where u.email = :email " +
                "and u.password = :password ";
        try (Session session = getSession()) {
        	Transaction transaction=getTransaction(session);
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
    public static List<Users> listEtudiant(int id,boolean premier){
    	List<Users> etudiants = new ArrayList<>();
//    	String hql = "select u.id, u.nom, u.prenom, u.formation,  " +
//    				 "case when u.photo is null then 'img/person-icon.png' else u.photo end " +
//    				 "from Seance s,Users u,Assister a " +
//    				 "where s.idS = :id " +
//    				 "and s.idS = a.seance.idS and a.users.id=u.id ";
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
//            transaction.commit();
            for(Users u:etudiants) {
            	Pattern pattern = Pattern.compile("img", Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(u.getPhoto());
                boolean matchFound = matcher.find();
                if(!matchFound) {
                  
            		u.setPhoto("img/person-icon.png");
//            		System.out.println("test");
            	}
            	System.out.println(u.getPhoto());
            }
            return etudiants;
        } catch (Exception e) {
            System.out.println("~~Error~~"+e.getMessage());
        }
        return null;
    }


//    // méthode permettant d'avoir la liste des étudiants
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


}
