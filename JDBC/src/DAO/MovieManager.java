package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import entityClass.Cast;
import entityClass.Comment;
import entityClass.*;

public class MovieManager {

	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet results = null;
	
	DataSource ds;
	
	public MovieManager(){
		try {
			Context jndi = new InitialContext();
			ds = (DataSource) jndi.lookup("java:comp/env/jdbc/movies");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	String createMovieSql = "INSERT INTO MOVIE (MOVIEID, TITLE, POSTERIMAGE, RELEASEDATE) VALUES (?, ?, ?, ?);";
	String readAllMoviesSql = "SELECT * FROM MOVIE;";
	String readMovie = "SELECT * FROM MOVIE WHERE MOVIEID=?;";
	String updateMovieSql = "UPDATE MOVIE SET MOVIEID=?, TITLE=?, POSTERIMAGE=?, RELEASEDATE=? WHERE MOVIEID=?;";
	String deleteMovieSql = "DELETE FROM MOVIE WHERE movieId=?";
	String pkidcommentSql = "SELECT ID FROM COMMENT WHERE fkMovie = ?";
	String pkidcastSql = "SELECT ID FROM CAST WHERE fkMovie = ?";
	
	public void createMovie(Movie movie) {
		try {
			connection = ds.getConnection();	
		
			statement = connection.prepareStatement(createMovieSql);
			statement.setString(1, movie.getMovieId());
			statement.setString(2, movie.getTitle());
			statement.setString(3, movie.getPosterImage());
			statement.setDate(4, (Date) movie.getReleaseDate());
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
	

	public List<Movie> readAllMovies() {
		List<Movie> movies = new ArrayList<Movie>();
		MovieManager moviemanager = new MovieManager();
		try {
			connection = ds.getConnection();
		
			statement = connection.prepareStatement(readAllMoviesSql);
			results = statement.executeQuery();
	
			while(results.next()) {
				Movie movie = new Movie(moviemanager, results.getString("movieId"), results.getString("title"), results.getString("posterImage"),
						results.getDate("releaseDate"));
				
				movies.add(movie);
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
		return movies;
	}
	
	public Movie readMovie(String movieId) {
		MovieManager moviemanager = new MovieManager();
		try {
			connection = ds.getConnection();
		
			statement = connection.prepareStatement(readMovie);
			statement.setString(1, movieId);
			
			results = statement.executeQuery();
			
			if(results.next()) {
				Movie movie = new Movie(moviemanager, results.getString("movieId"), results.getString("title"), results.getString("posterImage"), 
						results.getDate("releaseDate"));
				movie.setId(results.getInt("id"));
				
				return movie;
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
		
	
	public void updateMovie(String movieId, Movie movie) {
		try {
			connection = ds.getConnection();
						
			statement = connection.prepareStatement(updateMovieSql);
			statement.setString(1, movie.getMovieId());
			statement.setString(2, movie.getTitle());
			statement.setString(3, movie.getPosterImage());
			statement.setDate(4, (Date) movie.getReleaseDate());
			statement.setString(5, movieId);
			
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
	
	public void deleteMovie(String movieId) {
		try {
			connection = ds.getConnection();
			
			statement = connection.prepareStatement(deleteMovieSql);
			statement.setString(1, movieId);
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
