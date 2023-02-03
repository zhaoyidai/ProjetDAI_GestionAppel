package dao;

import dao.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import java.util.List;

/**
 * Cette classe implémente plusieurs méthodes qui permettront d'interagir avec la base de données.
 * @param <T> L'objet qui représente le modèle dans la base de données
 */
public abstract class DAO<T> {

    private final SessionFactory SESSION_FACTORY = HibernateUtil.getSessionFactory();
    private Class<T> entity;

    /**
     * Ce constructeur permet d'affecter à la classe qui hérite de DAO, la classe d'entité associée.
     * @param entity Le type de la class d'entité
     */
    public void setEntity(Class<T> entity) {
        this.entity = entity;
    }

    /**
     * @return Un objet de type Session
     */
    protected final Session getSession() {
        Session session = null;
        try {
            session = SESSION_FACTORY.getCurrentSession();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (session == null) {
            session = SESSION_FACTORY.openSession();
        }
        return session;
    }

    /**
     * @param session L'objet Session
     * @return Un objet de type Transaction
     */
    protected final Transaction getTransaction(Session session) {
        Transaction transaction = session.getTransaction();
        if (!TransactionStatus.ACTIVE.equals(transaction.getStatus())) {
            transaction = session.beginTransaction();
        }
        return transaction;
    }

    /**
     * Permet de créer une entrée dans la base de données.
     * @param entity L'objet que l'on veux créer
     * @return L'identifiant de l'objet crée initialement
     */
    public Integer create(T entity) {
        Transaction transaction = null;
        Integer id = null;
        try (Session session = this.getSession()) {
            transaction = this.getTransaction(session);
            id = (Integer) session.save(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return id;
    }

    /**
     * Permet de récupérer un objet via son ID dans la base de données.
     * @param id L'identifiant de l'objet en base de donnée
     * @return Un objet de type T
     */
    public T find(Integer id) {
        try (Session session = this.getSession()) {
            Transaction transaction = getTransaction(session);
            return session.get(entity, id);
        }
    }

    /**
     * Permet de mettre à jour les données d'une entrée dans la base de données.
     * @param entity L'objet à modifier
     */
    public void update(T entity) {
        Transaction transaction = null;
        try (Session session = this.getSession()) {
            transaction = this.getTransaction(session);
            session.update(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void createOrUpdate(T entity) {
        Transaction transaction = null;
        try (Session session = this.getSession()) {
            transaction = this.getTransaction(session);
            session.saveOrUpdate(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }


    /**
     * Petmet d'avoir accès à tous les objet T de la base de données.
     * @return Une liste d'object
     */
    public List<T> findAll() {
        List<T> entities;
        try (Session session = this.getSession()) {
            Transaction transaction = getTransaction(session);
            entities = session.createQuery("from " + this.entity.getName()).list();
        }
        return entities;
    }

    /**
     * Permet la suppression d'une entrée de la base de données.
     * @param entity L'objet à supprimer
     */
    public void delete(T entity) {
        Transaction transaction = null;
        try (Session session = this.getSession()) {
            transaction = this.getTransaction(session);
            session.delete(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

}
