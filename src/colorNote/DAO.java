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
			connection = DriverManager.getConnection("jdbc:mysql://localhost/color_note?", "root", "root");
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
	

	public List<Note> getNotesFromUser(User user) {
		List<Note> notas = new ArrayList<Note>();
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement("SELECT * FROM tb_note WHERE user_id =?");
			stmt.setInt(1, user.getUser_id());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Note note = new Note();
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
			System.out.println("DAO: ERRO AO PEGAR NOTAS DO USUARIO" + user.getUser_id().toString() +  "DO BANCO DE DADOS");
		}
		return notas;
	}
	
	
	public List<Note> getAllNotes() {
		List<Note> notas = new ArrayList<Note>();
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement("SELECT * FROM tb_note");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Note note = new Note();
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
	
	public User getUserById(Integer userId) {
		User user = new User();
		int n_users = 0;
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement("SELECT * FROM tb_user WHERE user_id=? and is_active=1");
			stmt.setInt(1, userId);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
			n_users += 1;
			user.setUser_id(rs.getInt("user_id"));
			user.setUsername(rs.getString("username"));
			user.setSenha(rs.getString("senha"));
			user.setFoto(rs.getBytes("foto"));
			user.setLast_session(rs.getTimestamp("last_session"));
			user.setIs_active(rs.getBoolean("is_active"));
			}
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println("DAO: ERRO AO PEGAR USER DO BANCO DE DADOS");
		}
		if(n_users > 1) {
			System.out.println("MAIS QUE UM USUARIO COM O MESMO ID!");
			return user;
		}
		return user;
	}
	
	public int addUser(User user) {
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement("SELECT * FROM tb_user WHERE username=?");
			stmt.setString(1, user.getUsername());
			ResultSet rs = stmt.executeQuery();
			if(!rs.isBeforeFirst()) {
				String sql = "INSERT INTO tb_user (username,senha,foto,last_session,is_active) values(?,?,?,?,?)";
				stmt = connection.prepareStatement(sql);
				stmt.setString(1, user.getUsername());
				stmt.setString(2, user.getSenha());
				stmt.setBytes(3, user.getFoto());
				stmt.setTimestamp(4, user.getLast_session());
				stmt.setBoolean(5, user.isIs_active());
				stmt.execute();
				stmt.close();
				
				return 1;	
			}
			else {
				return 2;
			}
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println("DAO: ERRO AO ADICIONAR USER NO BANCO DE DADOS VIA USERNAME");
		}
		
		return 0;
	}
	
	public User getUserByName(String username) {
		User user = new User();
		int n_users = 0;
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement("SELECT * FROM tb_user WHERE username=? and is_active=1");
			stmt.setString(1,username);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				n_users += 1;
				user.setUser_id(rs.getInt("user_id"));
				user.setUsername(rs.getString("username"));
				user.setSenha(rs.getString("senha"));
				user.setFoto(rs.getBytes("foto"));
				user.setLast_session(rs.getTimestamp("last_session"));
				user.setIs_active(rs.getBoolean("is_active"));
			}
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println("DAO: ERRO AO PEGAR USER DO BANCO DE DADOS");
		}
		if(n_users > 1) {
			System.out.println("MAIS QUE UM USUARIO COM O MESMO username!");
			return user;
		}
		return user;
		
	}
	
	
	public void editUserPassword(String password, User user) {
		String sql = "UPDATE tb_user SET password=? WHERE user_id=?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, password);
			stmt.setInt(2, user.getUser_id());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("DAO: ERRO AO EDITAR USER PASSWORD");
		}
	}
	
	public void editUserLastSession(User user) {
		String sql = "UPDATE tb_user SET last_session=? WHERE user_id=?";
		long time = System.currentTimeMillis();
		java.sql.Timestamp timestamp = new java.sql.Timestamp(time);
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setTimestamp(1, timestamp);
			stmt.setInt(2, user.getUser_id());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("DAO: ERRO AO MODIFICAR LAST_SESSION TIMESTAMP");
		}
	}
	
	public void deactivateUser(User user) {
		String sql = "UPDATE tb_user SET is_active=? WHERE user_id=?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setBoolean(1, false);
			stmt.setInt(2, user.getUser_id());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("DAO: ERRO AO DESATIVAR USUARIO");
		}
	}
	
	public void editNote(Note note) {
		
	}
	
	
	
	

}
