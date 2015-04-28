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
private String name ="Totala mål per dag i Allsvenskan";
private String unit="Mål";
	@Override
	public String getName()
	{
		return name;
	}

	@Override
	public String getUnit()
	{
		return unit;
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
		SourceMetaData meta = new SourceMetaData();
		meta.setLicense("");
		meta.setUrl("http://www.everysport.com/");
		meta.setOwner("Everyport");
		meta.setTitle(getName());
		return meta;
	}
}
