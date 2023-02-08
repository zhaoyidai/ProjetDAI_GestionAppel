package ctrl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TestHibernate;
import dao.UsersDao;

/**
 * Servlet implementation class CtrlValiderFiche
 */
@WebServlet("/CtrlValiderFiche")
public class CtrlValiderFiche extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idSeance = request.getParameter("ids");

		int ids=Integer.valueOf(idSeance);
		//valider fiche dans la base 
		TestHibernate.validerFiche(ids);
		
		request.setAttribute("msg_info", "Fiche d'Appel"+idSeance+" validé.");
		request.getRequestDispatcher("Accueil").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
