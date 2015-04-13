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
import domain.matching.DataMatcher;
import domain.matching.Resolution;

@WebServlet("/ApiServlet")
public class ApiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");

		DataSourceFactory factory = new DataSourceFactory();
		
		DataSource dataSource1 = factory.getDataSource(request.getParameter("datasource1"));
		DataSource dataSource2 = factory.getDataSource(request.getParameter("datasource2"));
		
		if(dataSource1 == null || dataSource2 == null)
		{
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
		else
		{
			boolean pretty = Boolean.parseBoolean(request.getParameter("pretty"));
			
			DataMatcher dataMatcher = new DataMatcher(dataSource1, dataSource2, Resolution.DAY);
			
			response.getWriter().print(new JsonParser(pretty).serialize(dataMatcher.match()));
			
		}
		
				
	}

}
