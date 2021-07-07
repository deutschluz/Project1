package beans;

import java.util.Objects;

public class Story {
	private Integer story_id;
	private Integer weight;
	private String story_type;
	private String status;
	private String title;
	private String completion_date;
	private String genre;
	private String tag_line;
	private String description;
	private String currentApprovalPhase;
	private Integer current_ed;
	private String pitchDate;
	
	
	
	public Integer getStory_id() {
		return story_id;
	}

	public void setStory_id(Integer story_id) {
		this.story_id = story_id;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public String getStory_type() {
		return story_type;
	}

	public void setStory_type(String story_type) {
		this.story_type = story_type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCompletion_date() {
		return completion_date;
	}

	public void setCompletion_date(String completion_date) {
		this.completion_date = completion_date;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getTag_line() {
		return tag_line;
	}

	public void setTag_line(String tag_line) {
		this.tag_line = tag_line;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCurrentApprovalPhase() {
		return currentApprovalPhase;
	}

	public void setCurrentApprovalPhase(String currentApprovalPhase) {
		this.currentApprovalPhase = currentApprovalPhase;
	}

	public Integer getCurrent_ed() {
		return current_ed;
	}

	public void setCurrent_ed(Integer current_ed) {
		this.current_ed = current_ed;
	}

	public String getPitchDate() {
		return pitchDate;
	}

	public void setPitchDate(String pitchDate) {
		this.pitchDate = pitchDate;
	}

	public Story() {
		super();
	}

	public Story(Integer story_id, Integer weight, String story_type, String status, String title,
			String completion_date, String genre, String tag_line, String description, String currentApprovalPhase,
			Integer current_ed, String pitchDate) {
		super();
		this.story_id = story_id;
		this.weight = weight;
		this.story_type = story_type;
		this.status = status;
		this.title = title;
		this.completion_date = completion_date;
		this.genre = genre;
		this.tag_line = tag_line;
		this.description = description;
		this.currentApprovalPhase = currentApprovalPhase;
		this.current_ed = current_ed;
		this.pitchDate = pitchDate;
	}

	public Story(String story_type, String title, String completion_date, String genre, String tag_line,
			 String pitchDate) {
		super();
		this.story_type = story_type;
		this.title = title;
		this.completion_date = completion_date;
		this.genre = genre;
		this.tag_line = tag_line;
		this.pitchDate = pitchDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(completion_date, currentApprovalPhase, current_ed, description, genre, pitchDate, status,
				story_id, story_type, tag_line, title, weight);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Story other = (Story) obj;
		return Objects.equals(completion_date, other.completion_date)
				&& Objects.equals(currentApprovalPhase, other.currentApprovalPhase)
				&& Objects.equals(current_ed, other.current_ed) && Objects.equals(description, other.description)
				&& Objects.equals(genre, other.genre) && Objects.equals(pitchDate, other.pitchDate)
				&& Objects.equals(status, other.status) && Objects.equals(story_id, other.story_id)
				&& Objects.equals(story_type, other.story_type) && Objects.equals(tag_line, other.tag_line)
				&& Objects.equals(title, other.title) && Objects.equals(weight, other.weight);
	}

	@Override
	public String toString() {
		return "Story [story_id=" + story_id + ", weight=" + weight + ", story_type=" + story_type + ", status="
				+ status + ", title=" + title + ", completion_date=" + completion_date + ", genre=" + genre
				+ ", tag_line=" + tag_line + ", description=" + description + ", currentApprovalPhase="
				+ currentApprovalPhase + ", current_ed=" + current_ed + ", pitchDate=" + pitchDate + "]";
	}
	
}
