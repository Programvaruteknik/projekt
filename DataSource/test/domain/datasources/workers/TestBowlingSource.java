package domain.datasources.workers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.TreeMap;

import org.junit.After;
import org.junit.Test;

import domain.api.ApiHandler;
import domain.api.serialization.JsonParser;
import domain.api.url.UrlFetcher;
import domain.datasources.model.MetaData;

public class TestBowlingSource {

	@Test
	public void testGetSore() {
		
		UrlFetcher fetcher = mock(UrlFetcher.class);
		String json ="{\"events\":[{\"startDate\":\"2001-01-01\",\"homeTeamScore\":5,\"visitingTeamScore\":5}]}";
		
		when(fetcher.getContent("http://api.everysport.com/v1/events?apikey=1769e0fdbeabd60f479b1dcaff03bf5c&league=66975")).thenReturn(json);
		ApiHandler handler = new ApiHandler(fetcher, new JsonParser());
		BowlingSource source = new BowlingSource(handler);
		source.downLoadDataSource("2000-01-01","2002-01-01");
		
		TreeMap<LocalDate,Double> map = source.getData();
		double expectedScore = 10d;
		assertEquals(expectedScore, map.get(LocalDate.parse("2001-01-01")),0.01);
		MetaData meta = source.getMetaData();
		
		String expectedName = "Scores in bowling";
		String expectedLicence = "";
		String expectedUnit ="Goals";
		String expectedUrl="http://www.everysport.com";
		String expectedOwner="Everysport";
		
		assertNotNull(meta);
		assertEquals(true, meta.containsData());
		assertEquals(expectedName, meta.getName());
		assertEquals(expectedLicence, meta.getLicense());
		assertEquals(expectedUrl,meta.getUrl());
		assertEquals(expectedUnit, meta.getUnit());
		assertEquals(expectedOwner, meta.getOwner());
		assertEquals(true,meta.containsData());
	}
	
}
