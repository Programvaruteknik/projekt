package domain.datasources.workers;

import java.time.LocalDate;
import java.util.TreeMap;

import domain.api.ApiHandler;
import domain.api.models.everysport.Event;
import domain.api.models.everysport.EverysportEvents;
import domain.api.models.everysport.Facts;
import domain.api.serialization.JsonParser;
import domain.api.url.UrlFetcher;
import domain.datasources.DataSource;
import domain.datasources.model.MetaData;

public class FootballSpectatorSource implements DataSource {
	private TreeMap<LocalDate, Double> map;
	private ApiHandler handler;


	public FootballSpectatorSource() {
		handler = new ApiHandler(new UrlFetcher(), new JsonParser());
		loadData();
	}

	protected FootballSpectatorSource(ApiHandler handler2) {
		handler = handler2;
		loadData();
	}

	protected void loadData() {
		map = new TreeMap<LocalDate, Double>();
		EverysportEvents events = handler
				.get("http://api.everysport.com/v1/events?apikey=1769e0fdbeabd60f479b1dcaff03bf5c&league=63925",
						EverysportEvents.class);
		for (Event e : events.getEvents()) {
			Facts f = e.getFacts();
			Double d = new Double(f.getSpectators());
			map.put(e.getStartDate(), d);
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

}
