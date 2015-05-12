package domain.datasources.workers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Map;

import org.junit.After;
import org.junit.Test;

import com.google.gson.Gson;

import domain.api.ApiHandler;
import domain.api.models.everysport.Facts;
import domain.api.serialization.JsonParser;
import domain.api.url.UrlFetcher;
import domain.datasources.model.MetaData;

public class TestFootballSpectators {

	@Test
	public void testGetFacts() {
		String json = "{\"spectators\":123}";
		FootballSpectatorSource source = new FootballSpectatorSource();
		Facts facts = new JsonParser().deserialize(json, Facts.class);
		int expectedSpectators = 123;
		assertEquals(expectedSpectators, facts.getSpectators());
	}

	@Test
	public void getData() {
		UrlFetcher fetcher = mock(UrlFetcher.class);
		String json = "{\"events\":[{\"startDate\":2001-01-01,\"facts\":{\"spectators\":123}}]}";
		when(fetcher.getContent("http://api.everysport.com/v1/events?apikey=1769e0fdbeabd60f479b1dcaff03bf5c&league=63925")).thenReturn(json);
		ApiHandler handler = new ApiHandler(fetcher,new JsonParser());
		FootballSpectatorSource source = new FootballSpectatorSource(handler);
				
		Map<LocalDate, Double> map = source.getData();
		double expectedSpectators = 123;
		assertEquals(new Double(expectedSpectators), map.get(LocalDate.parse("2001-01-01")));
		
		MetaData meta = source.getMetaData();
		
		assertNotNull(meta);
		assertEquals("Everysport", meta.getOwner());
		assertEquals("http://www.everysport.com", meta.getUrl());
		assertEquals("", meta.getLicense());
		assertEquals("Spectators", meta.getUnit());
		assertEquals("Football spectators", meta.getName());
		assertEquals(true, meta.containsData());
		
	}
}
