package domain.datasources.workers;

import java.lang.reflect.Type;
import java.util.List;

import domain.api.ApiHandler;
import domain.api.models.everysport.Event;
import domain.api.models.everysport.EverysportEvents;
import domain.api.serialization.JsonParser;
import domain.api.url.UrlFetcher;

public class downLoadURL
{
	private String baseURL = "http://api.everysport.com/v1/events?apikey=1769e0fdbeabd60f479b1dcaff03bf5c&";
	private ApiHandler downloader;
	private String URL;
	private Type T;
	
	public downLoadURL(String url, Type T)
	{
		URL = url;
		this.T = T;
		downloader = new ApiHandler(new UrlFetcher(), new JsonParser());
	}
	
	
	public List<Event> downLoad(String fromDate, String toDate)
	{
		System.out.println("Hela URL för bandy VM -----> " +URL+ "&fromDate=" + fromDate + "&toDate=" + toDate + "&limit=5000");
//		List<Event> events = downloader.get(URL+ "&fromDate=" + fromDate + "&toDate=" + toDate + "&limit=1000", EverysportEvents.class);
		EverysportEvents events = downloader.get(URL+ "&fromDate=" + fromDate + "&toDate=" + toDate + "&limit=1000", T);
		if(events != null)
			return events.getEvents();
		else
			return null;
	}
	
}
