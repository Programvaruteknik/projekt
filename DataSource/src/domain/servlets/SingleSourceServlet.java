package domain.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import collections.DataChart;
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
		DataSource source = factory.getDataSource(request
				.getParameter("source"));
		DataChart table = new DataChart(source.getData());
		JsonObject obj = new JsonObject();
		obj.setData(table.getTable());
		String json = new JsonParser().serialize(obj);
		response.getWriter().print(json);

	}

	private class JsonObject {
		List<List<Object>> data;

		public void setData(List<List<Object>> param) {
			data = param;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void setFactory(DataSourceFactory mockFactory) {
		this.factory = mockFactory;
	}

}
