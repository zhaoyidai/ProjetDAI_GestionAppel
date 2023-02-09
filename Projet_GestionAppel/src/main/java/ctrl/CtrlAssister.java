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

import dao.ListAssister;
import dao.TestHibernate;
import metier.Users;

/**
 * Servlet implementation class CtrlAssister
 */
@WebServlet("/CtrlAssister")
public class CtrlAssister extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		0:presence, 1:retard, 2 absence
		List<ListAssister> listeas=new ArrayList<>();
		for(Users u:TestHibernate.listeAssister()) {
			ListAssister lu=new ListAssister(u.getId());
			listeas.add(lu);
		}
		String SeanceJsonString = new Gson().toJson(listeas);
		
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
