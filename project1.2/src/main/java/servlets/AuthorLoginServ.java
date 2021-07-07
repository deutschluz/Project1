package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import DAOs.AuthorDAO;
import beans.Author;
import utils.Log;

public class AuthorLoginServ  extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7652066181530702823L;
	private AuthorDAO adao = new AuthorDAO();
	private Gson gson = new Gson();
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) {
		
	}

	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException {
		 Log.ger.info("logging in author..."); //
		  Author a = gson.fromJson(request.getReader(), Author.class);
		   Author auth = adao.getByUserNamePass(a.getUsername(),a.getPasswd());

			// Save author data in Session attribute
			HttpSession session = request.getSession();
			System.out.println(session.getId());
			session.setAttribute("loggedInAuthor", auth.toString());
			System.out.println(session.getAttribute("loggedInAuthor").toString());
			//send response to frontend
		   response.setHeader("Origin","http://localhost:8080/project1.2/authlog"); //
		  response.getWriter().append(gson.toJson(auth));
	}
	
	 @Override
	  protected void doOptions(HttpServletRequest req, HttpServletResponse resp)
	          throws ServletException, IOException {
	      setAccessControlHeaders(resp);
	      resp.setStatus(HttpServletResponse.SC_OK);
	  }

	  private void setAccessControlHeaders(HttpServletResponse resp) {
	      resp.setHeader("Access-Control-Allow-Origin", "*");
	      resp.setHeader("Access-Control-Allow-Methods", "GET");
	  }

}
