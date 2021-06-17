package beans;


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
	private String currentApprovalPhase;//4 phases AE,SE,GE,ME
	private StoryQueue<Story> sq;
	private String pitchDate;
}
