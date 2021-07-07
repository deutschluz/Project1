package beans;

import java.util.Objects;

public class Editor {
	private Integer editor_id;
	private String username;
	private String first_name;
	private String last_name;
	private String email;
	private String passwd;
	private String rank;
	
	

	public Integer getEditor_id() {
		return editor_id;
	}


	public void setEditor_id(Integer editor_id) {
		this.editor_id = editor_id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getFirst_name() {
		return first_name;
	}


	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}


	public String getLast_name() {
		return last_name;
	}


	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPasswd() {
		return passwd;
	}


	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}


	public String getRank() {
		return rank;
	}


	public void setRank(String rank) {
		this.rank = rank;
	}


	public Editor() {
		super();
	}


	public Editor(Integer editor_id, String username, String first_name, String last_name, String email, String passwd,
			String rank) {
		super();
		this.editor_id = editor_id;
		this.username = username;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.passwd = passwd;
		this.rank = rank;
	}


	@Override
	public int hashCode() {
		return Objects.hash(editor_id, email, first_name, last_name, passwd, rank, username);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Editor other = (Editor) obj;
		return Objects.equals(editor_id, other.editor_id) && Objects.equals(email, other.email)
				&& Objects.equals(first_name, other.first_name) && Objects.equals(last_name, other.last_name)
				&& Objects.equals(passwd, other.passwd) && Objects.equals(rank, other.rank)
				&& Objects.equals(username, other.username);
	}


	@Override
	public String toString() {
		return "Editor [editor_id=" + editor_id + ", username=" + username + ", first_name=" + first_name
				+ ", last_name=" + last_name + ", email=" + email + ", passwd=" + passwd + ", rank=" + rank + "]";
	}

}
