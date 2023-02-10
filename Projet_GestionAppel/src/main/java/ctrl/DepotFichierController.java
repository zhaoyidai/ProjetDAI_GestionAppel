package ctrl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.PasswordAuthentication;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.hibernate.Session;

import dao.JustificatifDAO;
import dao.UsersDao;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

import metier.Mail;
import metier.Users;

import javax.servlet.annotation.MultipartConfig;

@WebServlet(urlPatterns = {"/DepotFichierController","/uploadform"})
@MultipartConfig(
		maxFileSize = 10000000,      // 4 MB
		maxRequestSize = 10000000 ,  // 4 MB
		fileSizeThreshold = 10000000 // 4 MB
		)
/**
 * Servlet implementation class Test
 */

public class DepotFichierController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final int TAILLE_TAMPON = 10240;

	public static final String CHEMIN_FICHIERS = "C://Justificatif/";

	private static final SimpleDateFormat DFDATE = new SimpleDateFormat("dd-MM-yyyy");



	public DepotFichierController() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sessionliste = request.getSession();
		sessionliste.getAttribute("listesabsences");
		this.getServletContext().getRequestDispatcher("Justificatif").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("type_action");


		switch(action) {
		case "upload":
			// On récupère le champ description comme d'habitude
			String des = request.getParameter("description");
			request.setAttribute("description", des );

			// On récupère le champ du fichier
			Part p = request.getPart("fichier");

			File doss = new File("C://Justificatif/"); 

			// On vérifie qu'on a bien reçu un fichier
			String nomF = getNomFichier(p);

			// si le directory n'existe pas le creer
			if (!doss.exists()) {
				boolean res = doss.mkdir();
			}
			//Créer une copie du PDF
			//on recupere le pdf
			String nomfich = getNomFichier(p);
			System.out.println(nomfich);
			if (nomfich!= null) {
				ecrireFichier(p, nomfich, CHEMIN_FICHIERS );
				String chemin = "http://localhost:8080/Projet_GestionAppel/fichier/" + nomfich;

				UsersDao user = new UsersDao();
				Users users = (Users) request.getSession().getAttribute("auth");
				int id = users.getId();
				String debut = request.getParameter("debut");
				System.out.print(debut);
				String fin = request.getParameter("fin");
				user.updatePhotoProfil(chemin, id);  
			}

			this.getServletContext().getRequestDispatcher("/Profil.jsp").forward(request, response);	


			break;

		default:

			// On récupère le champ description comme d'habitude
			String description = request.getParameter("description");
			request.setAttribute("description", description );

			// On récupère le champ du fichier
			Part part = request.getPart("fichier");

			File dossier = new File("C://Justificatif/"); 

			// On vérifie qu'on a bien reçu un fichier
			String nomFichier = getNomFichier(part);

			// si le directory n'existe pas le creer
			if (!dossier.exists()) {
				boolean res = dossier.mkdir();
			}
			//Créer une copie du PDF
			//on recupere le pdf
			String nomfichier = getNomFichier(part);
			System.out.println(nomfichier);
			if (nomfichier!= null) {
				ecrireFichier(part, nomfichier, CHEMIN_FICHIERS );
				String chemin = "http://localhost:8080/Projet_GestionAppel/fichier/" + nomfichier;

				JustificatifDAO justificatif = new JustificatifDAO();
				Users users = (Users) request.getSession().getAttribute("auth");

				String debut = request.getParameter("debut");
				System.out.print(debut);
				String fin = request.getParameter("fin");
				try {

					justificatif.createJustificatif(DFDATE.parse(debut), DFDATE.parse(fin), chemin, false, users);
					//Un mail est envoyé à la scolarité à chaque insertion de justificatif
					Mail mail = new Mail();
					mail.envoyerMailScolarité("chartelain.david@gmail.com", users, debut, fin);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
			}

			try {
				request.setAttribute("msg_validation", "Votre justificatif a été deposé avec succès! ");
				this.getServletContext().getRequestDispatcher("/Justificatif.jsp").forward(request, response);
				break;
			}catch (Exception e) {
				request.setAttribute("msg_error", "Un probleme est survenue ! Votre depot n'a pas été pris en compte.");
				this.getServletContext().getRequestDispatcher("/Justificatif.jsp").forward(request, response);
				break;
			}
			
		}
	}

	private void ecrireFichier( Part part, String nomFichier, String chemin ) throws IOException {
		BufferedInputStream entree = null;
		BufferedOutputStream sortie = null;
		try {
			entree = new BufferedInputStream(part.getInputStream(), TAILLE_TAMPON);
			sortie = new BufferedOutputStream(new FileOutputStream(new File(chemin + nomFichier)), TAILLE_TAMPON);

			byte[] tampon = new byte[TAILLE_TAMPON];
			int longueur;
			while ((longueur = entree.read(tampon)) > 0) {
				sortie.write(tampon, 0, longueur);
			}
		} finally {
			try {
				sortie.close();
			} catch (IOException ignore) {
			}
			try {
				entree.close();
			} catch (IOException ignore) {
			}
		}
	}

	private static String getNomFichier( Part part ) {
		for ( String contentDisposition : part.getHeader( "content-disposition" ).split( ";" ) ) {
			if ( contentDisposition.trim().startsWith( "filename" ) ) {
				return contentDisposition.substring( contentDisposition.indexOf( '=' ) + 1 ).trim().replace( "\"", "" );
			}
		}
		return null;
	}


}
