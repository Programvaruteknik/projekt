package domain.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.api.serialization.JsonParser;
import domain.datasources.DataSource;
import domain.datasources.DataSourceFactory;
import domain.datasources.workers.SunAltitudeAtNoon;
import domain.datasources.workers.TotalFotballGoals;
import domain.matching.DataMatcher;
import domain.matching.Resolution;

@WebServlet("/ApiServlet")
public class ApiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");

<<<<<<< HEAD
		TotalFotballGoals goals = new TotalFotballGoals();

		SunAltitudeAtNoon sunAltitudeAtNoon = new SunAltitudeAtNoon();

		DataMatcher dataMatcher = new DataMatcher(goals, sunAltitudeAtNoon,
				Resolution.DAY);

		response.getWriter().print(
				new JsonParser(true).serialize(dataMatcher.match()));

		System.out.println("Done");

=======
		DataSource dataSource1 = DataSourceFactory.getDataSource(request.getParameter("datasource1"));
		DataSource dataSource2 = DataSourceFactory.getDataSource(request.getParameter("datasource2"));
		
		if(dataSource1 == null || dataSource2 == null)
		{
			response.setStatus(400);
		}
		else
		{
			boolean pretty = Boolean.parseBoolean(request.getParameter("pretty"));
			
			DataMatcher dataMatcher = new DataMatcher(dataSource1, dataSource2, Resolution.DAY);
			
			response.getWriter().print(new JsonParser(pretty).serialize(dataMatcher.match()));
			
		}
		
				
>>>>>>> refs/remotes/origin/dev
	}

}
