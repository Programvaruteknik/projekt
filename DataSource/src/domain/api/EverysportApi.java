package domain.api;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import domain.api.models.everysport.Event;
import domain.api.models.everysport.EverysportEvents;
import domain.api.serialization.JsonParser;
import domain.api.url.UrlFetcher;
import domain.datasources.workers.downLoadURL;

public class EverysportApi {
	private ApiHandler handler;
	private String baseURL = "http://api.everysport.com/v1/events?apikey=1769e0fdbeabd60f479b1dcaff03bf5c&";

	public EverysportApi() {
		handler = new ApiHandler(new UrlFetcher(), new JsonParser());
	}

	LeagueRegister register = new LeagueRegister();
	Map<String, Integer> map;
	String league;

	public downLoadURL getAllsvenskan() {
		map = register.getFootballLeagues();
		league = register.getIds(map);
		
		downLoadURL d = new downLoadURL(baseURL + league, EverysportEvents.class);
		return d;
	}

	public downLoadURL getSHL() {
		map = register.getSHL();
		league = register.getIds(map);

		downLoadURL d = new downLoadURL(baseURL + league, EverysportEvents.class);
		return d;
	}

	public downLoadURL getBandyDivition1Södra() {
		map = register.getBandy();
		league = register.getIds(map);

		downLoadURL d = new downLoadURL(baseURL +"league="+ league, EverysportEvents.class);
		System.out.println("TA DENNA URL: "+baseURL + league);
		return d;
	}

	public downLoadURL getSuperSerienAmrekanskFotboll() {
		map = register.getAmericanFootball();
		league = register.getIds(map);

		downLoadURL d = new downLoadURL(baseURL + league, EverysportEvents.class);
		return d;
	}

	public downLoadURL getBasketliganGrundserien() {
		map = register.getBasket();
		league = register.getIds(map);
		
		downLoadURL d = new downLoadURL(baseURL + league, EverysportEvents.class);
		return d;

	}

}
