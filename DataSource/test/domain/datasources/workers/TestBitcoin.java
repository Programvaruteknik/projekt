package domain.datasources.workers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.TreeMap;

import org.junit.Before;
import org.junit.Test;

import domain.api.ApiHandler;
import domain.api.serialization.JsonParser;
import domain.api.url.UrlFetcher;
import domain.datasources.model.MetaData;

public class TestBitcoin {
	UrlFetcher fetcher;
	String json;
	ApiHandler handler;
	TreeMap<LocalDate, Double> map;
	MetaData metaData;

	@Before
	public void setup() {
		fetcher = mock(UrlFetcher.class);
		handler = new ApiHandler(fetcher, new JsonParser());
	}

	@Test
	public void testBitCoinChange() {
		json = "{\"data\":[{\"date\":\"2001-01-01\",\"change\":123}]}";
		when(fetcher.getContent("http://api.cbix.ca/v1/history?limit=100"))
				.thenReturn(json);

		BitCoinChangeSource source = new BitCoinChangeSource(handler);
		source.downLoadDataSource("1990-01-01", "2002-01-01");
		
		map = source.getData();
		
		assertEquals(new Double(123), map.get(LocalDate.parse("2001-01-01")));
		metaData = source.getMetaData();
		testMetaData(metaData);
		assertEquals("Change In Canadian Bitcoin Index", metaData.getName());
	}


	@Test
	public void testBitCoinOpen() {
		json = "{\"data\":[{\"date\":\"2001-01-01\",\"open\":123}]}";
		when(fetcher.getContent("http://api.cbix.ca/v1/history?limit=100"))
				.thenReturn(json);

		BitCoinOpenSource source = new BitCoinOpenSource(handler);
		source.downLoadDataSource("1990-01-01", "2002-01-01");
		map = source.getData();
		assertEquals(new Double(123), map.get(LocalDate.parse("2001-01-01")));
		metaData = source.getMetaData();

		assertEquals("Open values In Canadian Bitcoin Index",
				metaData.getName());

		testMetaData(metaData);

	}

	@Test
	public void testBitcoinVolume() {
		json = "{\"data\":[{\"date\":\"2001-01-01\",\"volume\":123}]}";
		when(fetcher.getContent("http://api.cbix.ca/v1/history?limit=100"))
				.thenReturn(json);

		BitCoinVolume source = new BitCoinVolume(handler);
		source.downLoadDataSource("1990-01-01", "2002-01-01");
		map = source.getData();
		assertEquals(new Double(123), map.get(LocalDate.parse("2001-01-01")));
		metaData = source.getMetaData();
		testMetaData(metaData);
		assertEquals("Bitcoin volume", metaData.getName());
	}

	public void testMetaData(MetaData data) {
		assertEquals("", data.getLicense());
		assertEquals("cbix", data.getOwner());
		assertEquals("https://www.cbix.ca", data.getUrl());
		assertEquals(true, data.containsData());
		assertEquals("BTC", data.getUnit());

	}
}
