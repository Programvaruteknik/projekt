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

/**
 * Servlet implementation class SingleSourceServlet
 */
@WebServlet("/SingleSourceServlet")
public class SingleSourceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DataSourceFactory factory;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SingleSourceServlet() {
		super();
		factory = new DataSourceFactory();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String sourceName = request.getParameter("source");
		DataSource source = factory.getDataSource(sourceName);

		String json = new JsonParser().serialize(source.getData());
		response.getWriter().print(json);

	}

	protected void setFactory(DataSourceFactory mockFactory) {
		this.factory = mockFactory;
	}

}
