package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import entityClass.User;

public class UserManager {

	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet results = null;
	
	DataSource ds;
	

	String driver = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://localhost/movies";
	
	
	public UserManager() {
	//	try {
	//		Context jndi = new InitialContext();
	//		ds = (DataSource) jndi.lookup("java:comp/env/jdbc/movies");
	//	} catch (NamingException e) {
	//		// TODO Auto-generated catch block
	//		e.printStackTrace();
	//	}
		
	}
	
	String createUserSql = "INSERT INTO USER (username, password, firstName, lastName, email, dateOfBirth) VALUES (?, ?, ?, ?, ?, ?);";
	String readAllUsersSql = "SELECT * FROM USER;";
	String readUser = "SELECT * FROM USER WHERE USERNAME=?;";
	String updateUserSql = "UPDATE USER SET PASSWORD=? WHERE USERNAME=?;";
	String deleteUserSql = "DELETE FROM USER WHERE USERNAME=?";

	
	public void createUser(User user) {
		try {
			//connection = ds.getConnection();	
			Class.forName(driver).newInstance();
			connection = DriverManager.getConnection(url, "root", "password");
		
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
		try {
			//connection = ds.getConnection();
			
			Class.forName(driver).newInstance();
			connection = DriverManager.getConnection(url, "root", "password");
		
			statement = connection.prepareStatement(readAllUsersSql);
			results = statement.executeQuery();
	
			while(results.next()) {
				User user = new User(results.getString("username"), results.getString("password"), results.getString("firstName"), 
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
		try {
			//connection = ds.getConnection();
			
			Class.forName(driver).newInstance();
			connection = DriverManager.getConnection(url, "root", "password");
		
			statement = connection.prepareStatement(readUser);
			statement.setString(1, username);
			
			results = statement.executeQuery();
			
			if(results.next()) {
				User user = new User(results.getString("username"), results.getString("password"), results.getString("firstName"), 
						results.getString("lastName"), results.getString("email"), results.getDate("dateOfBirth"));
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
			//connection = ds.getConnection();
			
			Class.forName(driver).newInstance();
			connection = DriverManager.getConnection(url, "root", "password");
		
			
			statement = connection.prepareStatement(updateUserSql);
			statement.setString(1, user.getPassword());
			statement.setString(2, username);
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
			//connection = ds.getConnection();
			
			Class.forName(driver).newInstance();
			connection = DriverManager.getConnection(url, "root", "password");
		
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
	
	public static void main(String[] args)
	{
		//Calendar cal = Calendar.getInstance();
		//Date dob = (Date) cal.getTime();
		Date dob = new Date(1988, 3, 20);
		User user = new User("contashish", "world-best", "ashish", "khanna", "khanna@ccis.neu.edu", dob);
		
		UserManager usermanager = new UserManager();
		//usermanager.createUser(user);
		usermanager.deleteUser("contashish");
		
		//System.out.print(users.getFirstName());
	}
	
}
