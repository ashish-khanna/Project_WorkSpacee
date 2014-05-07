package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import entityClass.Comment;
import entityClass.User;

public class UserManager {

	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet results = null;
	
	DataSource ds;
	
	
	public UserManager() {
		try {
			Context jndi = new InitialContext();
			ds = (DataSource) jndi.lookup("java:comp/env/jdbc/movies");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	String createUserSql = "INSERT INTO USER (username, password, firstName, lastName, email, dateOfBirth) VALUES (?, ?, ?, ?, ?, ?);";
	String readAllUsersSql = "SELECT * FROM USER;";
	String readUser = "SELECT * FROM USER WHERE USERNAME=?;";
	String updateUserSql = "UPDATE USER SET USERNAME=?, PASSWORD=?, FIRSTNAME=?, LASTNAME=?, EMAIL=?, DATEOFBIRTH=? WHERE USERNAME=?;";
	String deleteUserSql = "DELETE FROM USER WHERE USERNAME=?";
	String pkidcommentSql = "SELECT ID FROM COMMENT WHERE fkUser = ?";
	
	public void createUser(User user) {
		try {
			connection = ds.getConnection();	
		
			statement = connection.prepareStatement(createUserSql);
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getFirstName());
			statement.setString(4, user.getLastName());
			statement.setString(5, user.getEmail());
			statement.setDate(6, (java.sql.Date) user.getDateOfBirth());
			statement.executeUpdate();
			

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e){}
		finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public List<User> readAllUsers() {
		List<User> users = new ArrayList<User>();
		UserManager usermanager = new UserManager();
		try {
			connection = ds.getConnection();
		
			statement = connection.prepareStatement(readAllUsersSql);
			results = statement.executeQuery();
	
			while(results.next()) {
				User user = new User(usermanager, results.getString("username"), results.getString("password"), results.getString("firstName"), 
						results.getString("lastName"), results.getString("email"), results.getDate("dateOfBirth"));
				
				users.add(user);
			}
		
		}catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {}
		finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return users;
	}
	
	
	public User readUser(String username) {
		UserManager usermanager = new UserManager();
		try {
			connection = ds.getConnection();
		
			statement = connection.prepareStatement(readUser);
			statement.setString(1, username);
			
			results = statement.executeQuery();
			
			if(results.next()) {
				User user = new User(usermanager, results.getString("username"), results.getString("password"), results.getString("firstName"), 
						results.getString("lastName"), results.getString("email"), results.getDate("dateOfBirth"));
				
				user.setId(results.getInt("id"));
				
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {}
		finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public void updateUser(String username, User user) {
		try {
			connection = ds.getConnection();
			
			
			statement = connection.prepareStatement(updateUserSql);
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getFirstName());
			statement.setString(4, user.getLastName());
			statement.setString(5, user.getEmail());
			statement.setDate(6, (java.sql.Date) user.getDateOfBirth());
			statement.setString(7, username);
			
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {}
		finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public void deleteUser(String username) {
		try {
			connection = ds.getConnection();
			
			statement = connection.prepareStatement(deleteUserSql);
			statement.setString(1, username);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {}
 		finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}


	public List<Comment> getComments(Integer id) {
		List<Comment> comments = new ArrayList<Comment>();
		
		try {
			connection = ds.getConnection();
			
			statement = connection.prepareStatement(pkidcommentSql);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			
			while(rs.next())
			{
				int cid = rs.getInt("id");
				Comment cm = new Comment();
				cm.setId(cid);
								
				comments.add(cm);
			}
			return comments;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {}
 		finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	
}
