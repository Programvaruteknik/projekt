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
	public void downLoadDataSource(String fromDate, String toDate)
	{
		data = new TreeMap<LocalDate, Double>();

		List<Event> events = new EverysportApi().getAllsvenskan().downLoad(fromDate, toDate);
		if(events != null){			
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
		
		
	}

	@Override
	public MetaData getMetaData() {
		MetaData meta = new MetaData();
		meta.setLicense("");
		meta.setUrl("http://www.everysport.com/");
		meta.setOwner("Everyport");
		meta.setTitle(name);
		meta.setUnit("Mål");
		
		meta.setFirstDate("2000");
		meta.setLastDate("2015");
		
		return meta;
	}

	@Override
	public TreeMap<LocalDate, Double> getData() {
		return data;
	}
}