package ctrl;

import javax.servlet.*;
import javax.servlet.http.*;

import dao.UsersDao;
import metier.Users;

import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(name = "ConnexionController", value = "/connexion")
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/view/connexion.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HashMap<String, String> erreurs = new HashMap<>();

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
                UsersDao usersDao = new UsersDao();
                Integer usersId = usersDao.loginUtilisateur(email, password);
                if (usersId == null) {
                    request.setAttribute("generale_error", "Email ou mot de passe incorrect");
                    request.getRequestDispatcher("/view/login.jsp").forward(request, response);
                } else {
                    request.getSession().setAttribute("auth", usersId);
                    response.sendRedirect("/compte");
                }
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("generale_error", "Probleme technique ! Veuillez contacter l'administrateur.");
                request.getRequestDispatcher("/view/login.jsp").forward(request, response);
            }

        } else {
            erreurs.forEach(request::setAttribute);
            request.getRequestDispatcher("/view/login.jsp").forward(request, response);
        }
    }

}
