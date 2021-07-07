package DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Author;
import utils.JDBCConnection;
import utils.Log;

public class AuthorDAO implements GenericDAO<Author>{
	private Connection conn = JDBCConnection.getConnection();
	
	public Author getByUserNamePass(String uname,String passwd) {
		String query="select * from \"project1\".authors a where a.username= ? and a.passwd = ?;";
		Log.ger.info("getting an author by uname and passwd");
		try {
			PreparedStatement ps=conn.prepareStatement(query);
			Log.ger.info("encapsulating and filling query");
			ps.setString(1, uname);
			ps.setString(2, passwd);
			Log.ger.info("executing query");
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				Log.ger.info("author successfully retrieved!");
				Author a = new Author();
				Log.ger.info("refilling bean and...");
				a.setAuthor_id(rs.getInt("author_id"));
				a.setAvailablePoints(rs.getInt("availablepoints"));
				a.setUsername(rs.getString("username"));
				a.setFirstN(rs.getString("firstn"));
				a.setLastN(rs.getString("lastn"));
				a.setAge(rs.getInt("author_age"));
				a.setEmail(rs.getString("email"));
				a.setPasswd(rs.getString("passwd"));
				Log.ger.info("sending back to caller");
				return a;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public Author add(Author a) throws Exception {
		Log.ger.info("adding author to table!"); //author_id::availablepoints,username,firstn,lastn,age,email,passwd
		String query = "insert into \"project1\".authors values(default, 100,?,?,?,?,?,?) returning *;";				
		try {
			Log.ger.info("encapsulating query string");
			PreparedStatement ps = conn.prepareStatement(query);
			Log.ger.info("filling the query");
			ps.setString(1, a.getUsername());
			ps.setString(2, a.getFirstN());
			ps.setString(3, a.getLastN());
			ps.setInt(4, a.getAge());
			ps.setString(5, a.getEmail());
			ps.setString(6, a.getPasswd());
			Log.ger.info( "executing the query");	
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Log.ger.info("Author Successfully Persisted!");
				rs = ps.getResultSet();			
				a = new Author();
				Log.ger.info("refilling bean and...");
				a.setAuthor_id(rs.getInt("author_id"));
				a.setAvailablePoints(rs.getInt("availablepoints"));
				a.setUsername(rs.getString("username"));
				a.setFirstN(rs.getString("firstn"));
				a.setLastN(rs.getString("lastn"));
				a.setAge(rs.getInt("author_age"));
				a.setEmail(rs.getString("email"));
				a.setPasswd(rs.getString("passwd"));
				Log.ger.info("sending back to caller");
				return a;
				}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
return null;
	}

	@Override
	public Author getById(Integer id) {
		String query="select * from \"project1\".authors a where a.author_id=?;";
		Log.ger.info("getting an author by id");
		try {
			Log.ger.info("encapsulating and filling query");
			PreparedStatement ps=conn.prepareStatement(query);
			ps.setInt(1, id);
			Log.ger.info("executing query");
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				Log.ger.info("Author Successfully Retrieved!");
				rs=ps.getResultSet();
				Author a= new Author();
				a.setAge(rs.getInt("author_age"));
				a.setAuthor_id(rs.getInt("author_id"));
				a.setAvailablePoints(rs.getInt("availablepoints"));
				a.setEmail(rs.getString("email"));
				a.setFirstN(rs.getString("firstn"));
				a.setLastN(rs.getString("lastn"));
				a.setUsername(rs.getString("username"));
				a.setPasswd(rs.getString("passwd"));
				Log.ger.info("sending back to caller");
				return a;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Author> getAll() {
		String query="select * from \"project1\".authors;";
		List<Author> autLst=new ArrayList<Author>();
		try {
			Log.ger.info("encapsulate query");
			PreparedStatement ps = conn.prepareStatement(query);
			Log.ger.info("execute query and save into result set");
			ResultSet rs = ps.executeQuery();
			Log.ger.info("iterate over the data stream");
			while (rs.next()) {
				Author a = new Author();
				a.setAuthor_id(rs.getInt("author_id"));
				a.setAvailablePoints(rs.getInt("availablepoints"));
				a.setUsername(rs.getString("username"));
				a.setFirstN(rs.getString("firstn"));
				a.setLastN(rs.getString("lastn"));
				a.setAge(rs.getInt("author_age"));
				a.setEmail(rs.getString("email"));
				a.setPasswd(rs.getString("passwd"));
				Log.ger.info("adding to list of authors");
				autLst.add(a);
			}
			return autLst;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	public void updatePoints(Author a,Integer Points) {
		AuthorDAO adao=new AuthorDAO();
		a.setAvailablePoints(Points);
		adao.update(a);
	}
	
	//use this as subroutine for updates on a particular column
	//this means that this method has to set all cols.
	@Override
	public void update(Author a) {
		String query="update \"project1\".authors set availablepoints=?,"
				+" set username=?,"
				+" set firstn=?,"
				+" set lastn=?,"
				+" set author_age=?,"
				+" set email=?,"
				+" set passwd=?,"
				+ " where author_id=? returning *;";
		Log.ger.info("updating author information");
		try {
			Log.ger.info("encapsulating and filling query");
			PreparedStatement ps=conn.prepareStatement(query);
			
			ps.setInt(1, a.getAvailablePoints());
			ps.setString(2, a.getUsername());
			
			ps.setString(3, a.getFirstN());
			ps.setString(4, a.getLastN());
			ps.setInt(5, a.getAge());
			ps.setString(7, a.getPasswd());
			ps.setString(6, a.getEmail());
			ps.setInt(8, a.getAuthor_id());
			Log.ger.info("executing query");
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				Log.ger.info("Author Information Successfully Updated!");
				rs=ps.getResultSet();
				return;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return;
		
	}

	@Override
	public void delete(Author a) {
		String query="delete from \"project1\".authors where author_id=?";
		Log.ger.info("deleting author information");
		try {
			Log.ger.info("encapsulating and filling query");
			PreparedStatement ps=conn.prepareStatement(query);
			ps.setInt(1, a.getAuthor_id());
			Log.ger.info("executing query");
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				Log.ger.info("Author Successfully deleted!");
				rs=ps.getResultSet();
				return;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return;
	}

}
