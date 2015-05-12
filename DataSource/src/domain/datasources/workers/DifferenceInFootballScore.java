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

public class DifferenceInFootballScore implements DataSource {
	private ApiHandler handler;
	private TreeMap<LocalDate, Double> map;

	public DifferenceInFootballScore() {
		map = new TreeMap<LocalDate, Double>();
		this.handler = new ApiHandler(new UrlFetcher(), new JsonParser());
		loadData();
	}

	protected DifferenceInFootballScore(ApiHandler handler) {
		map = new TreeMap<LocalDate, Double>();
		this.handler = handler;
		loadData();
	}

	private void loadData() {
		EverysportEvents events = handler
				.get("http://api.everysport.com/v1/events?apikey=1769e0fdbeabd60f479b1dcaff03bf5c&league=63925",
						EverysportEvents.class);
		for (Event e : events.getEvents()) {
			int home = e.getHomeTeamScore();
			int visiting = e.getVisitingTeamScore();
			int difference = Math.abs(home - visiting);
			Double dDifference = new Double(difference);
			map.put(e.getStartDate(), dDifference);
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

}
