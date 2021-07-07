package beans;

import java.util.Objects;

public class Author {
	private Integer author_id;
	private Integer availablePoints;
	private String username;
	private String firstN;
	private String lastN;
	private Integer age;
	private String email;
	private String passwd;
	
	public Integer getAuthor_id() {
		return author_id;
	}

	public void setAuthor_id(Integer author_id) {
		this.author_id = author_id;
	}

	public Integer getAvailablePoints() {
		return availablePoints;
	}

	public void setAvailablePoints(Integer availablePoints) {
		this.availablePoints = availablePoints;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstN() {
		return firstN;
	}

	public void setFirstN(String firstN) {
		this.firstN = firstN;
	}

	public String getLastN() {
		return lastN;
	}

	public void setLastN(String lastN) {
		this.lastN = lastN;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
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

	public Author() {
		super();
	}

	@Override
	public int hashCode() {
		return Objects.hash(age, author_id, availablePoints, email, firstN, lastN, passwd, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Author other = (Author) obj;
		return Objects.equals(age, other.age) && Objects.equals(author_id, other.author_id)
				&& Objects.equals(availablePoints, other.availablePoints) && Objects.equals(email, other.email)
				&& Objects.equals(firstN, other.firstN) && Objects.equals(lastN, other.lastN)
				&& Objects.equals(passwd, other.passwd) && Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "Author [author_id=" + author_id + ", availablePoints=" + availablePoints + ", username=" + username
				+ ", firstN=" + firstN + ", lastN=" + lastN + ", age=" + age + ", email=" + email + ", passwd=" + passwd
				+ "]";
	}

	public Author(Integer author_id, Integer availablePoints, String username, String firstN, String lastN, Integer age,
			String email, String passwd) {
		super();
		this.author_id = author_id;
		this.availablePoints = availablePoints;
		this.username = username;
		this.firstN = firstN;
		this.lastN = lastN;
		this.age = age;
		this.email = email;
		this.passwd = passwd;
	}
	
	
}
