package ctrl;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TestHibernate;
import metier.Users;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/ctrlListeAbsence")
public class ctrlListeAbsence extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      List<Users> users=TestHibernate.listeAssister();
      request.setAttribute("listeEtudiant", users);
      request.getRequestDispatcher("listeAbsenceEtudiant").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }


}
