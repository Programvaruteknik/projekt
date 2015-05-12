package domain.datasources.workers;

import java.time.LocalDate;
import java.util.Map;
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

	protected BowlingSource(ApiHandler handler) {
		map = new TreeMap<LocalDate, Double>();
		this.handler = handler;
		loadData();
	}

	public BowlingSource() {
		map = new TreeMap<LocalDate, Double>();
		this.handler = new ApiHandler(new UrlFetcher(), new JsonParser());
		loadData();
	}

	private void loadData() {
		EverysportEvents events = handler
				.get("http://api.everysport.com/v1/events?apikey=1769e0fdbeabd60f479b1dcaff03bf5c&league=66975",
						EverysportEvents.class);
		for (Event e : events.getEvents()) {
			Double totalScore = (double) (e.getHomeTeamScore() + e
					.getVisitingTeamScore());
			map.put(e.getStartDate(), totalScore);
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

}
