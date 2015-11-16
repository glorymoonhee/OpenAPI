package demo.search;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class NaverSearcher {

	public enum PARAM {key, target, display, start, sort}
	/**
	 * [naver]
	 * url : http://openapi.naver.com/search
	 * key : c1b406b32dbbbbeee5f2a36ddc14067f
	 * target : cafearticle
	 * display : 10 ~ 100
	 * start : 1 ~ 1000
	 * sort : [sim | date]
	 * 
	 * 
	 */
	private String url = "http://openapi.naver.com/search";
	private Map<PARAM, String> params;
	private String apiKey;
	
	
	public NaverSearcher(String apiKey) {
		this.apiKey = apiKey;
	}

	public SearchResult search(String query) {
		return search ( query, "10", "date");
	}

	public SearchResult search(String query, String display) {
		return search(query, display, "date");
	}
	
	public SearchResult search(String query, String nDisplay, String sort) {
		return search("cafearticle", query, nDisplay, sort);
	}
	
	/**
	 * 
	 * @param target - cafearticle,  blog, news
	 * @param query
	 * @param nDisplay
	 * @param sort
	 * @return
	 */
	public SearchResult search(String target, String query, String nDisplay, String sort) {
		params = new HashMap<>();
		params.put(PARAM.key, apiKey);
		params.put(PARAM.target, target);
		params.put(PARAM.display, nDisplay);
		params.put(PARAM.start, "1");
		params.put(PARAM.sort, sort);
	
		String fullUrl = assembleURL ( url, params, query);
		
		System.out.println(fullUrl);
		try {
			Connection conn = Jsoup.connect(fullUrl);
			conn.timeout(30*1000);
			Document doc = conn.get();
			SearchResult result = docToResult ( doc );
			result.setQuery ( query );
			return result;
		} catch (IOException e1) {
			e1.printStackTrace();
			throw new RuntimeException( e1 );
		}
	}
	
	public SearchResult docToResult(Document doc) {
		String total = doc.select("rss total").text();
		String start = doc.select("rss start").text();
		String display = doc.select("rss display").text();
//		String start = doc.getElementsByTag("start").
		
		SearchResult result = new SearchResult(
				asInt(total), 
				asInt(start), 
				asInt(display));
		Elements elems = doc.getElementsByTag("item");
		Iterator<Element> itr = elems.iterator();
		List<SearchItem> items = new ArrayList<>();
		while ( itr.hasNext()) {
			Element item = itr.next();
			String title = item.select("title").text();
			String link = item.select("link").text();
			String desc = item.select("description").text();
			
			items.add(new SearchItem(title, link, desc ));
		}
		result.setItems(items);
		return result;
	}
	private int asInt(String s) {
		
		return Integer.parseInt(s);
	}

	private String assembleURL(String url, Map<PARAM, String> params, String query) {
		Iterator<PARAM> itr = params.keySet().iterator();
		String s= url + "?";
		while ( itr.hasNext() ) {
			PARAM key = itr.next();
			String value= params.get(key);
			s += key + "=" + value + "&";
		}
		s += "query" + "=" + query ;
		return s ;
	}


}