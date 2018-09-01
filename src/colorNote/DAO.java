package colorNote;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAO {
	private Connection connection = null;

	public DAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/meus_dados", "root", "root");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println(" ====== DAO: ERRO AO CONECTAR COM O MYSQL ==============");
		}
	}
	
	public void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(" ====== DAO: ERRO AO FECHAR A CONEXAO COM O MYSQL ==============");
		}
	}

	public List<Notes> getNotesFromUser(Integer USER_ID) {
		List<Notes> notas = new ArrayList<Notes>();
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement("SELECT * FROM tb_note WHERE user_id =" + USER_ID.toString() + ";");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Notes note = new Notes();
				note.setNote_id(rs.getInt("note_id"));
				note.setTitle(rs.getString("title"));
				note.setBody(rs.getString("body"));
				note.setImage(rs.getBytes("image"));
				note.setLast_edit(rs.getTimestamp("last_edit"));
				note.setUser_id(rs.getInt("user_id"));
				note.setTema_id(rs.getInt("tema_id"));
				notas.add(note);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("DAO: ERRO AO PEGAR NOTAS DO USUARIO" + USER_ID.toString() +  "DO BANCO DE DADOS");
		}
		return notas;
	}
	
	public List<Notes> getAllNotes() {
		List<Notes> notas = new ArrayList<Notes>();
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement("SELECT * FROM tb_note WHERE");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Notes note = new Notes();
				note.setNote_id(rs.getInt("note_id"));
				note.setTitle(rs.getString("title"));
				note.setBody(rs.getString("body"));
				note.setImage(rs.getBytes("image"));
				note.setLast_edit(rs.getTimestamp("last_edit"));
				note.setUser_id(rs.getInt("user_id"));
				note.setTema_id(rs.getInt("tema_id"));
				notas.add(note);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("DAO: ERRO AO PEGAR NOTAS DO BANCO DE DADOS");
		}
		return notas;
	}
	
	public User getUser() {
		User user = new User();
		
		return user;
	}
	
	
	
	

}
