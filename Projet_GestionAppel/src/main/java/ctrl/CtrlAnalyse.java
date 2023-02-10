package ctrl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.CoursSeances;
import dao.TestHibernate;
import metier.Cours;

/**
 * Servlet implementation class CtrlAnalyse
 */
@WebServlet("/CtrlAnalyse")
public class CtrlAnalyse extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idu = request.getParameter("idu");
		int id=Integer.valueOf(idu);
		List<CoursSeances> allcours=new ArrayList<>();
		for(Cours c:TestHibernate.getEtuCours(id)) {
			CoursSeances cs=new CoursSeances(c.getIdC(),c.getNomC());
			allcours.add(cs);
		}
		String SeanceJsonString = new Gson().toJson(allcours);
		
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print(SeanceJsonString);
		out.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
