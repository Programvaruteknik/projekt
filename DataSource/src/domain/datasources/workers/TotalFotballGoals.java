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
private String name ="Totala mål per dag i Allsvenskan";
private String unit="Mål";
private TreeMap<LocalDate, Double> data = null;


	@Override
	public TreeMap<LocalDate, Double> getData(String fromDate, String toDate)
	{
		TreeMap<LocalDate, Double> output = new TreeMap<LocalDate, Double>();
		
		List<Event> events = new EverysportApi().getAllsvenskan2014(fromDate, toDate);
		
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
		data = output;
		return output;
	}

	@Override
	public MetaData getMetaData() {
		MetaData meta = new MetaData();
		meta.setLicense("");
		meta.setUrl("http://www.everysport.com/");
		meta.setOwner("Everyport");
		meta.setTitle(name);
		meta.setUnit("Mål");
		meta.setHasData(!data.isEmpty());
		
		return meta;
	}

	@Override
	public TreeMap<LocalDate, Double> getData() {
		// TODO Auto-generated method stub
		return data;
	}
	
	public double getMedel()
	{
		double sum = 0;
		for(Double d : data.values())
		{
			sum += d;
		}
		return sum/data.values().size();
	}
}