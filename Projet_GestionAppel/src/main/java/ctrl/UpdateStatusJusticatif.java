package ctrl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TestHibernate;
import metier.Mail;

/**
 * Servlet implementation class UpdateStatusJusticatif
 */
@WebServlet("/UpdateStatusJusticatif")
public class UpdateStatusJusticatif extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] listeseance= (String[])request.getParameterValues("seance");
		
		Integer id = Integer.parseInt(request.getParameter("idU"));
		Integer codeJ = Integer.parseInt(request.getParameter("justi"));
		
		if(listeseance!=null) {
			for(String i : listeseance) {
				TestHibernate.updateJustifRefus(codeJ);
				TestHibernate.updateJustifica(id, Integer.parseInt(i), codeJ);
				Mail mail = new Mail();
				mail.sendMailRefusJustif("chartelain.david@gmail.com",id);
			}
			request.getRequestDispatcher("ScolJustificatifController").forward(request, response);
		}else {
			request.setAttribute("erreur", "Probleme a la validation ! Reessayez plus tard !");
			request.getRequestDispatcher("ScolJustificatifController").forward(request, response);
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
