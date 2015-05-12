package domain.datasources.workers;

import java.time.LocalDate;
import java.util.TreeMap;

import domain.api.ApiHandler;
import domain.api.TvApiDoctorWho;
import domain.api.models.tv.SendingDay;
import domain.api.serialization.JsonParser;
import domain.api.url.UrlFetcher;
import domain.datasources.DataSource;
import domain.datasources.model.MetaData;

public class TvSourceDoctorWho implements DataSource {
	private TreeMap<LocalDate, Double> data = new TreeMap<>();
	private ApiHandler handler;

	public TvSourceDoctorWho() {
		handler = new ApiHandler(new UrlFetcher(), new JsonParser());
		loadData();
	}

	protected TvSourceDoctorWho(ApiHandler handlerParam) {
		handler = handlerParam;
		loadData();
		
	}

	private void loadData() {
		TvApiDoctorWho api = new TvApiDoctorWho(handler);

		for (SendingDay day : api.getAirDates()) {
			double d = day.getDayOfMonth();
			data.put(day.getAirDate(), d);
		}
	}

	@Override
	public TreeMap<LocalDate, Double> getData() {
		return data;
	}

	@Override
	public MetaData getMetaData() {
		MetaData meta = new MetaData();
		meta.setLicense("CC BY-SA 4.0");
		meta.setOwner("TvMaze");
		meta.setUrl("http://www.tvmaze.com");
		meta.setTitle("Airdate for Doctor Who");
		meta.setUnit("Day of the month");
		meta.setHasData(!data.isEmpty());
		return meta;
	}
}
