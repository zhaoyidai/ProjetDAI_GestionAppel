package ctrl;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bd.BD;
import dao.JustificatifDAO;

/**
 * Servlet implementation class CtrlConfirmationValidation
 */
@WebServlet("/CtrlConfirmationValidation")
public class CtrlConfirmationValidation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//J'ouvre ma session existante 
		HttpSession session = request.getSession();
		//Je recupere mon tableau stocké dans ma session par le name 'listeIDsession' grace a session.getAttribute
		ArrayList<Integer> listeID = (ArrayList<Integer>) session.getAttribute( "listeIDsession" );
		//Je fais ma gestion d'erreur
		try { 
			//J'appelle ma fonction supprimer de ma bdd en passant en parametre ma liste des id a supprimer
			JustificatifDAO justificatif = new JustificatifDAO();
			//justificatif.updateAbsences(listeID);
			//CHAINAGE
			//Je crée un msg de confirmation pour avoir un retour sur la validation de mon action de suppression
			request.setAttribute("msg_confirmation", "Votre selection a été supprimée avec succès");
			//Je redirige ce message vers la page accueil + msg confirmation
			request.getRequestDispatcher("Accueil").forward(request, response);		
		} catch (Exception e) {
			//Si quelque chose plante , je catch mon erreur en affichant la source du probleme sur ma page
			request.setAttribute("msg_error", e.getMessage());
			request.getRequestDispatcher("Confirmation").forward(request, response);	
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
