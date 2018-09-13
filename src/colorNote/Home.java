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
			System.out.println("Tenho o obj User");
			RequestDispatcher view = request.getRequestDispatcher("home.jsp");
			view.forward(request, response);
		} else {
			String username = request.getParameter("username");
			System.out.print("PEGUEI O USER PELO NOME: ");
			System.out.println(username);
			System.out.println(username != null);
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
		String user_id = request.getParameter("user_id");
		String note_id = request.getParameter("note_id");
		String action = request.getParameter("action");
		DAO dao = new DAO();
		Note nota = new Note();
		System.out.println(action);
		if ( action != null && action.equals("delete")) {
			System.out.println("deletando nota");
			nota.setNote_id(Integer.parseInt(note_id));
			nota.setUser_id(Integer.parseInt(user_id));
			dao.deleteNote(nota);
			request.setAttribute("user", dao.getUserById(Integer.parseInt(user_id)));
			doGet(request, response);
		} else if (action != null && action.equals("edit")) {
			System.out.println("editando nota");
			nota.setTitle(request.getParameter("mtitle"));
			nota.setBody(request.getParameter("mbody"));
			nota.setNote_id(Integer.parseInt(note_id));
			if (nota.getTitle().length() > 0 && nota.getBody().length() > 0) {
				dao.editNote(nota);
				request.setAttribute("user", dao.getUserById(Integer.parseInt(user_id)));
				doGet(request, response);
			} else {
				System.out.print("Nota Vazia");
			}
		} else if (user_id != null) {
			Note note = new Note();
			System.out.println("Entrei Pra add notas");
			note.setUser_id(Integer.parseInt(user_id));
			note.setTitle(request.getParameter("title"));
			note.setBody(request.getParameter("body"));
			System.out.println(note.getTitle());
			System.out.println(note.getBody());
			note.setTema_id(1);
			if (note.getTitle().length() > 0 && note.getBody().length() > 0) {
				dao.addNoteToUser(note);
			} else {
				System.out.print("Notas Vazias");
			}

			request.setAttribute("user", dao.getUserById(Integer.parseInt(user_id)));

			doGet(request, response);
		} else {
			System.out.println("entrando na home");
			doGet(request, response);
		}

	}

}
