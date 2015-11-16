package demo.service;

import demo.search.NaverSearcher;
import demo.search.SearchResult;

public class SearchService extends Object{

	private String apiKey;

	public SearchService(String apiKey) {
		super();
		this.apiKey = apiKey;
	}
	
	public SearchResult search(String q, int display) {
		
		NaverSearcher searcher = new NaverSearcher(apiKey);
		SearchResult result = searcher.search(q);
		
		return result;
	}
	
	
}
