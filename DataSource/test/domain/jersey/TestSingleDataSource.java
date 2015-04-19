package domain.jersey;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;

import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;

import domain.datasources.DataSource;
import domain.datasources.DataSourceFactory;

public class TestSingleDataSource {
	TreeMap<LocalDate, Double> data = new TreeMap<>();

	@Before
	public void setup() {
		data = new TreeMap<>();
	}

	@Test
	public void testDataSingleDataSource() {

		data.put(LocalDate.parse("2001-01-02"), 2.0);
		data.put(LocalDate.parse("2001-01-01"), 1.0);

		DataSource mockSource = mock(DataSource.class);
		when(mockSource.getData()).thenReturn(data);
		DataSourceFactory factory = mock(DataSourceFactory.class);
		when(factory.getDataSource("mockSource")).thenReturn(mockSource);

		DataSourceAPI api = new DataSourceAPI();
		api.setFactory(factory);
		String expectedJson = "{\"2001-01-01\":1.0,\"2001-01-02\":2.0}";
		assertEquals(expectedJson, api.getSources("mockSource")
				.getEntity());

		assertEquals(Response.Status.OK.getStatusCode(),
				api.getSources("mockSource").getStatus());
	}

	@Test
	public void testDataSingleDataSourceDontExists() {

		DataSourceFactory factory = mock(DataSourceFactory.class);

		DataSourceAPI api = new DataSourceAPI();
		api.setFactory(factory);
		String expectedJson = null;
		Response resp = api.getSources("iDontExists");
		assertEquals(expectedJson, resp.getEntity());
		assertEquals(Response.Status.BAD_REQUEST.getStatusCode(),
				resp.getStatus());
	}

	@Test
	public void testGetMissingDates() {
		TreeMap<LocalDate, Double> data = new TreeMap<>();

		data.put(LocalDate.parse("2001-01-04"), 4.0);

		data.put(LocalDate.parse("2001-01-02"), 2.0);
		data.put(LocalDate.parse("2001-01-01"), 1.0);

		DataSource mockSource = mock(DataSource.class);
		when(mockSource.getData()).thenReturn(data);
		DataSourceFactory mockFactory = mock(DataSourceFactory.class);
		when(mockFactory.getDataSource("mockSource")).thenReturn(mockSource);
		DataSourceAPI api = new DataSourceAPI();

		api.setFactory(mockFactory);
		Response resp = api.getSources("mockSource");
		String expectedResp = "{\"2001-01-01\":1.0,\"2001-01-02\":2.0,\"2001-01-03\":null,\"2001-01-04\":4.0}";
		assertEquals(expectedResp, resp.getEntity());
	}
}
