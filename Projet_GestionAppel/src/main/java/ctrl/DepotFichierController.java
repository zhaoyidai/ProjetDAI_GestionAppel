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
    
    public static final String CHEMIN_FICHIERS = Path.of("C:/Users/David_C/git/ProjetDAI_GestionAppel/Projet_GestionAppel/src/main/webapp/justificatif/").toString() ;
   
    
       
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
			
		  // On récupère le champ description comme d'habitude
        String description = request.getParameter("description");
        request.setAttribute("description", description );

        // On récupère le champ du fichier
        Part part = request.getPart("fichier");
            
        // On vérifie qu'on a bien reçu un fichier
        String nomFichier = getNomFichier(part);

        // Si on a bien un fichier
        if (nomFichier != null && !nomFichier.isEmpty()|| part == null) {
            String nomChamp = part.getName();
            // Corrige un bug du fonctionnement d'Internet Explorer
             nomFichier = nomFichier.substring(nomFichier.lastIndexOf('/') + 1)
                    .substring(nomFichier.lastIndexOf('\\') + 1);

            // On écrit définitivement le fichier sur le disque
            ecrireFichier(part, nomFichier, CHEMIN_FICHIERS);
            

            request.setAttribute(nomChamp, nomFichier);
            JustificatifDAO justificatif = new JustificatifDAO();
            Users users = (Users) request.getSession().getAttribute("auth");
           
            String debut = request.getParameter("debut");
            String fin = request.getParameter("fin");
            try {
				Date datedeb =new SimpleDateFormat("dd-MM-yyyy").parse(debut);
				Date datefin =new SimpleDateFormat("dd-MM-yyyy").parse(fin);
				String chemin = request.getContextPath() + nomFichier;
				justificatif.createJustificatif(datedeb, datefin, chemin, false, users);
				//Un mail est envoyé à la scolarité à chaque insertion de justificatif
				Mail mail = new Mail();
				mail.envoyerMail("chartelain.david@gmail.com", users,datedeb,datefin);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
        }

        this.getServletContext().getRequestDispatcher("/Justificatif.jsp").forward(request, response);
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
