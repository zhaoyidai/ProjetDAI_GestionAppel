package ctrl;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
				String[] values = request.getParameterValues("chbx");
				//Je crée une liste de message de type Integer
				ArrayList<Integer> listevalidation = new ArrayList<>();
				
				if(values == null) {
					request.setAttribute("checkbox_vide", "Veuillez selectionner des élements a supprimer !");
					request.getRequestDispatcher("ConsultationJustif").forward(request, response);
				}else {
					//Je transvase mes valeurs en integer dans ma nouvelle liste en integer
					for(String m : values) {
						listevalidation.add(Integer.parseInt(m));
					}
					//Je crée une nouvelle session
					HttpSession session = request.getSession();
					//je crée un nouvel attribut listeIDsession dans ma session en affectant a celui ci ma liste d'ID (listemessage)
					session.setAttribute("liste", listevalidation);
					//Je redirectionne vers ma page Confirmation.jsp
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
