package ctrl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TestHibernate;
import enumtype.AppelEtat;

/**
 * Servlet implementation class CtrlEnregistrer
 */
@WebServlet("/CtrlEnregistrer")
public class CtrlEnregistrer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] listeAbs=(String[])request.getParameterValues("absence");
		String[] listeRet=(String[])request.getParameterValues("retard");
		String idSeance = request.getParameter("idSeance");
		int ids=Integer.valueOf(idSeance);
		for(String i:listeAbs) {
			int idu=Integer.valueOf(i);
			TestHibernate.changerStatusEtu(idu, ids, AppelEtat.ABSENCE);
		}
		for(String ir:listeRet) {
			int idu=Integer.valueOf(ir);
			TestHibernate.changerStatusEtu(idu, ids, AppelEtat.RETARD);
		}
		request.setAttribute("msg_info", "Fiche d'Appel"+idSeance+" enregistré.");
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
