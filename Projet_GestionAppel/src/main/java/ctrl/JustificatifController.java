package ctrl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.SeanceDAO;
import dao.TestHibernate;
import dao.UsersDao;
import metier.Seance;

/**
 * Servlet implementation class JustificatifController
 */
@WebServlet("/JustificatifController")
public class JustificatifController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		int idU =  Integer.parseInt(id);
		
		
		request.setAttribute("id", idU);
		
		HttpSession sessionliste = request.getSession();
		List<Seance>seances =  TestHibernate.etuSeancelistAbs(idU);
		sessionliste.setAttribute("listesabsences", seances);
		
		request.setAttribute("listeAbscences", seances);
		request.getRequestDispatcher("Justificatif").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
