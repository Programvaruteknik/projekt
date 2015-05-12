package domain.datasources.workers.general;

import java.time.LocalDate;
import java.util.TreeMap;

import domain.api.ApiHandler;
import domain.api.TvApi;
import domain.api.models.tv.SendingDay;
import domain.api.serialization.JsonParser;
import domain.api.url.UrlFetcher;
import domain.datasources.DataSource;
import domain.datasources.model.MetaData;

public class TvSource implements DataSource {
	private TreeMap<LocalDate, Double> data = new TreeMap<>();
	private ApiHandler handler;
	private String id;
	private String name;

	public TvSource() {
		handler = (new ApiHandler(new UrlFetcher(), new JsonParser()));
	}

	public void setName(String name) {
		this.name = name;
	}

	protected TvSource(ApiHandler handlerParam) {
		handler = handlerParam;
	}

	protected void loadData(String id) {
		TvApi api = new TvApi(handler);
		api.setId(id);
		for (SendingDay day : api.getAirDates()) {
			double d = day.getDayOfMonth();
			data.put(day.getAirDate(), d);
		}

	}

	@Override
	public MetaData getMetaData() {
		MetaData meta = new MetaData();
		meta.setLicense("CC BY-SA 4.0");
		meta.setOwner("TvMaze");
		meta.setTitle(name);
		meta.setUrl("http://www.tvmaze.com");
		meta.setUnit("Day of the month");
		meta.setHasData(!data.isEmpty());
		return meta;
	}

	@Override
	public TreeMap<LocalDate, Double> getData() {
		return data;
	}

	@Override
	public TreeMap<LocalDate, Double> getData(String fromDate, String toDate) {
		// TODO Auto-generated method stub
		return null;
	}

}
