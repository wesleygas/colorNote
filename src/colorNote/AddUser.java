package colorNote;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/AddUser")
@SuppressWarnings("serial")
public class AddUser extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DAO dao = new DAO();
		
		User user = new User();
		user.setUsername(request.getParameter("username"));
		user.setUsername(request.getParameter("password"));
		
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("adicionado" + user.getUsername());
		out.println("<br><p>Com a senha:" + user.getSenha() + "</p>");
		out.println(getServletName());
		out.println("</body></html>");
		dao.close();
		
		response.sendRedirect("./Login");
	}
}
