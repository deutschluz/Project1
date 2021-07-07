package beans;

import java.util.List;
import java.util.Objects;

public class Committee {
	private Integer committee_id;
	private String comname;
	private String genre;
	private Integer se1editor_id;
	private Integer se2editor_id;
	private List<Integer> members;
	
	
	public Committee(Integer committee_id, String comname, String genre, Integer se1editor_id, Integer se2editor_id,
			List<Integer> members) {
		super();
		this.committee_id = committee_id;
		this.comname = comname;
		this.genre = genre;
		this.se1editor_id = se1editor_id;
		this.se2editor_id = se2editor_id;
		this.members = members;
	}


	@Override
	public String toString() {
		return "Committee [committee_id=" + committee_id + ", comname=" + comname + ", genre=" + genre
				+ ", se1editor_id=" + se1editor_id + ", se2editor_id=" + se2editor_id + ", members=" + members + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(committee_id, comname, genre, members, se1editor_id, se2editor_id);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Committee other = (Committee) obj;
		return Objects.equals(committee_id, other.committee_id) && Objects.equals(comname, other.comname)
				&& Objects.equals(genre, other.genre) && Objects.equals(members, other.members)
				&& Objects.equals(se1editor_id, other.se1editor_id) && Objects.equals(se2editor_id, other.se2editor_id);
	}


	public Integer getCommittee_id() {
		return committee_id;
	}


	public void setCommittee_id(Integer committee_id) {
		this.committee_id = committee_id;
	}


	public String getComname() {
		return comname;
	}


	public void setComname(String comname) {
		this.comname = comname;
	}


	public String getGenre() {
		return genre;
	}


	public void setGenre(String genre) {
		this.genre = genre;
	}


	public Integer getSe1editor_id() {
		return se1editor_id;
	}


	public void setSe1editor_id(Integer se1editor_id) {
		this.se1editor_id = se1editor_id;
	}


	public Integer getSe2editor_id() {
		return se2editor_id;
	}


	public void setSe2editor_id(Integer se2editor_id) {
		this.se2editor_id = se2editor_id;
	}


	public List<Integer> getMembers() {
		return members;
	}


	public void setMembers(List<Integer> members) {
		this.members = members;
	}


	public Committee() {
		super();
	}
	
	
}
