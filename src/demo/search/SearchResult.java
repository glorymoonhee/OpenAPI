package demo.search;

import java.util.List;

public class SearchResult {

	private int total;
	private int start;
	private int display;
	private String query ;
	
	private List<SearchItem> items;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getDisplay() {
		return display;
	}

	public void setDisplay(int display) {
		this.display = display;
	}

	public List<SearchItem> getItems() {
		return items;
	}

	public void setItems(List<SearchItem> items) {
		this.items = items;
	}

	public SearchResult(int total, int start, int display) {
		super();
		this.total = total;
		this.start = start;
		this.display = display;
	}

	public SearchResult(int total, int start, int display, String query) {
		super();
		this.total = total;
		this.start = start;
		this.display = display;
		this.query = query;
	}

	public void setQuery(String query) {
		this.query = query;
	}
	
	public String getQuery ( ) {
		return this.query ;
	}
}
