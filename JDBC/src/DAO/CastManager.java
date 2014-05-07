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

public class CastManager {
	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet results = null;
	
	DataSource ds;
	
	public CastManager(){
		try {
			Context jndi = new InitialContext();
			ds = (DataSource) jndi.lookup("java:comp/env/jdbc/movies");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	String createCastSql = "INSERT INTO CAST (CHARACTERNAME, FKACTOR, FKMOVIE) VALUES (?, ?, ?);";
	String readAllCastSql = "SELECT * FROM CAST;";
	String readAllCastForActorSql = "SELECT * FROM CAST WHERE FKACTOR =?;";
	String readAllCastForMovieSql = "SELECT * FROM CAST WHERE FKMOVIE =?;";
	String readCastForIdSql = "SELECT * FROM CAST WHERE ID =?;";
	String updateCastSql = "UPDATE CAST SET CHARACTERNAME = ?, FKMOVIE=?, FKACTOR=? WHERE id = ?";
	String deleteCastSql = "DELETE FROM CAST WHERE id = ?";

	
	public void createCast(Cast newCast) {
		try {
			connection = ds.getConnection();	
		
			statement = connection.prepareStatement(createCastSql);
			statement.setString(1, newCast.getCharacterName());
			statement.setInt(2, newCast.getActor().getId());
			statement.setInt(3, newCast.getMovie().getId());
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
	
	

	public List<Cast> readAllCast() {
		List<Cast> casts = new ArrayList<Cast>();
		CastManager castmanager = new CastManager();
		try {
			connection = ds.getConnection();
		
			statement = connection.prepareStatement(readAllCastSql);
			results = statement.executeQuery();
	
			while(results.next()) {
				Cast cast = new Cast(castmanager, results.getString("charactername"), 
						results.getInt("fkmovie"), results.getInt("fkuser"));
				
				casts.add(cast);
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
		return casts;
	}

	public List<Cast>  readAllCastForActor(String actorId) {
		List<Cast> casts = new ArrayList<Cast>();
		CastManager castmanager = new CastManager();
		ActorManager actormanager = new ActorManager();
		Actor actor = new Actor();
		try {
			connection = ds.getConnection();
		
			actor = actormanager.readActor(actorId);
			
			statement = connection.prepareStatement(readAllCastForActorSql);
			statement.setInt(1, actor.getId());
			
			results = statement.executeQuery();
	
			while(results.next()) {
				Cast cast = new Cast(castmanager, results.getString("characterName"), 
						results.getInt("fkmovie"), results.getInt("fkuser"));
				
				casts.add(cast);
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
		return casts;
	}

	
	public List<Cast>  readAllCastForMovie(String movieId) {
		List<Cast> casts = new ArrayList<Cast>();
		CastManager castmanager = new CastManager();
		MovieManager moviemanager = new MovieManager();
		Movie movie = new Movie();
		try {
			connection = ds.getConnection();
		
			movie = moviemanager.readMovie(movieId);
			
			statement = connection.prepareStatement(readAllCastForActorSql);
			statement.setInt(1, movie.getId());
			
			results = statement.executeQuery();
	
			while(results.next()) {
				Cast cast = new Cast(castmanager, results.getString("characterName"), 
						results.getInt("fkmovie"), results.getInt("fkuser"));
				
				casts.add(cast);
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
		return casts;
	}
	
	public Cast readCastForId(Integer castId){
		try {
			CastManager castmanager = new CastManager();
			connection = ds.getConnection();
		
			statement = connection.prepareStatement(readCastForIdSql);
			statement.setInt(1, castId);
			
			results = statement.executeQuery();
	
			if(results.next()) {
				Cast cast = new Cast(castmanager, results.getString("characterName"), 
						results.getInt("fkmovie"), results.getInt("fkuser"));
				
				return cast;
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
		return null;
	}
	

	public void updateCast(Integer castId, Cast newCast) {
		try {
			connection = ds.getConnection();
					
			statement = connection.prepareStatement(updateCastSql);
			statement.setString(1, newCast.getCharacterName());
			statement.setInt(2, newCast.getMovie().getId());
			statement.setInt(3, newCast.getActor().getId());
			statement.setInt(4, castId);
			
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
	
	public void deleteCast(Integer castId) {
		try {
			connection = ds.getConnection();
			
			statement = connection.prepareStatement(deleteCastSql);
			statement.setInt(1, castId);
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
	
}
