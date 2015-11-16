package demo.search;

public class SearchItem {

	private String title;
	private String link;
	private String description;
	public SearchItem(String title, String link, String description) {
		this.title = title;
		this.link = link;
		this.description = description;
	}
	
	public String getTitle() {
		return title;
	}
	public String getLink() {
		return link;
	}
	public String getDescription() {
		return description;
	}
	@Override
	public String toString() {
		return "SearchItem [title=" + title + ", link=" + link + ", description=" + description + "]";
	}
	
	
}
