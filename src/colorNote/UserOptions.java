package colorNote;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserOptions
 */
@WebServlet("/UserOptions")
public class UserOptions extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		DAO dao = new DAO();
		String user_id = request.getParameter("user_id");
		if (user_id != null) {
			User user = dao.getUserById(Integer.parseInt(user_id));
			request.setAttribute("user", user);
			RequestDispatcher view = request.getRequestDispatcher("userOptions.jsp");
			view.forward(request, response);
		} else {
			RequestDispatcher view = request.getRequestDispatcher("/Login");
			view.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		String user_id = request.getParameter("user_id");
		DAO dao = new DAO();
		User user = dao.getUserById(Integer.parseInt(user_id));
		System.out.println(action);
		System.out.println(user_id);
		if (user.isSessionActive() == false) {
			System.out.println("Sessao expirada");
			RequestDispatcher view = request.getRequestDispatcher("login.html");
			view.forward(request, response);
		} else {
			if (action.equals("delete")) {
				System.out.println("Deletando");
				dao.deactivateUser(user);
			} else if (action.equals("change_password")) {
				String new_password = request.getParameter("new_password");
				System.out.println("Mudando a senha para: " + new_password);
				dao.changeUserPassword(user, new_password);
			}
			RequestDispatcher view = request.getRequestDispatcher("login.html");
			view.forward(request, response);
		}
	}

}
