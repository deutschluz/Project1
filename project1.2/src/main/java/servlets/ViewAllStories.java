package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import DAOs.StoryDAO;
import beans.Story;

public class ViewAllStories extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private StoryDAO sdao=new StoryDAO();
	private Gson gson = new Gson();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {			
				List<Story> sts = sdao.getAll();
				response.setHeader("Access-Control-Allow-Origin","*");
				response.getWriter().append(gson.toJson(sts));

	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.getWriter().append("Post request");
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
