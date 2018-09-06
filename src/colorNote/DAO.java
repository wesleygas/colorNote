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
			connection = DriverManager.getConnection("jdbc:mysql://localhost/color_note", "root", "root");
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
	

	public List<Notes> getNotesFromUser(Integer user_id) {
		List<Notes> notas = new ArrayList<Notes>();
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement("SELECT * FROM tb_note WHERE user_id =?");
			stmt.setInt(1, user_id);
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
			System.out.println("DAO: ERRO AO PEGAR NOTAS DO USUARIO" + user_id.toString() +  "DO BANCO DE DADOS");
		}
		return notas;
	}
	
	
	public List<Notes> getAllNotes(Integer userId) {
		List<Notes> notas = new ArrayList<Notes>();
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement("SELECT * FROM tb_note");
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
	
	public User getUser(Integer userId) {
		User user = new User();
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement("SELECT * FROM tb_user WHERE user_id=?");
			stmt.setInt(1, userId);
			ResultSet rs = stmt.executeQuery();
			user.setUser_id(rs.getInt("user_id"));
			user.setUsername(rs.getString("username"));
			user.setSenha(rs.getString("senha"));
			user.setFoto(rs.getBytes("foto"));
			user.setLast_session(rs.getTimestamp("last_session"));
			user.setIs_active(rs.getBoolean("is_active"));
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println("DAO: ERRO AO PEGAR USER DO BANCO DE DADOS");
		}
		
		return user;
	}
	
	public void addUser(User user) {
		String sql = "INSERT INTO tb_user (username,senha,foto,last_session,is_active) values(?,?,?,?,?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getSenha());
			stmt.setBytes(3, user.getFoto());
			stmt.setTimestamp(4, user.getLast_session());
			stmt.setBoolean(5, user.isIs_active());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("DAO: ERRO AO ADICIONAR USER NA TABELA");
		}
	}
	
	
	public void editUserPassword(String password, Integer user_id) {
		String sql = "UPDATE tb_user SET password=? WHERE id=?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, password);
			stmt.setInt(2, user_id);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("DAO: ERRO AO ADICIONAR USER NA TABELA");
		}
	}
	
	
	
	

}
