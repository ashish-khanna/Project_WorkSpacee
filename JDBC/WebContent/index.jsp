<%@page import="DAO.MovieManager"%>
<%@page import="java.sql.Date"%>
<%@page import="DAO.UserManager"%>
<%@page import="entityClass.*,java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	
	<%
	
	Date dob = new Date(1988, 3, 20);
	User user = null;
	UserManager usermanager = new UserManager();
	
	user = new User(usermanager, "contashish23", "world-best", "ashish", "khanna", "khanna@ccis.neu.edu", dob);
	
	
	Movie movie = null;
	MovieManager moviemanager = new MovieManager();
	
	movie = new Movie(moviemanager, "NICE-1", "THE WORLD-1", "IMAGE-1", dob);
	
	//moviemanager.createMovie(movie);
	
	/*List<Movie> movies = moviemanager.readAllMovies();
	for(Movie movie : movies) {
		out.println(movie.getMovieId());
		out.println("<br/>");
	} */
	
	moviemanager.updateMovie("NICE", movie);
	
	//out.println(movie.getMovieId());
	//moviemanager.deleteMovie("ALL");
	
	//usermanager.createUser(user);
	
	//usermanager.updateUser("contashish23", user);
	
	//usermanager.readUser("contashish");
	
	/* List<User> users = usermanager.readAllUsers();
	for(User user : users) {
		out.println(user.getUsername());
		out.println("<br/>");
	} */
	
	//user = usermanager.readUser("contashish23");
	//out.println(user.getEmail());
	
	
	/*List<Comment> comments = usermanager.getComments(3);
	for(Comment comment : comments) {
		out.println(comment.getId());
		out.println("<br/>");
	}*/
	
	List<Comment> comments = moviemanager.getComments(4);
	for(Comment comment : comments) {
		out.println(comment.getId());
		out.println("<br/>");
	}
	
	%>
	<h1>Ashish</h1>
</body>
</html>