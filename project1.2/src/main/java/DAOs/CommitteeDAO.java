package DAOs;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import beans.Committee;
import utils.JDBCConnection;
import utils.Log;

public class CommitteeDAO implements GenericDAO<Committee>{
	private Connection conn = JDBCConnection.getConnection();
	
	@Override
	public Committee add(Committee c) throws Exception {
		Log.ger.info("adding committee to table!"); //committee_id,comname,genre,se1editor,se2editor,memberlist
		String query = "insert into \"project1\".committees values(default,?,?,?,?,?) returning *;";				
		try {
			Log.ger.info("encapsulating query string");
			PreparedStatement ps = conn.prepareStatement(query);
			Log.ger.info("filling the query");
			ps.setString(1, c.getComname());
			ps.setString(2, c.getGenre());
			ps.setInt(3, c.getSe1editor_id());
			ps.setInt(4, c.getSe2editor_id());
			Integer[] memlst=(Integer[])c.getMembers().toArray();
			Array memArr=conn.createArrayOf("integer", memlst);
			ps.setArray(5, memArr);
			Log.ger.info( "executing the query");	
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Log.ger.info("Committee Successfully Persisted!");
				rs = ps.getResultSet();			
				c = new Committee();
				Log.ger.info("refilling bean and...");
				c.setCommittee_id(rs.getInt("committee_id"));
				c.setComname(rs.getString("comname"));
				c.setGenre(rs.getString("genre"));
				c.setSe1editor_id(rs.getInt("se1editor_id"));
				c.setSe2editor_id(rs.getInt("se2editor_id"));
				memArr=rs.getArray("memberlist");
				memlst=(Integer[])memArr.getArray();
				c.setMembers(Arrays.asList(memlst));
		
				Log.ger.info("sending object back to caller");
				return c;
				}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Committee getById(Integer id) {
		String query="select * from \"project1\".committees c where c.committee_id=?;";
		Log.ger.info("getting a committee by id");
		try {
			Log.ger.info("encapsulating and filling query");
			PreparedStatement ps=conn.prepareStatement(query);
			ps.setInt(1, id);
			Log.ger.info("executing query");
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				Log.ger.info("Committee Successfully Retrieved!");
				rs=ps.getResultSet();
				Committee c = new Committee();
				Log.ger.info("refilling bean and...");
				c.setCommittee_id(rs.getInt("committee_id"));
				c.setComname(rs.getString("comname"));
				c.setGenre(rs.getString("genre"));
				c.setSe1editor_id(rs.getInt("se1editor_id"));
				c.setSe2editor_id(rs.getInt("se2editor_id"));
				Array memArr=rs.getArray("memberlist");
				Integer[] memlst=(Integer[])memArr.getArray();
				c.setMembers(Arrays.asList(memlst));
		
				Log.ger.info("sending object back to caller");
				return c;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Committee> getAll() {
		String query="select * from \"project1\".committees;";
		List<Committee> comLst=new ArrayList<Committee>();
		try {
			Log.ger.info("encapsulate query");
			PreparedStatement ps = conn.prepareStatement(query);
			Log.ger.info("execute query and save into result set");
			ResultSet rs = ps.executeQuery();
			Log.ger.info("iterate over the data stream");
			while (rs.next()) {
				Log.ger.info("committee data retrieved!");
				Committee c = new Committee();
				c.setCommittee_id(rs.getInt("committee_id"));
				c.setComname(rs.getString("comname"));
				c.setGenre(rs.getString("genre"));
				c.setSe1editor_id(rs.getInt("se1editor_id"));
				c.setSe2editor_id(rs.getInt("se2editor_id"));
				Array memArr=rs.getArray("memberlist");
				Integer[] memlst=(Integer[])memArr.getArray();
				c.setMembers(Arrays.asList(memlst));
				Log.ger.info("adding to list of committees");
				comLst.add(c);
			}
			return comLst;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}

	@Override
	public void update(Committee c) {
		String query="update \"project1\".committees "
				+" set comname=?"
				+" set genre=?"
				+" set se1editor_id=?"
				+" set se2editor_id=?"
				+" memberlist=?"
				+ " where committee_id=? returning *;";
		Log.ger.info("updating committee information");
		try {
			Log.ger.info("encapsulating and filling query");
			PreparedStatement ps=conn.prepareStatement(query);
			ps.setInt(1, c.getCommittee_id());
			Log.ger.info("executing query");
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				Log.ger.info("Committee Successfully Updated!");
				rs=ps.getResultSet();
				return;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return;
		
	}

	@Override
	public void delete(Committee t) {
		String query="delete from \"project1\".committees where committee_id=?";
		Log.ger.info("deleting committee information");
		try {
			Log.ger.info("encapsulating and filling query");
			PreparedStatement ps=conn.prepareStatement(query);
			ps.setInt(1, t.getCommittee_id());
			Log.ger.info("executing query");
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				Log.ger.info("Committee Successfully Deleted!");
				rs=ps.getResultSet();
				return;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return;
	}

}
