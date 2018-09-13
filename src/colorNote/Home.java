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
		if(user_id != null) {
			DAO dao = new DAO();
		Note note = new Note();
		System.out.println("Entrei Pra add notas");
		note.setUser_id(Integer.parseInt(user_id));
		note.setTitle(request.getParameter("title"));
		note.setBody(request.getParameter("body"));
		System.out.println(note.getTitle());
		System.out.println(note.getBody());
		note.setTema_id(1);
		dao.addNoteToUser(note);
		doGet(request, response);
		}
		else {
			doGet(request, response);
		}
		
	}
	
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String note_id = request.getParameter("note_id");
		String user_id = request.getParameter("user_id");
		DAO dao = new DAO();
		Note note = new Note();
		note.setNote_id(Integer.parseInt(note_id));
		note.setUser_id(Integer.parseInt(user_id));
		dao.deleteNote(note);
		request.setAttribute("user", dao.getUserById(Integer.parseInt(user_id)));
		RequestDispatcher view = request.getRequestDispatcher("home.jsp");
		System.out.println("AAAAA" + user_id);
		view.forward(request, response);
		
		
	}
		
		
	
		
		
}


