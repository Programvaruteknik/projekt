package domain.datasources.workers.general;

import java.time.LocalDate;
import java.util.List;
import java.util.TreeMap;

import domain.api.models.everysport.Event;
import domain.datasources.DataSource;
import domain.datasources.model.MetaData;
import domain.datasources.workers.downLoadURL;

public class TotalGoalDataSource implements DataSource
{
	private TreeMap<LocalDate, Double> data;
	private List<Event> events;
	private String title;
	private downLoadURL loader;
	
	public TotalGoalDataSource(String title, downLoadURL loader) 
	{
		this.loader = loader;
		this.title = title;
	}


	@Override
	public TreeMap<LocalDate, Double> getData() {
		return data;
	}
	
	@Override
	public MetaData getMetaData() {
		MetaData meta = new MetaData();
		meta.setLicense("");
		meta.setUrl("http://www.everysport.com/");
		meta.setOwner("Everyport");
		meta.setTitle(title);
		meta.setUnit("Mål");
		if(data != null)
			meta.setHasData(!data.isEmpty());
		return meta;
	}

	@Override
	public TreeMap<LocalDate, Double> getData(String fromDate, String toDate) {
		if(data == null){
			events = loader.downLoad(fromDate, toDate);
			loadData();
		}
		System.out.println("HEJSAN HOPPSAN: "+data.toString());
		return data;
	}

	private void loadData() {
		data = new TreeMap<LocalDate, Double>();
		for (Event event : events) {
			
			Double totalScore = new Double(event.getHomeTeamScore()
					+ event.getVisitingTeamScore());
			
			LocalDate startDate = event.getStartDate();
			
			
			if (data.get(startDate) != null) {
				data.put(startDate, data.get(startDate)
						+ totalScore);
			} else {
				data.put(startDate, totalScore);
			}
			
		}
	}
}
