package DAOs;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import beans.ApprovalProcess;
import utils.JDBCConnection;
import utils.Log;

public class ApprovalProcessDAO implements GenericDAO<ApprovalProcess>{
	private Connection conn = JDBCConnection.getConnection();
	
	@Override
	public ApprovalProcess add(ApprovalProcess ap) throws Exception {
		Log.ger.info("adding \"project1\".approvalprocess to table!"); //ap_id, editorlist,story_id,author_id
		String query = "insert into approvalprocess values(default,?,?,?) returning *;";				
		try {
			Log.ger.info("encapsulating query string");
			PreparedStatement ps = conn.prepareStatement(query);
			Log.ger.info("filling the query");
			Integer[] edlst=(Integer[]) ap.getEdList().toArray();
			Array edArr=conn.createArrayOf("integer", edlst);
			ps.setArray(1, edArr);
			ps.setInt(2, ap.getStory_id());
			ps.setInt(3, ap.getAuthor_id());
			Log.ger.info( "executing the query");	
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Log.ger.info("Author Successfully Persisted!");
				rs = ps.getResultSet();			
				ap = new ApprovalProcess();
				Log.ger.info("refilling bean and...");
				ap.setAuthor_id(rs.getInt("author_id"));
				edArr=rs.getArray("editorlist");
				edlst=(Integer[])edArr.getArray();
				ap.setEdList(Arrays.asList(edlst));
				Log.ger.info("sending back to caller");
				return ap;
				}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ApprovalProcess getById(Integer id) {
		String query="select * from \"project1\".approvalprocess a where a.ap_id=?;";
		Log.ger.info("getting an aprovalprocess by id");
		try {
			Log.ger.info("encapsulating and filling query");
			PreparedStatement ps=conn.prepareStatement(query);
			ps.setInt(1, id);
			Log.ger.info("executing query");
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				Log.ger.info("ApprovalProcess Successfully Retrieved!");
				rs=ps.getResultSet();
				ApprovalProcess ap = new ApprovalProcess();
				Log.ger.info("refilling bean and...");
				ap.setAuthor_id(rs.getInt("author_id"));
				ap.setStory_id(rs.getInt("story_id"));
				ap.setApproval_id(id);
				Array edArr=rs.getArray("editorlist");
				Integer[] edlst=(Integer[])edArr.getArray();
				ap.setEdList(Arrays.asList(edlst));
				Log.ger.info("sending back to caller");
				return ap;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<ApprovalProcess> getAll() {
		String query="select * from \"project1\".approvalprocess;";
		List<ApprovalProcess> apLst=new ArrayList<ApprovalProcess>();
		try {
			Log.ger.info("encapsulate query");
			PreparedStatement ps = conn.prepareStatement(query);
			Log.ger.info("execute query and save into result set");
			ResultSet rs = ps.executeQuery();
			Log.ger.info("iterate over the data stream");
			while (rs.next()) {
				Log.ger.info("ApprovalProcess Successfully Retrieved!");
				rs=ps.getResultSet();
				ApprovalProcess ap = new ApprovalProcess();
				Log.ger.info("refilling bean and...");
				ap.setAuthor_id(rs.getInt("author_id"));
				ap.setStory_id(rs.getInt("story_id"));
				ap.setApproval_id(rs.getInt("ap_id"));
				Array edArr=rs.getArray("editorlist");
				Integer[] edlst=(Integer[])edArr.getArray();
				ap.setEdList(Arrays.asList(edlst));
				Log.ger.info("adding to list of authors");
				apLst.add(ap);
			}
			return apLst;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void update(ApprovalProcess ap) {
		String query="update \"project1\".approvalprocess"
				+" set editorlist=?"
				+" set story_id=?"
				+" set author_id=?"
				+ " where ap_id=? returning *;";
		Log.ger.info("updating author information");
		try {
			Log.ger.info("encapsulating and filling query");
			PreparedStatement ps=conn.prepareStatement(query);
			Integer[] edlst=(Integer[]) ap.getEdList().toArray();
			Array edArr=conn.createArrayOf("integer", edlst);
			ps.setArray(1, edArr);
			ps.setInt(3,ap.getAuthor_id());
			ps.setInt(2, ap.getStory_id());
			ps.setInt(4, ap.getApproval_id());
			Log.ger.info("executing query");
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				Log.ger.info("ApprovalProcess Successfully Updated!");
				return;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return;	
	}

	@Override
	public void delete(ApprovalProcess t) {
		String query="delete from \"project1\".approvalprocess a where a.ap_id=? returning *;";
		Log.ger.info("deleting approvalprocess information");
		try {
			Log.ger.info("encapsulating and filling query");
			PreparedStatement ps=conn.prepareStatement(query);
			ps.setInt(1, t.getApproval_id());
			Log.ger.info("executing query");
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				Log.ger.info("ApprovalProcess Successfully Deleted!");
				return;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return;
		
	}
	
}
