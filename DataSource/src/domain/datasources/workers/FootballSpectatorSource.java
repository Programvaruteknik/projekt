package domain.datasources.workers;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import domain.api.ApiHandler;
import domain.api.LeagueRegister;
import domain.api.models.everysport.Event;
import domain.api.models.everysport.EverysportEvents;
import domain.api.models.everysport.Facts;
import domain.api.serialization.JsonParser;
import domain.api.url.UrlFetcher;
import domain.datasources.DataSource;
import domain.datasources.model.MetaData;

public class FootballSpectatorSource implements DataSource {
	private TreeMap<LocalDate, Double> map = new TreeMap<LocalDate, Double>();
	private ApiHandler handler;
	private String leagueIDS;
	private String baseURL = "http://api.everysport.com/v1/events?apikey=1769e0fdbeabd60f479b1dcaff03bf5c&league=";

	public FootballSpectatorSource() {
		handler = new ApiHandler(new UrlFetcher(), new JsonParser());
		LeagueRegister league = new LeagueRegister();
		Map<String, Integer> leagueid = league.getFootballLeagues();
		leagueIDS = league.getIds(leagueid);
	}

	protected FootballSpectatorSource(ApiHandler handler2) {
		handler = handler2;
		loadData("startDate" , "endDate");
	}

	protected void loadData(String fromDate, String toDate) {
		
		EverysportEvents events = handler
				.get(baseURL + leagueIDS + "&fromDate=" + fromDate + "&toDate=" +toDate+ "&limit=5000",
						EverysportEvents.class);
		System.out.println(baseURL + leagueIDS + "&fromDate=" + fromDate + "&toDate=" +toDate+ "&limit=5000");
		if(events != null)
		{
			List<Event> ev = events.getEvents();
			for (Event e : ev) {
				Facts f = e.getFacts();
				Double d = new Double(f.getSpectators());
				map.put(e.getStartDate(), d);
			}			
		}

	}

	@Override
	public TreeMap<LocalDate, Double> getData() {
		return map;
	}

	@Override
	public MetaData getMetaData() {
		MetaData meta = new MetaData();
		meta.setTitle("Football spectators");
		meta.setOwner("Everysport");
		meta.setUrl("http://www.everysport.com");
		meta.setLicense("");
		meta.setUnit("Spectators");
		meta.setHasData(!map.isEmpty());
		return meta;
	}


	@Override
	public void downLoadDataSource(String fromDate,
			String toDate) {
		loadData(fromDate, toDate);
	}
}
