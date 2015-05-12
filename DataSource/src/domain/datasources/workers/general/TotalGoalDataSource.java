package domain.datasources.workers.general;

import java.time.LocalDate;
import java.util.List;
import java.util.TreeMap;

import domain.api.models.everysport.Event;
import domain.datasources.DataSource;
import domain.datasources.model.MetaData;

public class TotalGoalDataSource implements DataSource
{
	private TreeMap<LocalDate, Double> data;
	private List<Event> events;
	private String title;
	
	
	public TotalGoalDataSource(String title, List<Event> events) 
	{
		this.events = events;
		this.title = title;
		data = new TreeMap<LocalDate, Double>();
		loadData();
	}

	private void loadData() {
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
		meta.setHasData(!data.isEmpty());

		return meta;
	}
}
