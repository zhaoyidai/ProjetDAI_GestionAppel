package dao;


import org.hibernate.Session;
import org.hibernate.query.Query;

import metier.Users;

public class UsersDao extends DAO<Users> {

    public UsersDao() {
        super.setEntity(Users.class);
    }
   

    public Integer loginUtilisateur(String email, String pwd) {
        Integer utilisateurId = null;
        try (Session session = getSession()) {
            getTransaction(session);
            Query<Integer> query = session.createQuery("select u.id " +
                    "from Users u " +
                    "where u.email = :email " +
                    "and u.password = :password");
            query.setParameter("email", email);
            query.setParameter("password", pwd);
            if (!query.getResultList().isEmpty()) {
                utilisateurId = query.uniqueResult();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return utilisateurId;
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

}
