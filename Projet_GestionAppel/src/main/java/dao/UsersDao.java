package dao;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

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


    // méthode permettant d'avoir la liste des étudiants
    public List<Users> listEtudiant(){
    	List<Users> etudiants = new ArrayList<>();
    	String hql = "select u " +
                "from Users u " +
                "where u.statut LIKE 'ETUDIANT' ";
        try (Session session = getSession()) {
        	Transaction transaction=getTransaction(session);
            Query<Users>query = session.createQuery(hql);
            if (!query.getResultList().isEmpty()) {
            	etudiants=query.list();
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return etudiants;
    }


}
