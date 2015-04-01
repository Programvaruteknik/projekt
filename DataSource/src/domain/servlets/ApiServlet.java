package domain.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.api.serialization.JsonParser;
import domain.datasources.workers.SunAltitudeAtNoon;
import domain.datasources.workers.TotalFotballGoals;
import domain.matching.DataMatcher;
import domain.matching.Resolution;


@WebServlet("/ApiServlet")
public class ApiServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("application/json");
		
		
		TotalFotballGoals goals = new TotalFotballGoals();
		
		SunAltitudeAtNoon sunAltitudeAtNoon = new SunAltitudeAtNoon();
		
		DataMatcher dataMatcher = new DataMatcher(goals, sunAltitudeAtNoon, Resolution.DAY);
		
		
		
		response.getWriter().print(new JsonParser().serialize(dataMatcher.match()));
		
		System.out.println("Done");
				
	}

}
