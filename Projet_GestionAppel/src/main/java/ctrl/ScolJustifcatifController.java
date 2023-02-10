package ctrl;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.JustificatifDAO;
import dao.UsersDao;
import metier.Justificatif;

/**
 * Servlet implementation class ScolJustifcatifController
 */

@WebServlet("/ScolJustificatifController")
public class ScolJustifcatifController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		 JustificatifDAO justificatifDAO = new JustificatifDAO();
		 ArrayList<Justificatif> justif = new ArrayList<>();
		 justif = justificatifDAO.loadJustificatif();
		 request.setAttribute("Justificatif", justif);
		 if(justif.size() == 0 || justif == null) {
			 request.setAttribute("msg_vide", "Vous n'avez aucun justificatif a consulter...");
		     request.getRequestDispatcher("ConsultationJustif").forward(request, response);

		 }else {
			 request.getRequestDispatcher("ConsultationJustif").forward(request, response);
		 }
	    
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
