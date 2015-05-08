package domain.datasources.workers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.TreeMap;

import org.junit.After;
import org.junit.Test;

import sun.util.resources.LocaleData;
import domain.api.ApiHandler;
import domain.api.serialization.JsonParser;
import domain.api.url.UrlFetcher;
import domain.datasources.model.MetaData;

public class TestBitcoin {

	@Test
	public void testBitCoinChange() {
		UrlFetcher fetcher = mock(UrlFetcher.class);
		String json = "{\"data\":[{\"date\":\"2001-01-01\",\"change\":123}]}";
		when(fetcher.getContent("http://api.cbix.ca/v1/history?limit=100")).thenReturn(json);
		ApiHandler handler = new ApiHandler(fetcher, new JsonParser());
		BitCoinChangeSource source = new BitCoinChangeSource(handler);
		TreeMap<LocalDate, Double> map = source.getData();
		
		assertEquals(new Double(123), map.get(LocalDate.parse("2001-01-01")));
		MetaData data = source.getMetaData();
		
		assertEquals("",data.getLicense());
		assertEquals("cbix.ca",data.getOwner());
		assertEquals("https://www.cbix.ca",data.getUrl());
		assertEquals("Change In Canadian Bitcoin Index",data.getName());
		assertEquals("BTC",data.getUnit());
		
	}
	
	@Test
	public void testBitCoinOpen() {
		UrlFetcher fetcher = mock(UrlFetcher.class);
		String json = "{\"data\":[{\"date\":\"2001-01-01\",\"open\":123}]}";
		when(fetcher.getContent("http://api.cbix.ca/v1/history?limit=100")).thenReturn(json);
		ApiHandler handler = new ApiHandler(fetcher, new JsonParser());
		BitCoinOpenSource source = new BitCoinOpenSource(handler);
		TreeMap<LocalDate, Double> map = source.getData();
		
		assertEquals(new Double(123), map.get(LocalDate.parse("2001-01-01")));
		MetaData data = source.getMetaData();
		
		assertEquals("",data.getLicense());
		assertEquals("cbix.ca",data.getOwner());
		assertEquals("https://www.cbix.ca",data.getUrl());
		assertEquals("Open values In Canadian Bitcoin Index",data.getName());
		assertEquals("BTC",data.getUnit());
		
	}
}
