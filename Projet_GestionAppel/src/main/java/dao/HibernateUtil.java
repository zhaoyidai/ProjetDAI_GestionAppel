package dao;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


/**
 * Chargement de la configuration et création de la SessionFactory.
 * (hibernate.cfg.xml)
 */
public class HibernateUtil
{
	private static final SessionFactory SESSION_FACTORY;

	static
		{
		try	{
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            System.out.println("Hibernate Configuration loaded");

			/**
			 * Ajout des classes.
			 * Pour miage.metier.Employe le fichier ressource hbm.xml
			 * attaché est metier/Employe.hbm.xml.
			 */
//			configuration.addClass(metier.Employe.class);

			/**
			 * Entité.(a rajouter)
			 */
			configuration.addAnnotatedClass(metier.Users.class);
			configuration.addAnnotatedClass(metier.Justificatif.class);
			configuration.addAnnotatedClass(metier.Cours.class);
			configuration.addAnnotatedClass(metier.Seance.class);
			configuration.addAnnotatedClass(metier.Assister.class);
			configuration.addAnnotatedClass(metier.AssisterId.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            System.out.println("Hibernate serviceRegistry created");

            SESSION_FACTORY = configuration.buildSessionFactory(serviceRegistry);
			}
		catch (HibernateException ex)
			{
			/*----- Exception -----*/
			System.err.println("Initial SessionFactory creation failed.\n" + ex);
			throw new ExceptionInInitializerError(ex);
			}
		}

	public static SessionFactory getSessionFactory () { return SESSION_FACTORY; }

} /*----- Fin de la classe HibernateUtil -----*/
