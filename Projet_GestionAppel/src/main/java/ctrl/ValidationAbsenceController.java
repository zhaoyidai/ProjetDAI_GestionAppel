package ctrl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.TestHibernate;
import enumtype.AppelEtat;
import metier.Mail;
import metier.Seance;

/**
 * Servlet implementation class ValidationAbsenceController
 */
@WebServlet("/ValidationAbsenceController")
public class ValidationAbsenceController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Je recupere les valaurs cochées dans mon formulaire sur Supprimer.jsp et disponible dans l'url de la requete HTTP
			
				String action = request.getParameter("type_action");
				
				Integer codeJ = Integer.parseInt(request.getParameter("idJ"));
				Integer idU = Integer.parseInt(request.getParameter("idU"));


				switch(action) {
				case "valider":
					request.setAttribute("idU", idU);
					TestHibernate.etuSeancelistAbs(idU);
					List<Seance> seance = (List<Seance>) TestHibernate.etuSeancelistAbs(idU);
					request.setAttribute("seance", seance);
					request.setAttribute("codeJ", codeJ);
					request.getRequestDispatcher("ListeAbsencesSeance").forward(request, response);
					break;
				
				case "refuser" :
					TestHibernate.updateJustifRefus(codeJ);
					Mail mail = new Mail();
					mail.sendMailRefusJustif("chartelain.david@gmail.com",idU);
					request.setAttribute("infos", "Le mail de refus a été envoyé a l'etudiant !");
					request.getRequestDispatcher("ScolJustificatifController").forward(request, response);
					break;
				}
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				//Je crée une liste de message de type Integer
//				ArrayList<Integer> listevalidation = new ArrayList<>();
//				
//				try {
//				if(listevalider!=null){
//					for(String i:listevalider) {
//						int idu=Integer.valueOf(i);
//						Mail mail = new Mail();
//						mail.sendMailValidationJustif("chartelain.david@gmail.com",idu);
//						request.setAttribute("msg_validation", "Les justificatifs ont été validé avec succès !");
//					}
//				}
//				if(listerefuser!=null) {
//					for(String i:listerefuser) {
//						int idu=Integer.valueOf(i);
//						Mail mail = new Mail();
//						mail.sendMailRefusJustif("chartelain.david@gmail.com",idu);
//						request.setAttribute("msg_validation2", "Les justificatifs rejetés ont été notifié aux éleves concernés !");
//					}
//				}
//				request.getRequestDispatcher("ScolJustificatifController").forward(request, response);
//				}catch (Exception e) {
//						request.setAttribute("checkbox_vide", "Veuillez selectionner des élements a supprimer !");
//						request.getRequestDispatcher("ScolJustificatifController").forward(request, response);
//					
//				}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
