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

import entityClass.Comment;
import entityClass.Movie;
import entityClass.User;

public class CommentManager {

	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet results = null;
	
	DataSource ds;
	
	public CommentManager(){
		try {
			Context jndi = new InitialContext();
			ds = (DataSource) jndi.lookup("java:comp/env/jdbc/movies");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	String createCommentSql = "INSERT INTO COMMENT (COMMENT, DATE, FKUSER, FKMOVIE) VALUES (?, ?, ?, ?);";
	String readAllCommentSql = "SELECT * FROM COMMENT;";
	String readAllCommentsForUsernameSql = "SELECT * FROM COMMENT WHERE FKUSER =?;";
	String readAllCommentsForMovieSql = "SELECT * FROM COMMENT WHERE FKMOVIE =?;";
	String readCommentForIdSql = "SELECT * FROM COMMENT WHERE ID =?;";
	String updateCommentSql = "UPDATE COMMENT SET COMMENT = ? WHERE id = ?";
	String deleteCommentSql = "DELETE FROM COMMENT WHERE id = ?";
	
	public void createComment(Comment comment) {
		try {
			connection = ds.getConnection();	
		
			statement = connection.prepareStatement(createCommentSql);
			statement.setString(1, comment.getComment());
			statement.setDate(2, (Date) comment.getDate());
			statement.setInt(3, comment.getUser().getId());
			statement.setInt(4, comment.getMovie().getId());
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
	
	public List<Comment> readAllComments() {
		List<Comment> comments = new ArrayList<Comment>();
		CommentManager commentmanager = new CommentManager();
		try {
			connection = ds.getConnection();
		
			statement = connection.prepareStatement(readAllCommentSql);
			results = statement.executeQuery();
	
			while(results.next()) {
				Comment comment = new Comment(commentmanager, results.getString("comment"), results.getDate("date"),
						results.getInt("fkuser"), results.getInt("fkmovie"));
				
				comments.add(comment);
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
		return comments;
	}

	
	public List<Comment>  readAllCommentsForUsername(String username) {
		List<Comment> comments = new ArrayList<Comment>();
		CommentManager commentmanager = new CommentManager();
		UserManager usermanager = new UserManager();
		User user = new User();
		try {
			connection = ds.getConnection();
		
			user = usermanager.readUser(username);
			
			statement = connection.prepareStatement(readAllCommentsForUsernameSql);
			statement.setInt(1, user.getId());
			
			results = statement.executeQuery();
	
			while(results.next()) {
				Comment comment = new Comment(commentmanager, results.getString("comment"), results.getDate("date"),
						results.getInt("fkuser"), results.getInt("fkmovie"));
				
				comments.add(comment);
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
		return comments;
	}

	
	public List<Comment>  readAllCommentsForMovie(String movieId) {
		List<Comment> comments = new ArrayList<Comment>();
		CommentManager commentmanager = new CommentManager();
		MovieManager moviemanager = new MovieManager();
		Movie movie = new Movie();
		try {
			connection = ds.getConnection();
		
			movie = moviemanager.readMovie(movieId);
			
			statement = connection.prepareStatement(readAllCommentsForMovieSql);
			statement.setInt(1, movie.getId());
			
			results = statement.executeQuery();
	
			while(results.next()) {
				Comment comment = new Comment(commentmanager, results.getString("comment"), results.getDate("date"),
						results.getInt("fkuser"), results.getInt("fkmovie"));
				
				comments.add(comment);
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
		return comments;
	}

	public Comment readCommentForId(Integer commentId){
		try {
			CommentManager commentmanager = new CommentManager();
			connection = ds.getConnection();
		
			statement = connection.prepareStatement(readCommentForIdSql);
			statement.setInt(1, commentId);
			
			results = statement.executeQuery();
	
			if(results.next()) {
				Comment comment = new Comment(commentmanager, results.getString("comment"), results.getDate("date"),
						results.getInt("fkuser"), results.getInt("fkmovie"));
				
				return comment;
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
	
	
	public void updateComment(Integer commentId, String newComment) {
		try {
			connection = ds.getConnection();
					
			statement = connection.prepareStatement(updateCommentSql);
			statement.setString(1, newComment);
			statement.setInt(2, commentId);
			
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
	
	
	public void deleteComment(Integer commentId) {
		try {
			connection = ds.getConnection();
			
			statement = connection.prepareStatement(deleteCommentSql);
			statement.setInt(1, commentId);
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
