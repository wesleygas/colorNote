package colorNote;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		user.setSenha(request.getParameter("password"));
		user.setIs_active(true);
		//System.out.println(request.getParameter("foto"));
		
		int result = dao.addUser(user);
		if(result == 1) {
			System.out.println("USUARIO ADICIONADO COM SUCESSO");
		}else if(result == 2){
			System.out.println("USUARIO JA CADASTRADO");
			//TODO: RETURN ERROR TO LOGIN PAGE
		}
		else {
			System.out.println("#SOMETHING IS WROOOONG (ADDING USER RETURNED WITH AN ERROR");
		}
		
		RequestDispatcher view = request.getRequestDispatcher("login.html");
		view.forward(request, response);
		dao.close();
		
		//response.sendRedirect("./Login");
	}
}
