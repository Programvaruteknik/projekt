package domain.servlets;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;

public class ApiServletTest
{

	HttpServletRequest request;
	HttpServletResponse response;
	StringWriter resultingWriter = new StringWriter();
	ApiServlet apiServlet;

	@Before
	public void setupTest()
	{
		apiServlet = new ApiServlet();
		request = mock(HttpServletRequest.class);
		response = mock(HttpServletResponse.class);
		try
		{
			when(response.getWriter()).thenReturn(new PrintWriter(resultingWriter));
		} catch (IOException ex)
		{
			fail("Unexpected Exception thrown");
		}
	}
	
	@Test
	public void testFactoryInServlet()
	{
		
	}

}
