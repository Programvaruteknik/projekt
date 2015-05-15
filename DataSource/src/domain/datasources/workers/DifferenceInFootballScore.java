package domain.datasources.workers;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import domain.api.ApiHandler;
import domain.api.LeagueRegister;
import domain.api.models.everysport.Event;
import domain.api.models.everysport.EverysportEvents;
import domain.api.serialization.JsonParser;
import domain.api.url.UrlFetcher;
import domain.datasources.DataSource;
import domain.datasources.model.MetaData;

public class DifferenceInFootballScore implements DataSource {
	private ApiHandler handler;
	private TreeMap<LocalDate, Double> map;
	private String leagueIDS;
	private String baseURL = "http://api.everysport.com/v1/events?apikey=1769e0fdbeabd60f479b1dcaff03bf5c&league=";

	public DifferenceInFootballScore() {
		map = new TreeMap<LocalDate, Double>();
		this.handler = new ApiHandler(new UrlFetcher(), new JsonParser());
		LeagueRegister league = new LeagueRegister();
		setLeagueIDS(league);
	}

	private void setLeagueIDS(LeagueRegister league) {
		Map<String, Integer> ids = league.getFootballLeagues();
		leagueIDS = league.getIds(ids);
		leagueIDS += "&";
	}

	protected DifferenceInFootballScore(ApiHandler handler) {
		map = new TreeMap<LocalDate, Double>();
		this.handler = handler;
		loadData("fromDate", "toDate");
	}

	private void loadData(String fromDate, String toDate) {
		EverysportEvents events = handler
				.get(baseURL+leagueIDS +"fromDate=" + fromDate + "&toDate=" + toDate + "&limit=5000",
						EverysportEvents.class);
		List<Event> eventList = events.getEvents();
		if(eventList != null)
		{
			for (Event e : eventList) {
				int home = e.getHomeTeamScore();
				int visiting = e.getVisitingTeamScore();
				int difference = Math.abs(home - visiting);
				Double dDifference = new Double(difference);
				map.put(e.getStartDate(), dDifference);
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
		meta.setTitle("Difference in football scores");
		meta.setOwner("Everysport");
		meta.setUrl("http://www.everysport.com");
		meta.setLicense("");
		meta.setUnit("Goals");
		meta.setHasData(!map.isEmpty());
		return meta;

	}

	@Override
	public void downLoadDataSource(String fromDate, String toDate) {
		loadData(fromDate, toDate);	
	}

}
