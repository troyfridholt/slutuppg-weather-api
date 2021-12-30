package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.GettheWeather;
import model.weatherBean;

/**
 * Servlet implementation class Wservlet
 */
@WebServlet("/WServlet")
public class WServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Set response.setcontenttype(text/html)?
		
		String cityStr = request.getParameter("city");
		String countryStr = request.getParameter("country");
		//Create a citycookie and country cookie
		Cookie cityCookie = new Cookie("ckcity",cityStr);
		cityCookie.setMaxAge(2*60*60);
		response.addCookie(cityCookie);
		Cookie countryCookie = new Cookie("ckcountry", countryStr);
		countryCookie.setMaxAge(2*60*60);
		response.addCookie(countryCookie);
		
		

		weatherBean wBean = new weatherBean(cityStr, countryStr);

		GettheWeather.getWeather(wBean);

		request.setAttribute("wBean", wBean);

		RequestDispatcher rd = request.getRequestDispatcher("showWeather.jsp");
		rd.forward(request, response);

	}

}