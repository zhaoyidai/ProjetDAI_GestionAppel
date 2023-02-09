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

import dao.SeancetoJson;
import dao.TestHibernate;
import metier.Seance;
import metier.Users;

/**
 * Servlet implementation class CtrlPlanningJson
 */
@WebServlet("/CtrlPlanningJson")
public class CtrlPlanningJson extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String week = request.getParameter("week");
		int weekint=Integer.valueOf(week);
		int id=((Users)request.getSession().getAttribute("auth")).getId();
		List<Seance> s=TestHibernate.loadSeance(id);
		List<SeancetoJson> sjliste=new ArrayList<>();
		String str="CtrlAccederFicheAppel?idSeance=";
		for(Seance se:s) {
			String lien=str+se.getIdS();
			if(se.datetest(se.getDateSeance())==weekint) {
				SeancetoJson sj=new SeancetoJson(se.getIdS(),se.getCoursSeance().getNomC(),se.getHeureDebut().toString(),se.dateFin(se.getHeureDebut(),se.getDureeS()) ,lien,se.datetest(se.getDateSeance()),se.getDateSeance().toString(),se.jour(se.getDateSeance()));
			
				sjliste.add(sj);
			}
			
		}
		String SeanceJsonString = new Gson().toJson(sjliste);
		SeanceJsonString=SeanceJsonString;
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
