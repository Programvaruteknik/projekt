package domain.datasources.workers;

import java.time.LocalDate;
import java.util.TreeMap;

import domain.api.ApiHandler;
import domain.api.models.everysport.Event;
import domain.api.models.everysport.EverysportEvents;
import domain.api.serialization.JsonParser;
import domain.api.url.UrlFetcher;
import domain.datasources.DataSource;
import domain.datasources.model.MetaData;

public class BowlingSource implements DataSource {
	private ApiHandler handler;
	private TreeMap<LocalDate, Double> map;
	private String baseURL = "http://api.everysport.com/v1/events?apikey=1769e0fdbeabd60f479b1dcaff03bf5c&league=66975,60851,55579,48764,42317,35714,30972,25623,19080,9929,8619&";

	protected BowlingSource(ApiHandler handler) {
		map = new TreeMap<LocalDate, Double>();
		this.handler = handler;
	}

	public BowlingSource() {
		map = new TreeMap<LocalDate, Double>();
		this.handler = new ApiHandler(new UrlFetcher(), new JsonParser());
		
	}

	private void loadData(String fromDate, String toDate) {
		EverysportEvents events = handler
				.get(baseURL + "fromDate=" + fromDate + "&toDate=" + toDate +"&limit=1000",
						EverysportEvents.class);
		if(events != null)
		{
			for (Event e : events.getEvents()) {
				Double totalScore = (double) (e.getHomeTeamScore() + e
						.getVisitingTeamScore());
				map.put(e.getStartDate(), totalScore);
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
		meta.setTitle("Scores in bowling");
		meta.setLicense("");
		meta.setUnit("Goals");
		meta.setUrl("http://www.everysport.com");
		meta.setOwner("Everysport");
		meta.setHasData(!map.isEmpty());
		return meta;
	}

	@Override
	public void downLoadDataSource(String fromDate, String toDate) {
		loadData(fromDate, toDate);
		
	}

	

}
