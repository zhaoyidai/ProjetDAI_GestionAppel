package ctrl;

import javax.servlet.*;
import javax.servlet.http.*;

import dao.UsersDao;
import metier.Users;

import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(name="/LoginController" , value="/login")
public class LoginController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session!=null){
			session.invalidate();
		}
		response.sendRedirect("Login");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HashMap<String, String> erreurs = new HashMap<>();
//		String msg = request.getParameter("msg_info");
        
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		if (email == null || email.isEmpty()) {
			erreurs.put("email_error", "Veillez entrer l'addresse email");
		}
		if (password == null || password.isEmpty()) {
			erreurs.put("password_error", "Veillez entrer un mot de passe");
		}

		if (erreurs.isEmpty()) {
			try {
				
				Users usersId = UsersDao.loginUtilisateur(email, password);
				if (usersId == null) {
					request.setAttribute("generale_error", "Email ou mot de passe incorrect ! Veuillez réessayer !");
					request.getRequestDispatcher("Login").forward(request, response);
				} else {
//					request.setAttribute("msg_info", "msg");
					request.getSession().setAttribute("auth", usersId);
					request.getSession().setAttribute("id", usersId.getId());
					request.getSession().setAttribute("email", usersId.getEmail());
					request.getSession().setAttribute("nom", usersId.getNom());
					request.getSession().setAttribute("prenom", usersId.getPrenom());
					request.getSession().setAttribute("statut", usersId.getStatut());
					response.sendRedirect("Accueil");
				}
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("generale_error", "Probleme technique ! Veuillez contacter l'administrateur.");
				request.getRequestDispatcher("Login").forward(request, response);
			}

		} else {
			erreurs.forEach(request::setAttribute);
			request.getRequestDispatcher("Login").forward(request, response);
		}
	}

}
