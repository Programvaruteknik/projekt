package domain.datasources.workers.general;

import java.time.LocalDate;
import java.util.List;
import java.util.TreeMap;

import domain.api.models.everysport.Event;
import domain.api.models.everysport.EverysportEvents;
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
	public MetaData getMetaData() {
		MetaData meta = new MetaData();
		meta.setLicense("");
		meta.setUrl("http://www.everysport.com/");
		meta.setOwner("Everyport");
		meta.setTitle(title);
		meta.setUnit("Mål");
		
		return meta;
	}

	@Override
	public TreeMap<LocalDate, Double> getData() 
	{
		return data;
	}

	private void loadData(List<Event> ev) {
		data = new TreeMap<LocalDate, Double>();
		if(ev != null)
		{
			for (Event event : ev) {
				
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


	@Override
	public void downLoadDataSource(String fromDate, String toDate) 
	{
		events = loader.downLoad(fromDate, toDate);
		loadData(events);
	}
}
