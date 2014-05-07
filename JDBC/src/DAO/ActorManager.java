package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import entityClass.*;

public class ActorManager {


	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet results = null;
	
	DataSource ds;
	
	public ActorManager(){
		try {
			Context jndi = new InitialContext();
			ds = (DataSource) jndi.lookup("java:comp/env/jdbc/movies");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	String createActorSql = "INSERT INTO ACTOR (ACTORID, FIRSTNAME, lastName, dateOfBirth) VALUES (?, ?, ?, ?);";
	String readAllActorsSql = "SELECT * FROM ACTOR;";
	String readActor = "SELECT * FROM ACTOR WHERE ACTORID=?;";
	String updateActorSql = "UPDATE ACTOR SET ACTORID=?, FIRSTNAME=?, LASTNAME=?, DATEOFBIRTH=? WHERE ACTORID=?;";
	String deleteActorSql = "DELETE FROM ACTOR WHERE ACTORID=?";
	String pkidcastSql = "SELECT ID FROM CAST WHERE fkActor = ?";
	

	public void createActor(Actor actor) {
		try {
			connection = ds.getConnection();	
		
			statement = connection.prepareStatement(createActorSql);
			statement.setString(1, actor.getActorId());
			statement.setString(2, actor.getFirstName());
			statement.setString(3, actor.getLastName());
			statement.setDate(4, (java.sql.Date) actor.getDateOfBirth());
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

	public List<Actor> readAllActor() {
		List<Actor> actors = new ArrayList<Actor>();
		ActorManager actormanager = new ActorManager();
		try {
			connection = ds.getConnection();
		
			statement = connection.prepareStatement(readAllActorsSql);
			results = statement.executeQuery();
	
			while(results.next()) {
				Actor actor = new Actor(actormanager, results.getString("actorId"), results.getString("firstName"), 
						results.getString("lastName"), results.getDate("dateOfBirth"));
				
				actors.add(actor);
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
		return actors;
	}
	
	public Actor readActor(String actorId) {
		ActorManager actormanager = new ActorManager();
		try {
			connection = ds.getConnection();
		
			statement = connection.prepareStatement(readActor);
			statement.setString(1, actorId);
			
			results = statement.executeQuery();
			
			if(results.next()) {
				Actor actor = new Actor(actormanager, results.getString("actorId"),results.getString("firstName"), 
						results.getString("lastName"), results.getDate("dateOfBirth"));
				
				actor.setId(results.getInt("id"));
				
				return actor;
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
	
	public void updateActor(String actorid, Actor actor) {
		try {
			connection = ds.getConnection();
						
			statement = connection.prepareStatement(updateActorSql);
			statement.setString(1, actor.getActorId());
			statement.setString(2, actor.getFirstName());
			statement.setString(3, actor.getLastName());
			statement.setDate(4, (java.sql.Date) actor.getDateOfBirth());
			statement.setString(5, actorid);
			
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
	
	public void deleteActor(String actorId) {
		try {
			connection = ds.getConnection();
			
			statement = connection.prepareStatement(deleteActorSql);
			statement.setString(1, actorId);
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

	
	public List<Cast> getCasts(Integer id) {
		List<Cast> casts = new ArrayList<Cast>();
		
		try {
			connection = ds.getConnection();
			
			statement = connection.prepareStatement(pkidcastSql);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			
			while(rs.next())
			{
				int cid = rs.getInt("id");
				Cast cm = new Cast();
				cm.setId(cid);
								
				casts.add(cm);
			}
			return casts;
			
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
