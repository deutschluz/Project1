package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import DAOs.EditorDAO;
import beans.Editor;

public class EditorReg extends HttpServlet{

	private EditorDAO edao = new EditorDAO();
	private Gson gson = new Gson();
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) {
		
	}

	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException {
			try {
		System.out.println("Adding editor..."); //
		Editor e = gson.fromJson(request.getReader(), Editor.class); System.out.println("first name is: "+ e.getFirst_name());
			
			e = edao.add(e);
			 response.setHeader("Origin","http://localhost:8080/project1/editreg"); //
			  response.getWriter().append(gson.toJson(e));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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
