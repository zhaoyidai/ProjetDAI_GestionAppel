package ctrl;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TestHibernate;
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
		
		switch(idSeance) {
		case "planning":
			
			int id=((Users)request.getSession().getAttribute("auth")).getId();
			List<Seance> seances=TestHibernate.loadSeance(id);
			
			request.setAttribute("seances", seances);
			request.getRequestDispatcher("Planning").forward(request, response);
			break;
		default:
			request.getRequestDispatcher("Accueil").forward(request, response);}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
