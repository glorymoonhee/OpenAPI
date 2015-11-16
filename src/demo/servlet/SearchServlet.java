package demo.servlet;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import demo.search.SearchItem;
import demo.search.SearchResult;
import demo.service.SearchService;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet(
		urlPatterns = { "/search" }, 
		initParams = { 
				@WebInitParam(name = "naver.key", value = "4f86f5eb5d1f056b081bfe52fb6e3a64")
		})
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/search.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		System.out.println("dlkdkdkd");
		String apiKey = getInitParameter("naver.key");
		SearchService service = new SearchService(apiKey);
		System.out.println("key: " + apiKey);
		
		String q = request.getParameter("q");
		int display = Integer.parseInt(request.getParameter("display"));
		System.out.println("q:" + q);
		System.out.println("display: " +  display);
		
		SearchResult result = service.search( URLEncoder.encode(q, "UTF-8"), display);
		request.setAttribute("keyword", q);
		request.setAttribute("results", result);
		
		HttpSession session = prepareSession(request);
		List<String> queries = (List<String>) session.getAttribute("query");
		queries.add(q);
		
		request.getRequestDispatcher("/WEB-INF/search.jsp").forward(request, response);;
		
	}

	private HttpSession prepareSession(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if ( session != null) {
			if ( session.getAttribute("query") == null) {
				session.setAttribute("query", new ArrayList<String>());
			}
			return session;
		}
		session = request.getSession(true);
		session.setAttribute("query", new ArrayList<String>());
		return session;
	}

}
