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
	private Map<String, Integer> leagueYear = new TreeMap<>();
	private ApiHandler handler;
	private String baseURL = "http://api.everysport.com/v1/events?apikey=1769e0fdbeabd60f479b1dcaff03bf5c&";

	public EverysportApi() {
		handler = new ApiHandler(new UrlFetcher(), new JsonParser());
		prePopulateLeagueYear();
	}

	private void prePopulateLeagueYear() {
		leagueYear.put("2015", 69620);
		leagueYear.put("2014", 63925);
		leagueYear.put("2013", 57973);
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
		leagueYear.put("2000", 2752);
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
