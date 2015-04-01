package domain.servlets;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;

public class ApiServletTest
{

	HttpServletRequest request;
	HttpServletResponse response;
	StringWriter stringWriter = new StringWriter();
	ApiServlet apiServlet;

	@Before
	public void setupTest()
	{
		apiServlet = new ApiServlet();
		request = mock(HttpServletRequest.class);
		response = mock(HttpServletResponse.class);
		try
		{
			when(response.getWriter()).thenReturn(new PrintWriter(stringWriter));
		} catch (IOException ex)
		{
			fail("Unexpected Exception thrown");
		}
	}
	
	@Test
	public void testMissingDataSource1()
	{
		when(request.getParameter("datasource1")).thenReturn("TotalFotballGoals");
		
		try
		{
			apiServlet.doGet(request, response);
		} catch (ServletException | IOException e)
		{
			fail("Unexpected Exception thrown");
		}
		
		assertEquals("", stringWriter.toString());
	}
	
	@Test
	public void testMissingDataSource2()
	{
		when(request.getParameter("datasource2")).thenReturn("TotalFotballGoals");
		
		try
		{
			apiServlet.doGet(request, response);
		} catch (ServletException | IOException e)
		{
			fail("Unexpected Exception thrown");
		}
		
		assertEquals("", stringWriter.toString());
	}
	
	@Test
	public void testTwoDataSources()
	{
		when(request.getParameter("datasource1")).thenReturn("TotalFotballGoals");
		when(request.getParameter("datasource2")).thenReturn("TotalFotballGoals");
		
		try
		{
			apiServlet.doGet(request, response);
		} catch (ServletException | IOException e)
		{
			fail("Unexpected Exception thrown");
		}
		
		assertNotEquals("", stringWriter.toString());
	}

}
