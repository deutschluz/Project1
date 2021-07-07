package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import DAOs.ApprovalProcessDAO;
import beans.ApprovalProcess;

public class ViewAllAPs extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ApprovalProcessDAO adao=new ApprovalProcessDAO();
	private Gson gson = new Gson();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {	
				List<ApprovalProcess> aps = adao.getAll();
				response.setHeader("Access-Control-Allow-Origin","*");
				response.getWriter().append(gson.toJson(aps));

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
