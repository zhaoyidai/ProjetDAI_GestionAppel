package metier;

import java.awt.Desktop.Action;
import java.util.Date;
import java.util.Properties;
import javax.mail.*;  
import javax.mail.internet.*;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.PasswordAuthentication;

public class Mail {

	  /**
	   * Host du mail.
	   */
	  private static final String HOST = "smtp.gmail.com";
	  /**
	   * Adresse de la boite mail d'envoi.
	   */
	  private static final String USER = "chartelain.david@gmail.com";
	  /**
	   * Mot de passe de la boite mail d'envoi.
	   */
	  private static final String MDP = "chartelain972";
	  

	  /**
	   * Méthode pour envoyer un mail après l'achat d'une action.
	   * @param to Adresse de la boite mail de réception.
	   * @param a Action acheté qui correspond à l'envoi.
	   * @param qte Quantité d'action acheter.
	   */
	  public final void envoyerMail(final String to, final  Users users, Date datedeb, Date datefin) {
	   Properties props = new Properties();
	   props.put("mail.smtp.host","smtp.gmail.com");
	   props.put("mail.smtp.auth", "true");
	   props.put("mail.smtp.ssl.enable", "true");
	    props.setProperty("mail.smtp.socketFactory.fallback", "false");
	    props.setProperty("mail.smtp.port", "25");
	    props.setProperty("mail.smtp.socketFactory.port", "25");

	   Authenticator auth = new Authenticator() {
	      protected PasswordAuthentication getPasswordAuthentication() {
	              return new PasswordAuthentication(USER, "ProjetAgile");
	      }
	    };
	    Session session = Session.getInstance(props, auth);
	    
	    try {
	     MimeMessage message = new MimeMessage(session);
	     message.setFrom(new InternetAddress(USER));
	     message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
	     message.setSubject("Récapitulatif");
	     message.setText("" +
                 "Bonjour,"+
                 "\nL'étudiant "+users.getNom()+" "+users.getPrenom()+" vient d'ajouter un justificatif pour son absence du "+datedeb+" au "+datefin+
                 "\nVous pouvez la consulter depuis l'application."
                 +"\nCordialement,"+
                 "\nHomeWeb");
	     
	    Transport.send(message);
	   
	     } catch (MessagingException e) {e.printStackTrace();}
	 }
	
}
