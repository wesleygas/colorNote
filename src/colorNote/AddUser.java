package colorNote;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig(fileSizeThreshold = 1024 * 1024,
maxFileSize = 1024 * 1024 * 5, 
maxRequestSize = 1024 * 1024 * 5 * 5)

@WebServlet("/AddUser")
@SuppressWarnings("serial")
public class AddUser extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		RequestDispatcher view = request.getRequestDispatcher("signUp.html");
		view.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DAO dao = new DAO();
		
		User user = new User();
		
		user.setUsername(request.getParameter("username"));
		user.setUsername(request.getParameter("password"));
		//System.out.println(request.getParameter("foto"));
		
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
