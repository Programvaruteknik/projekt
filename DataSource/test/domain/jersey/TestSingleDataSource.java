package domain.jersey;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Response;

import org.junit.Test;

import domain.datasources.DataSource;
import domain.datasources.DataSourceFactory;
import domain.jersey.DataSourceAPI;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestSingleDataSource {

	@Test
	public void testDataSingleDataSource() {
		Map<LocalDate,Double> data = new HashMap<>();
		data.put(LocalDate.parse("2001-01-02"), 2.0);
		data.put(LocalDate.parse("2001-01-01"), 1.0);
		
		DataSource mockSource = mock(DataSource.class);
		when(mockSource.getData()).thenReturn(data);
		DataSourceFactory factory = mock(DataSourceFactory.class);
		when(factory.getDataSource("mockSource")).thenReturn(mockSource);
		
		DataSourceAPI api = new DataSourceAPI();
		api.setFactory(factory);
		String expectedJson = "{\"2001-01-02\":2.0,\"2001-01-01\":1.0}";
		assertEquals(expectedJson, api.getSingleSource("mockSource")
				.getEntity());

		assertEquals(Response.Status.OK.getStatusCode(), api.getSingleSource("mockSource").getStatus());
	}

	@Test
	public void testDataSingleDataSourceDontExists() {
		
		DataSourceFactory factory = mock(DataSourceFactory.class);
		
		DataSourceAPI api = new DataSourceAPI();
		api.setFactory(factory);
		String expectedJson =null;
		Response resp = api.getSingleSource("iDontExists");
		assertEquals(expectedJson, resp.getEntity());
		assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), resp.getStatus());
	}
		
}
