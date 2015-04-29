package domain.jersey;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import javax.ws.rs.core.Response;

import org.junit.Test;

import domain.api.serialization.JsonParser;
import domain.datasources.DataSource;
import domain.datasources.DataSourceFactory;
import domain.datasources.model.MetaData;

public class TestGetMetadata {

	@Test
	public void testGetMetaData() {
		String expectedUrl = "url";
		String expectedOwner = "owner";
		String expectedLicense = "license";

		DataSource sc = mock(DataSource.class);
		MetaData metaMock = mock(MetaData.class);

		when(metaMock.getUrl()).thenReturn("url");
		when(metaMock.getOwner()).thenReturn("owner");
		when(metaMock.getLicense()).thenReturn("license");
		
		when(sc.getMetaData()).thenReturn(metaMock);

		DataSourceFactory factory = mock(DataSourceFactory.class);
		when(factory.getDataSource("metaSource")).thenReturn(sc);

		DataSourceAPI api = new DataSourceAPI();
		api.setFactory(factory);
		
		MetaData meta = api.getMetaData("metaSource");
		assertNotNull(meta);
		assertEquals(expectedUrl, meta.getUrl());
		assertEquals(expectedOwner, meta.getOwner());
		assertEquals(expectedLicense, meta.getLicense());

	}
	
	@Test
	public void testGetMetaDataList(){
		DataSourceAPI api = new DataSourceAPI();
		DataSourceFactory factory = mock(DataSourceFactory.class);
		
		TreeMap<LocalDate, Double> map1 = new TreeMap<LocalDate, Double>();
		TreeMap<LocalDate, Double> map2 = new TreeMap<LocalDate, Double>();

		DataSource sc1Mock = mock(DataSource.class);
		DataSource sc2Mock = mock(DataSource.class);
		
		map1.put(LocalDate.parse("2001-01-01"), 1d);
		map2.put(LocalDate.parse("2001-01-01"), 1d);
		when(sc1Mock.getData()).thenReturn(map1);
		when(sc2Mock.getData()).thenReturn(map2);
		
		when(factory.getDataSource("sc1")).thenReturn(sc1Mock);
		when(factory.getDataSource("sc2")).thenReturn(sc2Mock);
		
		api.setFactory(factory);
		Response resp = api.getSources("['sc1','sc2']");
		String json = (String) resp.getEntity();
		Map<String,Object> map = new JsonParser().deserialize(json, Map.class);
		ArrayList<Object> metaData = (ArrayList<Object>) map.get("metaDataList");
		int expectedSize = 2;
		assertNotNull(metaData);
		assertEquals(expectedSize,metaData.size());
	}

}
