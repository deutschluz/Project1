package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import DAOs.StoryDAO;
import beans.Author;
import beans.Story;
import utils.Parser;
public class StoryServ extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7892824309335312592L;
	private StoryDAO sdao = new StoryDAO();
	private Gson gson = new Gson();
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) {
		
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException {
		try {
			System.out.println("getting loggedInAuthor to update available points");
			HttpSession session = request.getSession();
			System.out.println(session.getId());
			System.out.println(session.getAttribute("loggedInAuthor").toString());
			//creating an author object from session attribute
			String authString=session.getAttribute("loggedInAuthor").toString();
			String[] authStrArr=Parser.parseToString(authString,"Author");//this should have all of the author
				//properties in different indexes.
			Author a=new Author();
		 System.out.println("Adding story..."); //
		  Story s = gson.fromJson(request.getReader(), Story.class); System.out.println("title is: "+ s.getTitle());
		   Story st = sdao.add(s);
		   response.setHeader("Origin","http://localhost:8080/project1/storyreg"); //
		  response.getWriter().append(gson.toJson(st));
		}catch(Exception e) {
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
