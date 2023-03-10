package ctrl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.TestHibernate;
import metier.Seance;
import metier.Users;

import java.util.GregorianCalendar;


/**
 * Servlet implementation class CtrlRedirect
 */
@WebServlet("/CtrlRedirect")
public class CtrlRedirect extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("type_action");

		
		switch(action) {
		case "planning":
			
			int id=((Users)request.getSession().getAttribute("auth")).getId();
//			List<Seance> seances=TestHibernate.loadSeance(id);
//			
//			request.setAttribute("seances", seances);
//			request.getRequestDispatcher("Planning").forward(request, response);
			List<String> weeksaffichage=new ArrayList<>();
			List<Integer> weeks=new ArrayList<>();
			for(Seance s:TestHibernate.loadSeance(id)) {
				int week=s.datetest(s.getDateSeance());
				if(!weeks.contains(week)){
					weeks.add(week);
					weeksaffichage.add("semaine "+week+" "+s.dateofweek(week));
				}
			}
			request.setAttribute("semaines", weeks);
			request.setAttribute("semainesaff", weeksaffichage);
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
