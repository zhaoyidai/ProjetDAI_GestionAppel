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
	  private static final String HOST = "smtp-mail.outlook.com";
	  /**
	   * Adresse de la boite mail d'envoi.
	   */
	  private static final String USER = "home-web31@outlook.com";
	  /**
	   * Mot de passe de la boite mail d'envoi.
	   */
	  private static final String MDP = "ProjetAgile";
	  

	  /**
	   * Méthode pour envoyer un mail après l'achat d'une action.
	   * @param to Adresse de la boite mail de réception.
	   * @param a Action acheté qui correspond à l'envoi.
	   * @param qte Quantité d'action acheter.
	   */
	  public final void envoyerMailScolarité(final String to, final  Users users, String datedeb, String datefin) {
	   Properties props = new Properties();
	   props.put("mail.smtp.host", this.HOST);
	   props.put("mail.smtp.auth", "true");
	   props.put("mail.smtp.starttls.enable", "true");

	   Authenticator auth = new Authenticator() {
	      protected PasswordAuthentication getPasswordAuthentication() {
	              return new PasswordAuthentication(USER, MDP);
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
	  
	  
	  public final void envoyerMailAbsenceEtudiant(final String to, final  Users users , Date idS) {
		   Properties props = new Properties();
		   props.put("mail.smtp.host", this.HOST);
		   props.put("mail.smtp.auth", "true");
		   props.put("mail.smtp.starttls.enable", "true");

		   Authenticator auth = new Authenticator() {
		      protected PasswordAuthentication getPasswordAuthentication() {
		              return new PasswordAuthentication(USER, MDP);
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
	                 "\nMr/Mme"+users.getNom()+" "+users.getPrenom()+" vous avez été notifié absent pour la seance du "+idS+
	                 "\nPour justifier cette absence, veuillez deposer un justificatif via l'application Gest Appel."
	                 +"\nCordialement,"+
	                 "\nLa scolarité");
		     
		    Transport.send(message);
		   
		     } catch (MessagingException e) {e.printStackTrace();}
		 }
	
	  public final void sendMailValidationJustif(final String to, final int i ) {
		   Properties props = new Properties();
		   props.put("mail.smtp.host", this.HOST);
		   props.put("mail.smtp.auth", "true");
		   props.put("mail.smtp.starttls.enable", "true");

		   Authenticator auth = new Authenticator() {
		      protected PasswordAuthentication getPasswordAuthentication() {
		              return new PasswordAuthentication(USER, MDP);
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
	                 "\nMr/Mme votre depot de justifiatif a été validé !"+
	                 "\nCordialement,"+
	                 "\nLa scolarité");
		     
		    Transport.send(message);
		   
		     } catch (MessagingException e) {e.printStackTrace();}
		 }
	  
	  
	  
	  
	  public final void sendMailRefusJustif(final String to, final  int i) {
		   Properties props = new Properties();
		   props.put("mail.smtp.host", this.HOST);
		   props.put("mail.smtp.auth", "true");
		   props.put("mail.smtp.starttls.enable", "true");

		   Authenticator auth = new Authenticator() {
		      protected PasswordAuthentication getPasswordAuthentication() {
		              return new PasswordAuthentication(USER, MDP);
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
	                 "\nMr/Mme votre depot de justifiatif a été refusé ! Merci de deposer un nouveau document dans l'espace dédié a cet effet ! "+
	                 "\nCordialement,"+
	                 "\nLa scolarité");
		     
		    Transport.send(message);
		   
		     } catch (MessagingException e) {e.printStackTrace();}
		 }
	  
	  
}
