package ctrl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TestHibernate;
import dao.UsersDao;
import metier.Seance;
import metier.Users;

/**
 * Servlet implementation class CtrlAccederFicheAppel
 */
@WebServlet("/CtrlAccederFicheAppel")
public class CtrlAccederFicheAppel extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idSeance = request.getParameter("idSeance");
//		List<String> emails=new ArrayList<>();
//		for(Users u:UsersDao.listEtudiant()) {
//			
//		}
		int ids=Integer.valueOf(idSeance);
		List<Users> listeEtudiant= TestHibernate.listEtudiant(ids,TestHibernate.affichestatus(ids));
		if(listeEtudiant!=null) {
			request.setAttribute("idSeance",idSeance);
			
			request.setAttribute("listeEtudiant", listeEtudiant);
			request.getRequestDispatcher("Cours").forward(request, response);
		}
		else {
			request.getRequestDispatcher("Accueil").forward(request, response);
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
