package metier;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.descriptor.web.WebXml;

import dao.UsersDao;

/**
 * Servlet Filter implementation class Filter
 */
@WebFilter("/Filter")
public class Filter extends HttpFilter implements javax.servlet.Filter {

	public void destroy() {
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		 Integer userID = (Integer) ((HttpServletRequest) request).getSession().getAttribute("auth");
	        if (userID == null) {
	            ((HttpServletResponse) response).sendRedirect("/connexion");
	        } else {
	            UsersDao usersDao = new UsersDao();
	            Users users = usersDao.find(userID);
	            request.setAttribute("utilisateur", users);
	            chain.doFilter(request, response);
	        }

			 }
			 


	public void init(FilterConfig fConfig) throws ServletException {
	}

}
