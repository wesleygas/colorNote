package colorNote;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Home
 */
@WebServlet("/Home")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
	long session_timeout = 2 * 60 * 1000;

	public Home() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DAO dao = new DAO();
		User user = (User) request.getAttribute("user");
		if (user != null) {
			List<Note> notas = dao.getNotesFromUser(user);
			request.setAttribute("notas", notas);
			RequestDispatcher view = request.getRequestDispatcher("home.jsp");
			view.forward(request, response);
		} else {
			String username = request.getParameter("username");
			if (username != null) {
				user = dao.getUserByName(request.getParameter("username"));
				Timestamp timestamp = user.getLast_session();
				long last_session = timestamp.getTime();
				if (last_session + session_timeout > System.currentTimeMillis()) {
					List<Note> notas = dao.getNotesFromUser(user);
					request.setAttribute("notas", notas);
					request.setAttribute("user", user);
					RequestDispatcher view = request.getRequestDispatcher("home.jsp");
					view.forward(request, response);

				} else {
					System.out.println("TIMEOUT -------------------------------------");
					request.setAttribute("error", "Sua sessao expirou, logue novamente.");
					RequestDispatcher view = request.getRequestDispatcher("/Login");
					view.forward(request, response);

				}
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DAO dao = new DAO();
		request.getAttribute("user");
		
		

		doGet(request, response);
	}

}
