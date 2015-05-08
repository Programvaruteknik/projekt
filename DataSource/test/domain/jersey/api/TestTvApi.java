package domain.jersey.api;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.Test;


import domain.api.ApiHandler;
import domain.api.TvApiCommunity;
import domain.api.models.tv.SendingDay;
import domain.api.serialization.JsonParser;
import domain.api.url.UrlFetcher;

public class TestTvApi {

	@Test
	public void testGetAirDates() {
		String json = "[{\"airdate\":\"2001-01-01\"}]";
		UrlFetcher fetcher = mock(UrlFetcher.class);
		when(fetcher.getContent("http://api.tvmaze.com/shows/812/episodes")).thenReturn(json);

		ApiHandler handler = new ApiHandler(fetcher, new JsonParser());

		TvApiCommunity api = new TvApiCommunity(handler);

		List<SendingDay> list = api.getAirDates();

		int expectedListSize = 1;
		int expectedFirstAirDay = 1;

		assertEquals(expectedListSize, list.size());
		assertEquals(expectedFirstAirDay, list.get(0).getDayOfMonth());

	}

}
