package domain.datasources.workers.general;

import java.time.LocalDate;
import java.util.List;
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

	public TvSource(String id)
	{
		handler = (new ApiHandler(new UrlFetcher(), new JsonParser()));
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	protected TvSource(ApiHandler handlerParam, String id) {
		handler = handlerParam;
		this.id = id;
	}

	protected void loadData() {
		TvApi api = new TvApi(handler);
		api.setId(id);
		List<SendingDay> sending = api.getAirDates();
		if(sending != null)
		{
			for (SendingDay day : sending) {
				double d = day.getDayOfMonth();
				data.put(day.getAirDate(), d);
			}			
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
		
		return meta;
	}

	@Override
	public TreeMap<LocalDate, Double> getData() {
		return data;
	}

	@Override
	public void downLoadDataSource(String fromDate, String toDate) {
		loadData();
		
	}

}
