package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import DAOs.EditorDAO;
import beans.Editor;

public class EditorLoginServ extends HttpServlet{
	private Gson gson = new Gson();
	private EditorDAO edao= new EditorDAO();

	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException {
		 System.out.println("checking credentials..."); //
		  Editor a = gson.fromJson(request.getReader(), Editor.class); System.out.println("user name is: "+ a.getUsername());
		   Editor ed = edao.getByUserNamePass(a.getUsername(),a.getPasswd());
		   //store in session 
		// Save author data in Session attribute
					HttpSession session = request.getSession();
					System.out.println(session.getId());
					session.setAttribute("loggedInEditor", ed.toString());
		   response.setHeader("Origin","http://localhost:8080/project1.2/editlog"); //
		  response.getWriter().append(gson.toJson(ed));
		  
	}
	
	 
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
