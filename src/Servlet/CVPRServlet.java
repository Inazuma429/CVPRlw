package Servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Dao.CloudDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class CVPRServlet
 */
@WebServlet("/CVPRServlet")
public class CVPRServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CVPRServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		Map<String,Integer>sortMap=CloudDao.getallmax();
		JSONArray json=new JSONArray();
		int k=0;
		for(Map.Entry<String, Integer>entry:sortMap.entrySet()) {
			JSONObject job=new JSONObject();
			job.put("Kname", entry.getKey());
			job.put("Tvalue", entry.getValue());
			if(!entry.getKey().equals("for")||entry.getKey().equals("and")||entry.getKey().equals("With")||entry.getKey().equals("of")
				||entry.getKey().equals("in")||entry.getKey().equals("From")||entry.getKey().equals("A")||entry.getKey().equals("to")
				||entry.getKey().equals("a")||entry.getKey().equals("the")||entry.getKey().equals("by")) {
				json.add(job);
				k++;
			}
			if(k==30) {
				break;
			}
		}
		response.getWriter().write(json.toString());
	}

}
