package domain.datasources.workers;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import domain.api.EverysportApi;
import domain.api.models.everysport.Event;
import domain.datasources.DataSource;
import domain.datasources.model.MetaData;

public class TotalFotballGoals implements DataSource
{
	private TreeMap<LocalDate, Double> data;
	
	public TotalFotballGoals()
	{
		data = new TreeMap<LocalDate, Double>();
		List<Event> events = new EverysportApi().getEvents();
		for (Event event : events)
		{
			Double totalScore = new Double(event.getHomeTeamScore() + event.getVisitingTeamScore());
			if(data.get(event.getStartDate()) != null)
			{
				data.put(event.getStartDate(), data.get(event.getStartDate()) + totalScore);
			}
			else
			{
				data.put(event.getStartDate(), totalScore);				
			}
		}

	}

	@Override
	public TreeMap<LocalDate, Double> getData()
	{
		return data;
	}

	@Override
	public MetaData getMetaData() {
		MetaData meta = new MetaData();
		meta.setLicense("");
		meta.setUrl("http://www.everysport.com/");
		meta.setOwner("Everyport");
		meta.setTitle("Totala mål per dag i Allsvenskan");
		meta.setUnit("Mål");
		meta.setHasData(!data.isEmpty());
		
		
		return meta;
	}
}
