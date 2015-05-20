package domain.api;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import domain.api.models.everysport.Event;
import domain.api.models.everysport.EverysportEvents;
import domain.api.serialization.JsonParser;
import domain.api.url.UrlFetcher;
import domain.datasources.workers.DownLoadURL;

public class EverysportApi {
	private ApiHandler handler;
	private String baseURL = "http://api.everysport.com/v1/events?apikey=1769e0fdbeabd60f479b1dcaff03bf5c&";
	private LeagueRegister register;
	private Map<String, Integer> map;
	private String league;

	public EverysportApi() {
		handler = new ApiHandler(new UrlFetcher(), new JsonParser());
		register = new LeagueRegister();
	}


	public DownLoadURL getAllsvenskan() {
		map = register.getFootballLeagues();
		league = register.getIds(map);
		
		DownLoadURL d = new DownLoadURL(baseURL +"league="+ league, EverysportEvents.class);
		return d;
	}

	public DownLoadURL getSHL() {
		map = register.getSHL();
		league = register.getIds(map);
		DownLoadURL d = new DownLoadURL(baseURL +"league="+ league, EverysportEvents.class);
		return d;
	}

	public DownLoadURL getBandyDivition1Södra() {
		map = register.getBandy();
		league = register.getIds(map);

		DownLoadURL d = new DownLoadURL(baseURL +"league="+ league, EverysportEvents.class);
		return d;
	}

	public DownLoadURL getSuperSerienAmrekanskFotboll() {
		map = register.getAmericanFootball();
		league = register.getIds(map);
		DownLoadURL d = new DownLoadURL(baseURL +"league="+ league, EverysportEvents.class);
		return d;
	}

	public DownLoadURL getBasketliganGrundserien() {
		map = register.getBasket();
		league = register.getIds(map);
		
		DownLoadURL d = new DownLoadURL(baseURL +"league="+ league, EverysportEvents.class);
		return d;

	}

}
