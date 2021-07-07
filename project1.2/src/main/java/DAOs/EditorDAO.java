package DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Editor;
import utils.JDBCConnection;
import utils.Log;

public class EditorDAO implements GenericDAO<Editor>{
	private Connection conn = JDBCConnection.getConnection();
	
	public Editor getByUserNamePass(String uname,String passwd) {
		String query="select * from \"project1\".editors e where e.username=? and e.passwd =?;";
		Log.ger.info("getting an editor by uname and passwd");
		try {
			PreparedStatement ps=conn.prepareStatement(query);
			Log.ger.info("encapsulating and filling query");
			ps.setString(1, uname);
			ps.setString(2,passwd);
			Log.ger.info("executing query");
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				Log.ger.info("editor successfully retrieved!");
				Editor ed = new Editor();
				Log.ger.info("refilling bean and...");
			    ed.setEditor_id(rs.getInt("editor_id"));
			    ed.setUsername(rs.getString("username"));
			    ed.setFirst_name(rs.getString("firstn"));
			    ed.setLast_name(rs.getString("lastn"));
			    ed.setEmail(rs.getString("email"));
			    ed.setPasswd(rs.getString("passwd"));
			    ed.setRank(rs.getString("edrank"));
				Log.ger.info("sending back to caller");
				return ed;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Editor add(Editor ed) throws Exception {
		Log.ger.info("adding editor to db!"); //editor_id::username,firstn,lastn,email,passwd,edrank
		String query = "insert into \"project1\".editors values(default,?,?,?,?,?,?) returning *;";				
		try {
			Log.ger.info("encapsulating query string");
			PreparedStatement ps = conn.prepareStatement(query);
			Log.ger.info("filling the query");
			ps.setString(1, ed.getUsername());
			ps.setString(2, ed.getFirst_name());
			ps.setString(3, ed.getLast_name());
			ps.setString(4, ed.getEmail());
			ps.setString(5, ed.getPasswd());
			ps.setString(6, ed.getRank());
			Log.ger.info("executing the query");	
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Log.ger.info("Author Successfully Persisted!");
				rs = ps.getResultSet();			
				ed = new Editor();
				Log.ger.info("refilling bean and...");
			    ed.setEditor_id(rs.getInt("editor_id"));
			    ed.setUsername(rs.getString("username"));
			    ed.setFirst_name(rs.getString("firstn"));
			    ed.setLast_name(rs.getString("lastn"));
			    ed.setEmail(rs.getString("email"));
			    ed.setPasswd(rs.getString("passwd"));
			    ed.setRank(rs.getString("edrank"));
				Log.ger.info("sending back to caller");
				return ed;
				}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Editor getById(Integer id) {
		String query="select * from \"project1\".editors e where e.editor_id=?;";
		Log.ger.info("getting an editor by id");
		try {
			Log.ger.info("encapsulating and filling query");
			PreparedStatement ps=conn.prepareStatement(query);
			ps.setInt(1, id);
			Log.ger.info("executing query");
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				Log.ger.info("Editor Successfully Retrieved!");
				rs=ps.getResultSet();
				Editor ed = new Editor();
				Log.ger.info("refilling bean and...");
			    ed.setEditor_id(rs.getInt("editor_id"));
			    ed.setUsername(rs.getString("username"));
			    ed.setFirst_name(rs.getString("firstn"));
			    ed.setLast_name(rs.getString("lastn"));
			    ed.setEmail(rs.getString("email"));
			    ed.setPasswd(rs.getString("passwd"));
			    ed.setRank(rs.getString("edrank"));
				Log.ger.info("sending back to caller");
				return ed;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Editor> getAll() {
		String query="select * from \"project1\".editors;";
		List<Editor> edLst=new ArrayList<Editor>();
		try {
			Log.ger.info("encapsulate query");
			PreparedStatement ps = conn.prepareStatement(query);
			Log.ger.info("execute query and save into result set");
			ResultSet rs = ps.executeQuery();
			Log.ger.info("iterate over the data stream");
			while (rs.next()) {
				Editor ed = new Editor();
				 ed.setEditor_id(rs.getInt("editor_id"));
				    ed.setUsername(rs.getString("username"));
				    ed.setFirst_name(rs.getString("firstn"));
				    ed.setLast_name(rs.getString("lastn"));
				    ed.setEmail(rs.getString("email"));
				    ed.setPasswd(rs.getString("passwd"));
				    ed.setRank(rs.getString("edrank"));
				Log.ger.info("adding to list of editors");
				edLst.add(ed);
			}
			return edLst;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void update(Editor t) {
		String query="update \"project1\".editors"
				+" set username=?"
				+" set firstn=?"
				+" set lastn=?"
				+" email=?"
				+" passwd=?"
				+" edrank=?"
				+ " where editor_id=? returning *;";
		Log.ger.info("updating editor information");
		try {
			Log.ger.info("encapsulating and filling query");
			PreparedStatement ps=conn.prepareStatement(query);
			
			ps.setString(1,t.getUsername() );
			ps.setString(2,t.getFirst_name() );
			
			ps.setString(3, t.getLast_name());
			ps.setString(4, t.getEmail());
			ps.setString(5, t.getPasswd());
			ps.setString(6, t.getRank());
			ps.setInt(7, t.getEditor_id());
			Log.ger.info("executing query");
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				Log.ger.info("Editor Information Successfully Updated!");
				rs=ps.getResultSet();
				return;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return;
	}

	@Override
	public void delete(Editor ed) {
		String query="delete from \"project1\".editors where editor_id=?";
		Log.ger.info("deleting editor information");
		try {
			Log.ger.info("encapsulating and filling query");
			PreparedStatement ps=conn.prepareStatement(query);
			ps.setInt(1, ed.getEditor_id());
			Log.ger.info("executing query");
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				Log.ger.info("Editor Successfully deleted!");
				rs=ps.getResultSet();
				return;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return;
	}

}
