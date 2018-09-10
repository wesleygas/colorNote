package colorNote;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DAO dao = new DAO();
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher view = request.getRequestDispatcher("login.html");
		view.forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = dao.getUserByName(request.getParameter("username"));
		PrintWriter out = response.getWriter();
		if(user.getUsername() == null){
			out.println("<html><body>");
			out.println("<img src=\"https://www.speakgif.com/wp-content/uploads/2015/12/confused-travolta-original-pulp-fiction-animated-gif.gif\">");
			out.println("<p>User not found</p>");
			out.println("</body></html>");
		}else {
			out.println("<html><body>");
			out.println("<p>Encontrei um carinha aqui</p>");
			out.println("<h2>Ele se chama: " + user.getUsername() + "</h2>");
			out.println("A senha dele eh: " + user.getSenha());
			out.println(" e voce deu: " + request.getParameter("password").toString());
			if (user.getSenha().equals(request.getParameter("password")) && user.getSenha() != null){
				//response.sendRedirect("./Home");
				dao.editUserLastSession(user);
				out.println("A senha ta serta!<p>");
				System.out.println("INDO PRA HOME");
				request.setAttribute("user", user);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/Home");
				dispatcher.forward(request, response);
				}
			else {
				System.out.println("RETORNANDO PARA O LOGIN");
				request.setAttribute("error", "Senha inválida!");
				RequestDispatcher view = request.getRequestDispatcher("login.html");
				view.forward(request, response);
			}
			out.println("</body></html>");
		}

		
		
	}
}
