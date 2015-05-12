package domain.datasources.workers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import domain.api.ApiHandler;
import domain.api.serialization.JsonParser;
import domain.api.url.UrlFetcher;
import domain.datasources.model.MetaData;

public class TestTvSources {
	UrlFetcher fetcher;
	String json = "[{\"airdate\":\"2001-01-01\"}]";
	MetaData meta;
	Map<LocalDate, Double> map;
	ApiHandler handler = new ApiHandler(fetcher, new JsonParser());

	@Before
	public void setup() {
		fetcher = mock(UrlFetcher.class);
		handler = new ApiHandler(fetcher, new JsonParser());
	}

	@Test
	public void testCommunity() {

		when(fetcher.getContent("http://api.tvmaze.com/shows/318/episodes"))
				.thenReturn(json);
		
		TvSourceCommunity source = new TvSourceCommunity(handler);
		map = source.getData();
		
		assertEquals(new Double(1), map.get(LocalDate.parse("2001-01-01")));

		meta = source.getMetaData();
		testMetaData(meta);
	}

	@Test
	public void testDoctor() {

		when(fetcher.getContent("http://api.tvmaze.com/shows/210/episodes"))
				.thenReturn(json);

		TvSourceDoctorWho source = new TvSourceDoctorWho(handler);
		map = source.getData();

		assertEquals(new Double(1), map.get(LocalDate.parse("2001-01-01")));

		meta = source.getMetaData();
		testMetaData(meta);

	}

	public void testMetaData(MetaData meta) {
		assertNotNull(meta);
		assertEquals("TvMaze", meta.getOwner());
		assertEquals("http://www.tvmaze.com", meta.getUrl());
		assertEquals("CC BY-SA 4.0", meta.getLicense());
		assertEquals("Day of the month", meta.getUnit());
	}

}
