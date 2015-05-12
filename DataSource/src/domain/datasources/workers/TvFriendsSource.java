package domain.datasources.workers;

import java.time.LocalDate;
import java.util.List;
import java.util.TreeMap;

import domain.api.ApiHandler;
import domain.api.TvApiFriends;
import domain.api.models.tv.SendingDay;
import domain.datasources.DataSource;
import domain.datasources.model.MetaData;

public class TvFriendsSource implements DataSource {
	private ApiHandler handler;
	private TreeMap<LocalDate,Double> map;
	
	protected TvFriendsSource(ApiHandler handler) {
		this.handler = handler;
	}

	@Override
	public TreeMap<LocalDate, Double> getData() {
		map = new TreeMap<LocalDate, Double>();
		TvApiFriends api = new TvApiFriends(handler);
		List<SendingDay> list = api.getList();
		
		for(SendingDay day : list){
			Double dayOfMonth = new Double(day.getDayOfMonth());
			map.put(day.getAirDate(),dayOfMonth);
		}
		
		return map;
	}

	@Override
	public MetaData getMetaData() {
		MetaData meta = new MetaData();
		meta.setTitle("Airdate for Friends");
		meta.setOwner("TvMaze");
		meta.setLicense("CC BY-SA 4.0");
		meta.setUrl("http://www.tvmaze.com");
		meta.setHasData(!map.isEmpty());
		meta.setUnit("Day of the month");
		return meta;
	}

}
