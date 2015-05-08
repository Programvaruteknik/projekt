package domain.datasources.workers;

import java.time.LocalDate;
import java.util.TreeMap;

import domain.api.ApiHandler;
import domain.api.TvApiCommunity;
import domain.api.models.tv.SendingDay;
import domain.api.serialization.JsonParser;
import domain.api.url.UrlFetcher;
import domain.datasources.DataSource;
import domain.datasources.model.MetaData;

public class TvSourceCommunity implements DataSource {
	private TreeMap<LocalDate, Double> data = new TreeMap<>();
	private ApiHandler handler;

	public TvSourceCommunity() {
		handler = (new ApiHandler(new UrlFetcher(), new JsonParser()));
	}

	protected TvSourceCommunity(ApiHandler handlerParam) {
		handler = handlerParam;
	}

	@Override
	public TreeMap<LocalDate, Double> getData() {
		TvApiCommunity api = new TvApiCommunity(handler);
		
		for (SendingDay day : api.getAirDates()) {
			double d = day.getDayOfMonth();
			data.put(day.getAirDate(), d);
		}

		return data;
	}

	@Override
	public MetaData getMetaData() {
		MetaData meta = new MetaData();
		meta.setLicense("CC BY-SA 4.0");
		meta.setOwner("TvMaze");
		meta.setUrl("http://www.tvmaze.com");
		meta.setTitle("Airdate for Community");
		meta.setUnit("Day of the month");
		meta.setHasData(!data.isEmpty());
		return meta;
	}

}
