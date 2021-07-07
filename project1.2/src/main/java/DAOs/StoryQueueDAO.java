package DAOs;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import beans.Author;
import beans.StoryQueue;
import utils.JDBCConnection;
import utils.Log;

public class StoryQueueDAO implements GenericDAO<StoryQueue> {
	private Connection conn = JDBCConnection.getConnection();
	
	@Override
	public StoryQueue add(StoryQueue s) throws Exception {
		Log.ger.info("adding \"project1\".storyqueue to table!"); //author_id::availablepoints,username,firstn,lastn,age,email,passwd
		String query = "insert into storyqueue values(default,?,?,?) returning *;";				
		try {
			Log.ger.info("encapsulating query string");
			PreparedStatement ps = conn.prepareStatement(query);
			Log.ger.info("filling the query");
			ps.setInt(1, s.getEd_id());
			ps.setString(2, s.getPasswd());
			Integer[] sqlst=(Integer[])s.getSQ().toArray();
			Array sqArr=conn.createArrayOf("integer", sqlst);
			ps.setArray(3, sqArr);
			Log.ger.info( "executing the query");	
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Log.ger.info("Storyqueue Successfully Persisted!");
				rs = ps.getResultSet();			
				s = new StoryQueue();
				Log.ger.info("refilling bean and...");
				s.setSq_id(rs.getInt("sq_id"));
				s.setEd_id(rs.getInt("editor_id"));
				s.setPasswd(rs.getString("passwd"));
				sqArr=rs.getArray("sq");
				sqlst=(Integer[])sqArr.getArray();
				s.setSQ(Arrays.asList(sqlst));
				Log.ger.info("sending back to caller");
				return s;
				}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public StoryQueue getById(Integer id) {
		String query="select * from \"project1\".storyqueue s where s.sq_id=?;";
		Log.ger.info("getting a storyqueue by id");
		try {
			Log.ger.info("encapsulating and filling query");
			PreparedStatement ps=conn.prepareStatement(query);
			ps.setInt(1, id);
			Log.ger.info("executing query");
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				Log.ger.info("Author Successfully Retrieved!");
				rs=ps.getResultSet();
				StoryQueue s = new StoryQueue();
				Log.ger.info("refilling bean and...");
				s.setSq_id(rs.getInt("sq_id"));
				s.setEd_id(rs.getInt("editor_id"));
				s.setPasswd(rs.getString("passwd"));
				Array stArr=rs.getArray("editorlist");
				Integer[] stlst=(Integer[])stArr.getArray();
				s.setSQ(Arrays.asList(stlst));
				Log.ger.info("sending back to caller");
				return s;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<StoryQueue> getAll() {
		String query="select * from \"project1\".storyqueue;";
		List<StoryQueue> sqLst=new ArrayList<StoryQueue>();
		try {
			Log.ger.info("encapsulate query");
			PreparedStatement ps = conn.prepareStatement(query);
			Log.ger.info("execute query and save into result set");
			ResultSet rs = ps.executeQuery();
			Log.ger.info("iterate over the data stream");
			while (rs.next()) {
				Log.ger.info("StoryQueue Successfully Retrieved!");
				rs=ps.getResultSet();
				StoryQueue s = new StoryQueue();
				Log.ger.info("refilling bean and...");
				s.setSq_id(rs.getInt("sq_id"));
				s.setEd_id(rs.getInt("editor_id"));
				s.setPasswd(rs.getString("passwd"));
				Array stArr=rs.getArray("sq");
				Integer[] stlst=(Integer[])stArr.getArray();
				s.setSQ(Arrays.asList(stlst));
				Log.ger.info("adding to list of storyqueues");
				sqLst.add(s);
			}
			return sqLst;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void update(StoryQueue s) {
		String query="update \"project1\".storyqueue s"
				+" set editor_id=?,"
				+" set passwd=?,"
				+" sq=?,"
				+ " where s.sq_id=? returning *;";
		Log.ger.info("updating storyqueue information");
		try {
			Log.ger.info("encapsulating and filling query");
			PreparedStatement ps=conn.prepareStatement(query);
			ps.setInt(1, s.getEd_id());
			ps.setString(2, s.getPasswd());
			Integer[] stlst=(Integer[])s.getSQ().toArray();
			Array stArr=conn.createArrayOf("integer", stlst);
			ps.setArray(3,stArr);
			ps.setInt(4, s.getSq_id());
			Log.ger.info("executing query");
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				Log.ger.info("StoryQueue Successfully Updated!");
				return;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return;
	}

	@Override
	public void delete(StoryQueue s) {
		String query="delete from \"project1\".storyqueue s where s.sq_id=?";
		Log.ger.info("deleting storyqueue information");
		try {
			Log.ger.info("encapsulating and filling query");
			PreparedStatement ps=conn.prepareStatement(query);
			ps.setInt(1,s.getSq_id());
			Log.ger.info("executing query");
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				Log.ger.info("StoryQueue Successfully Deleted!");
				return;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return;
	}

}
