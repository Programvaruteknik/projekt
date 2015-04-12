package domain.servlets;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;

import domain.datasources.DataSource;
import domain.datasources.DataSourceFactory;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestSingleSource {
	Map<LocalDate, Double> map;
	StringWriter writer;
	HttpServletRequest request = mock(HttpServletRequest.class);
	HttpServletResponse response = mock(HttpServletResponse.class);
	SingleSourceServlet servlet;
	
	@Before
	public void setup(){
		map = new HashMap<>();
		writer = new StringWriter();
		mockFactory = mock(DataSourceFactory.class);
		servlet = new SingleSourceServlet();
		
		setupMap();
		setupFactory();
		setupResponseRequest();

	}
	
	public void setupResponseRequest() {
		when(request.getParameter("source")).thenReturn("mockSource");
		try {
			when(response.getWriter()).thenReturn(new PrintWriter(writer));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setupMap() {
		map = new HashMap<>();
		map.put(LocalDate.parse("2001-01-02"), 2.0);
		map.put(LocalDate.parse("2001-01-01"), 1.0);
	}

	DataSource source = mock(DataSource.class);
	DataSourceFactory mockFactory;

	public void setupFactory() {
		when(source.getData()).thenReturn(map);
		when(mockFactory.getDataSource("mockSource")).thenReturn(source);
	}

	@Test
	public void testFormated() throws ServletException, IOException {

		String expectedJson = "{\"data\":[[\"2001-01-02\",2.0],[\"2001-01-01\",1.0]]}";
		servlet.setFactory(mockFactory);
		servlet.doGet(request, response);
		assertEquals(expectedJson, writer.toString());
	}

}
