package projectDAO;

import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;

import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.filter.ClientFilter;

public class AuthenticationFilter extends ClientFilter {

	@Context
	HttpSession session;
	
	   @Override
	   public ClientResponse handle(ClientRequest request) {
		   User user = (User) session.getAttribute("USER");
		   if(user != null)
		   {
			   System.out.println(user.getId());
		   }
	       ClientResponse response = getNext().handle(request);
	       return response;
	   }
	}