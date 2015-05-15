package domain.jersey;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import javax.ws.rs.core.Response;

import jdk.nashorn.internal.runtime.regexp.JoniRegExp.Factory;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

import domain.api.serialization.JsonParser;
import domain.datasources.DataSource;
import domain.datasources.DataSourceFactory;
import domain.datasources.model.MetaData;
import domain.datasources.workers.SunAltitudeAtNoon;

public class TestSingleDataSource {
	TreeMap<LocalDate, Double> data = new TreeMap<>();
	DataSourceFactory factory;
	DataSourceAPI api;
	Response resp;
	Map<String, Object> jsonMap;
	ArrayList<String> headerList;
	String entity;
	DataSource mockSource;
	Map<String, ArrayList<Double>> list;

	@Before
	public void setup() {
		api = new DataSourceAPI();
		data = new TreeMap<>();
		factory = mock(DataSourceFactory.class);
	}

	@Test
	public void testDataSingleDataSource() {

		data.put(LocalDate.parse("2001-01-02"), 2.0);
		data.put(LocalDate.parse("2001-01-01"), 1.0);

		mockSource = mock(DataSource.class);
		when(mockSource.getData()).thenReturn(data);
		when(factory.getDataSource("mockSource")).thenReturn(mockSource);

		api.setFactory(factory);
		entity = (String) api.getSources("['mockSource']", "", "").getEntity();

		Map<String, Object> map = new JsonParser().deserialize(entity,
				Map.class);

		headerList = (ArrayList<String>) map.get("header");
		list = (Map<String, ArrayList<Double>>) map.get("data");

		assertEquals(2, headerList.size());

		assertEquals(1, list.get("2001-01-01").size());
		assertEquals(1, list.get("2001-01-02").size());

		assertEquals(1d, list.get("2001-01-01").get(0), 0.01);
		assertEquals(2d, list.get("2001-01-02").get(0), 0.01);

	}

	@Test
	public void testDataSingleDataSourceDontExists() {

		api.setFactory(factory);
		String expectedJson = null;
		resp = api.getSources("['iDontExists']", "", "");

		assertEquals(expectedJson, resp.getEntity());
		assertEquals(Response.Status.BAD_REQUEST.getStatusCode(),
				resp.getStatus());
	}

	DataSource x = new DataSource() {
		TreeMap<LocalDate, Double> map = new TreeMap<LocalDate, Double>();

		@Override
		public MetaData getMetaData() {
			MetaData meta = new MetaData();
			meta.setTitle("xSource");
			meta.setUnit("xUnit");
			return meta;
		}

		@Override
		public TreeMap<LocalDate, Double> getData() {
			return map;
		}

		@Override
		public void downLoadDataSource(String fromDate, String toDate) {
			map.put(LocalDate.parse("2001-01-01"), 1d);
		}
	};

	DataSource y = new DataSource() {
		TreeMap<LocalDate, Double> map = new TreeMap<LocalDate, Double>();

		@Override
		public MetaData getMetaData() {
			MetaData meta = new MetaData();
			meta.setTitle("ySource");
			meta.setUnit("yUnit");
			return meta;
		}

		@Override
		public TreeMap<LocalDate, Double> getData() {
			return map;
		}

		@Override
		public void downLoadDataSource(String fromDate, String toDate) {
			map.put(LocalDate.parse("2001-01-01"), 1d);
		}
	};

	@Test
	public void testGetMetaData() {
		String expectedName1 = "xSource";
		String expectedName2 = "ySource";
		x.downLoadDataSource("", "");
		y.downLoadDataSource("", "");

		when(factory.getDataSource("sc1")).thenReturn(x);
		when(factory.getDataSource("sc2")).thenReturn(y);
		api.setFactory(factory);
		String modification = "[{\"dataSourceName\":\"name\",\"year\":1,\"month\":1,\"days\":1}]";

		resp = api
				.getCorrelationData("sc1", "sc2", "DAY", modification, "", "");

		String entityContent = (String) resp.getEntity();

		jsonMap = new Gson().fromJson(entityContent, Map.class);

		Map<String, Object> metaDataX = (Map<String, Object>) jsonMap
				.get("xMeta");

		Map<String, Object> metaDataY = (Map<String, Object>) jsonMap
				.get("yMeta");

		assertEquals(expectedName1, metaDataX.get("title"));
		assertEquals(expectedName2, metaDataY.get("title"));
		assertEquals("xUnit", metaDataX.get("unit"));
		assertEquals("yUnit", metaDataY.get("unit"));
	}

	@Test
	public void testGetMissingDates() {
		data.put(LocalDate.parse("2001-01-04"), 4.0);
		data.put(LocalDate.parse("2001-01-02"), 2.0);
		data.put(LocalDate.parse("2001-01-01"), 1.0);

		mockSource = mock(DataSource.class);
		when(mockSource.getData()).thenReturn(data);
		when(factory.getDataSource("mockSource")).thenReturn(mockSource);
		api.setFactory(factory);

		String fromDate = "2001-01-01";
		String toDate = "2003-03-03";

		resp = api.getSources("['mockSource']", fromDate, toDate);
		entity = (String) resp.getEntity();

		jsonMap = new Gson().fromJson(entity, Map.class);

		headerList = (ArrayList<String>) jsonMap.get("header");
		list = (Map<String, ArrayList<Double>>) jsonMap.get("data");

		assertEquals(1, list.get("2001-01-01").size());
		assertEquals(1, list.get("2001-01-02").size());
		assertEquals(1, list.get("2001-01-03").size());

		assertEquals(1d, list.get("2001-01-01").get(0), 0.01);
		assertEquals(2d, list.get("2001-01-02").get(0), 0.01);
		assertNull(list.get("2001-01-03").get(0));
		assertEquals(4d, list.get("2001-01-04").get(0), 0.01);

	}
}
