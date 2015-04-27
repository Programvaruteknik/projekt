package domain.datasources.workers;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import domain.api.EverysportApi;
import domain.api.models.everysport.Event;
import domain.datasources.DataSource;
import domain.datasources.model.SourceMetaData;

public class TotalFotballGoals implements DataSource
{

	@Override
	public String getName()
	{
		return "Totala mål per dag i Allsvenskan";
	}

	@Override
	public String getUnit()
	{
		return "Mål";
	}

	@Override
	public TreeMap<LocalDate, Double> getData()
	{
		TreeMap<LocalDate, Double> output = new TreeMap<LocalDate, Double>();
		List<Event> events = new EverysportApi().getEvents();
		
		for (Event event : events)
		{
			Double totalScore = new Double(event.getHomeTeamScore() + event.getVisitingTeamScore());
			if(output.get(event.getStartDate()) != null)
			{
				output.put(event.getStartDate(), output.get(event.getStartDate()) + totalScore);
			}
			else
			{
				output.put(event.getStartDate(), totalScore);				
			}
		}
		return output;
	}

	@Override
	public SourceMetaData getMetaData() {
		SourceMetaData meta =new SourceMetaData();
		meta.setLicense("The license");
		meta.setUrl("http://www.everysport.com/");
		meta.setOwner("Everyport");
		return meta;
	}
}
