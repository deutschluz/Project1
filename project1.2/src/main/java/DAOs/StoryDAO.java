package DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Author;
import beans.Story;
import utils.JDBCConnection;
import utils.Log;

public class StoryDAO implements GenericDAO<Story> {
	private Connection conn = JDBCConnection.getConnection();
	
	@Override
	public Story add(Story s) throws Exception {
		Log.ger.info("adding Story to table!"); //(story_id ,weight ,story_type ,status,title,completion_date
		//,genre,tag_line,description, currentApprovalPhase,current_ed,pitchDate
		                                                                    //12
		String query = "insert into \"project1\".stories values(default,?,?,?,?,?,?,?,?,?,?,?) returning *;";				
		try {
			Log.ger.info("encapsulating query string");
			PreparedStatement ps = conn.prepareStatement(query);
			Log.ger.info("filling the query");
			
			ps.setInt(1, s.getWeight());
		    ps.setString(2,s.getStory_type());
			ps.setString(3, s.getStatus());
			ps.setString(4, s.getTitle());
			ps.setString(5, s.getCompletion_date());
			ps.setString(6, s.getGenre());
			ps.setString(7, s.getTag_line());
			ps.setString(8, s.getDescription());
			ps.setString(9, s.getCurrentApprovalPhase());
			ps.setInt(10, s.getCurrent_ed());
			ps.setString(11, s.getPitchDate());
			Log.ger.info( "executing the query");	
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Log.ger.info("Story Successfully Persisted!");
				rs = ps.getResultSet();	
				s = new Story();
				Log.ger.info("refilling bean and...");
				s.setCompletion_date(rs.getString("completion_date"));
				s.setCurrent_ed(rs.getInt("current_ed"));
				s.setCurrentApprovalPhase(rs.getString("currentapprovalphase"));
				s.setDescription(rs.getString("description"));
				s.setGenre(rs.getString("genre"));
				s.setPitchDate(rs.getString("pitchdate"));
				s.setStatus(rs.getString("status"));
				s.setStory_type(rs.getString("story_type"));
				s.setTag_line(rs.getString("tag_line"));
				s.setTitle(rs.getString("title"));
				s.setWeight(rs.getInt("weight"));
				Log.ger.info("sending back to caller");
				return s;
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Story getById(Integer id) {
		String query="select * from \"project1\".stories s where s.story_id=?;";
		Log.ger.info("getting an story by id");
		try {
			Log.ger.info("encapsulating and filling query");
			PreparedStatement ps=conn.prepareStatement(query);
			ps.setInt(1, id);
			Log.ger.info("executing query");
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				Log.ger.info("Story Successfully Retrieved!");
				rs=ps.getResultSet();
				Story s = new Story();
				Log.ger.info("refilling bean and...");
				s.setCompletion_date(rs.getString("completion_date"));
				s.setCurrent_ed(rs.getInt("current_ed"));
				s.setCurrentApprovalPhase(rs.getString("currentapprovalphase"));
				s.setDescription(rs.getString("description"));
				s.setGenre(rs.getString("genre"));
				s.setPitchDate(rs.getString("pitchdate"));
				s.setStatus(rs.getString("status"));
				s.setStory_type(rs.getString("story_type"));
				s.setTag_line(rs.getString("tag_line"));
				s.setTitle(rs.getString("title"));
				s.setWeight(rs.getInt("weight"));
				Log.ger.info("sending back to caller");
				return s;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Story> getAll() {
		String query="select * from \"project1\".stories;";
		List<Story> stLst=new ArrayList<Story>();
		try {
			Log.ger.info("encapsulate query");
			PreparedStatement ps = conn.prepareStatement(query);
			Log.ger.info("execute query and save into result set");
			ResultSet rs = ps.executeQuery();
			Log.ger.info("iterate over the data stream");
			while (rs.next()) {
				//create account ob
				Story s = new Story();
				Log.ger.info("refilling bean and...");
				s.setStory_id(rs.getInt("story_id"));
				s.setCompletion_date(rs.getString("completion_date"));
				s.setCurrent_ed(rs.getInt("current_ed"));
				s.setCurrentApprovalPhase(rs.getString("currentapprovalphase"));
				s.setDescription(rs.getString("description"));
				s.setGenre(rs.getString("genre"));
				s.setPitchDate(rs.getString("pitchdate"));
				s.setStatus(rs.getString("status"));
				s.setStory_type(rs.getString("story_type"));
				s.setTag_line(rs.getString("tag_line"));
				s.setTitle(rs.getString("title"));
				s.setWeight(rs.getInt("weight"));

				Log.ger.info("adding to list of stories");
				stLst.add(s);
			}
			return stLst;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void update(Story s) {
		String query="update \"project1\".stories"
				+" set weight=? ,"
				+" set story_type=? ,"
				+" set status=? ,"
				+" set title=? ,"
				+" set completion_date=? ,"
				+" set genre=? ,"
				+" set tag_line=? ,"
				+" set description=?,"
	            +" set currentApprovalPhase=?,"
				+" set current_ed=?,"
				+" set pitchDate=?,"
				+" where story_id=? returning *;";
		Log.ger.info("updating story information");
		try {
			Log.ger.info("encapsulating and filling query");
			PreparedStatement ps=conn.prepareStatement(query);
			ps.setInt(1, s.getWeight());
			ps.setString(2, s.getStory_type());
			ps.setString(3,s.getStatus());
			ps.setString(4,s.getTitle());
			ps.setString(5,s.getCompletion_date());
			ps.setString(6,s.getGenre());
			ps.setString(7,s.getTag_line());
			ps.setString(8,s.getDescription());
			ps.setString(9,s.getCurrentApprovalPhase());
			ps.setInt(10,s.getCurrent_ed());
			ps.setString(11,s.getPitchDate());
			ps.setInt(12,s.getStory_id());
			Log.ger.info("executing query");
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				Log.ger.info("Story Successfully Updated!");
				return;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return;
	}

	@Override
	public void delete(Story t) {
		String query="delete from \"project1\".stories where story_id=?";
		Log.ger.info("deleting story information");
		try {
			Log.ger.info("encapsulating and filling query");
			PreparedStatement ps=conn.prepareStatement(query);
			ps.setInt(1,t.getStory_id());
			Log.ger.info("executing query");
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				Log.ger.info("Story Successfully Deleted!");
				return;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return;
		
	}

}
