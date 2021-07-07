package beans;

import java.util.List;
import java.util.Objects;

public class StoryQueue {
	private Integer sq_id;	
	private Integer ed_id;
	private String passwd;
    private List<Integer> SQ;
	
    
    
	public StoryQueue() {
		super();
	}
	
	
	
	public StoryQueue(Integer sq_id, Integer ed_id, String passwd, List<Integer> sQ) {
		super();
		this.sq_id = sq_id;
		this.ed_id = ed_id;
		this.passwd = passwd;
		this.SQ = sQ;
	}



	@Override
	public String toString() {
		return "StoryQueue [sq_id=" + sq_id + ", ed_id=" + ed_id + ", passwd=" + passwd + ", SQ=" + SQ + "]";
	}



	@Override
	public int hashCode() {
		return Objects.hash(SQ, ed_id, passwd, sq_id);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StoryQueue other = (StoryQueue) obj;
		return Objects.equals(SQ, other.SQ) && Objects.equals(ed_id, other.ed_id)
				&& Objects.equals(passwd, other.passwd) && Objects.equals(sq_id, other.sq_id);
	}



	public Integer getSq_id() {
		return sq_id;
	}

	public void setSq_id(Integer sq_id) {
		this.sq_id = sq_id;
	}

	public Integer getEd_id() {
		return ed_id;
	}

	public void setEd_id(Integer ed_id) {
		this.ed_id = ed_id;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public List<Integer> getSQ() {
		return SQ;
	}

	public void setSQ(List<Integer> sQ) {
		SQ = sQ;
	}
	
}
