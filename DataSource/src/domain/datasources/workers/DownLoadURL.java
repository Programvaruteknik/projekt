package domain.datasources.workers;

import java.lang.reflect.Type;
import java.util.List;

import domain.api.ApiHandler;
import domain.api.models.everysport.Event;
import domain.api.models.everysport.EverysportEvents;
import domain.api.serialization.JsonParser;
import domain.api.url.UrlFetcher;

public class DownLoadURL
{
	
	private ApiHandler downloader;
	private String URL;
	private Type T;
	
	public DownLoadURL(String url, Type T)
	{
		URL = url;
		this.T = T;
		downloader = new ApiHandler(new UrlFetcher(), new JsonParser());
	}
	
	
	public List<Event> downLoad(String fromDate, String toDate)
	{
//		List<Event> events = downloader.get(URL+ "&fromDate=" + fromDate + "&toDate=" + toDate + "&limit=1000", EverysportEvents.class);
		EverysportEvents events = downloader.get(URL+ "&fromDate=" + fromDate + "&toDate=" + toDate + "&limit=1000", T);
		if(events != null)
			return events.getEvents();
		else
			return null;
	}
	
}
