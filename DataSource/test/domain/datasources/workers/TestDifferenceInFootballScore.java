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

public class TestDifferenceInFootballScore {

	@Test
	public void test() {		
		
		UrlFetcher fetcher = mock(UrlFetcher.class);
		String json = "{\"events\":[{\"startDate\":\"2001-01-01\",\"visitingTeamScore\":0,\"homeTeamScore\":2}]}";
		when(fetcher.getContent("http://api.everysport.com/v1/events?apikey=1769e0fdbeabd60f479b1dcaff03bf5c&league=nullfromDate=1999-01-01&toDate=2002-01-01&limit=5000")).thenReturn(json);
		ApiHandler handler = new ApiHandler(fetcher, new JsonParser());
		
		DifferenceInFootballScore source = new DifferenceInFootballScore(handler);
		source.downLoadDataSource("1999-01-01", "2002-01-01");
		TreeMap<LocalDate,Double> map = source.getData();
		double expectedDifference = 2;
		assertEquals(expectedDifference, map.get(LocalDate.parse("2001-01-01")).doubleValue(),0.01);
		MetaData meta = source.getMetaData();
		
		String expectedName = "Difference in football scores";
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
	}

}
