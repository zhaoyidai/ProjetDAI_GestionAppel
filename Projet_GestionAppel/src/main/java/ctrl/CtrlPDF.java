package ctrl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EtudiantPresence;
import dao.TestHibernate;

/**
 * Servlet implementation class CtrlPDF
 */
@WebServlet("/CtrlPDF")
public class CtrlPDF extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idSeance = request.getParameter("idSeance");
		int ids=Integer.valueOf(idSeance);
		List<EtudiantPresence> listeEtudiant=TestHibernate.loadEtudiant(ids);
		if(listeEtudiant!=null) {
			request.setAttribute("idSeance",idSeance);
			
			request.setAttribute("listeEtudiant", listeEtudiant);
			request.getRequestDispatcher("Pdf.jsp").forward(request, response);
		}
		else {
			request.getRequestDispatcher("Pdf.jsp").forward(request, response);
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
