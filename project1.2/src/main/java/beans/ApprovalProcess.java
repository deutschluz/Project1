package beans;

import java.util.List;
import java.util.Objects;

public class ApprovalProcess {
	private Integer approval_id;
	private List<Integer> edList;
	private Integer story_id;
	private Integer author_id;
	
	
	
	public Integer getApproval_id() {
		return approval_id;
	}

	public void setApproval_id(Integer approval_id) {
		this.approval_id = approval_id;
	}

	public List<Integer> getEdList() {
		return edList;
	}

	public void setEdList(List<Integer> edList) {
		this.edList = edList;
	}

	public Integer getStory_id() {
		return story_id;
	}

	public void setStory_id(Integer story_id) {
		this.story_id = story_id;
	}

	public Integer getAuthor_id() {
		return author_id;
	}

	public void setAuthor_id(Integer author_id) {
		this.author_id = author_id;
	}

	public ApprovalProcess() {
		super();
	}

	@Override
	public int hashCode() {
		return Objects.hash(approval_id, author_id, edList, story_id);
	}

	public ApprovalProcess(Integer approval_id, List<Integer> edList, Integer story_id, Integer author_id) {
		super();
		this.approval_id = approval_id;
		this.edList = edList;
		this.story_id = story_id;
		this.author_id = author_id;
	}

	@Override
	public String toString() {
		return "ApprovalProcess [approval_id=" + approval_id + ", edList=" + edList + ", story_id=" + story_id
				+ ", author_id=" + author_id + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ApprovalProcess other = (ApprovalProcess) obj;
		return Objects.equals(approval_id, other.approval_id) && Objects.equals(author_id, other.author_id)
				&& Objects.equals(edList, other.edList) && Objects.equals(story_id, other.story_id);
	}
}
