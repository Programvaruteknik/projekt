package domain.api;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import domain.api.models.everysport.Event;
import domain.api.models.everysport.EverysportEvents;
import domain.api.serialization.JsonParser;
import domain.api.url.UrlFetcher;

public class EverysportApi
{
	private Map<String, Integer> leagueYear = new TreeMap<>();
	ApiHandler handler;
	private String baseURL = "http://api.everysport.com/v1/events?apikey=1769e0fdbeabd60f479b1dcaff03bf5c&";
	
	
	
	public EverysportApi()
	{
		handler = new ApiHandler(new UrlFetcher(), new JsonParser());
		prePopulateLeagueYear();
	}
	
	private void prePopulateLeagueYear() 
	{
		leagueYear.put("2015", 69620);
		leagueYear.put("2014", 63925);
		leagueYear.put("2013",57973 );
		leagueYear.put("2012", 51603);
		leagueYear.put("2011", 44165);
		leagueYear.put("2010", 38686);
		leagueYear.put("2009", 32911);
		leagueYear.put("2008", 27773);
		leagueYear.put("2007", 21511);
		leagueYear.put("2006", 10581);
		leagueYear.put("2005", 9402);
		leagueYear.put("2004", 8051);
		leagueYear.put("2003", 6469);
		leagueYear.put("2002", 5307);
		leagueYear.put("2001", 3863);
		leagueYear.put("2000" ,2752);
		
	}

	public List<Event> getEvents(String fromDate, String toDate)
	{
		
		String leagueRange = getYear(fromDate, toDate);
		System.out.println("hejsan");
		String league = "league=" + leagueRange; 
		String fDate = "fromDate=" + fromDate;
		String tDate = "toDate=" + toDate;
		System.out.println(baseURL + league + fDate +"&" + tDate);
		EverysportEvents events = handler.get(baseURL + league + fDate +"&" + tDate+ "&limit=1000", EverysportEvents.class);
		System.out.println(events.toString());
		return events.getEvents();
	}
	
	private String getYear(String fromDate, String toDate) 
	{
		LocalDate fDate = LocalDate.parse(fromDate);
		LocalDate tDate = LocalDate.parse(toDate);
		String leagueYears = getRangeYears(fDate, (tDate.getYear() - fDate.getYear()));
		return ""+leagueYears+"&";
		
	}
	
	private String getRangeYears(LocalDate fDate, int i) 
	{
		String league = "";
		int firstYear = fDate.getYear();
		System.out.println(firstYear);
		String firstY = "" + firstYear;
		league += leagueYear.get(firstY);
		for(int t = 1 ; t <= i ; t++)
		{
			String getYear = "" + (firstYear + t);
			System.out.println(getYear);
			league += "," +leagueYear.get(getYear);
		}
		
		return league;
	}
}
