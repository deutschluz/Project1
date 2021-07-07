package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import DAOs.AuthorDAO;
import beans.Author;

public class AuthorReg  extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2411299371438853570L;
	private AuthorDAO adao = new AuthorDAO();
	private Gson gson = new Gson();
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) {
		
	}

	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		try {

			System.out.println("Adding customer..."); //
			  Author a = gson.fromJson(request.getReader(), Author.class); System.out.println("first name is: "+ a.getFirstN());
			   Author auth;
			auth = adao.add(a);
			 response.setHeader("Origin","http://localhost:8080/project1/authreg"); //
			  response.getWriter().append(gson.toJson(auth));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
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
