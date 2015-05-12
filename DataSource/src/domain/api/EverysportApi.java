package domain.api;

import java.util.List;

import domain.api.serialization.JsonParser;
import domain.api.url.UrlFetcher;
import domain.api.models.everysport.Event;
import domain.api.models.everysport.EverysportEvents;

public class EverysportApi
{
	ApiHandler handler;
	
	
	
	public EverysportApi()
	{
		handler = new ApiHandler(new UrlFetcher(), new JsonParser());
	}

	public List<Event> getAllsvenskan2014()
	{
		EverysportEvents events = handler.get("http://api.everysport.com/v1/events?apikey=1769e0fdbeabd60f479b1dcaff03bf5c&league=63925", EverysportEvents.class);
		return events.getEvents();
	}
	
	public List<Event> getSHL2014()
	{
		EverysportEvents events = handler.get("http://api.everysport.com/v1/events?apikey=1769e0fdbeabd60f479b1dcaff03bf5c&league=66817", EverysportEvents.class);
		return events.getEvents();
	}
	public List<Event> getBandyDivition1Södra2014()
	{
		EverysportEvents events = handler.get("http://api.everysport.com/v1/events?apikey=1769e0fdbeabd60f479b1dcaff03bf5c&league=67536", EverysportEvents.class);
		return events.getEvents();
	}
	public List<Event> getSuperSerienAmrekanskFotboll()
	{
		EverysportEvents events = handler.get("http://api.everysport.com/v1/events?apikey=1769e0fdbeabd60f479b1dcaff03bf5c&league=71250", EverysportEvents.class);
		return events.getEvents();
	}
}
