package domain.api;


import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import domain.api.models.weatherapi.AstroDateCombine;
import domain.api.models.weatherapi.Astrodata;
import domain.api.models.weatherapi.Time;
import domain.api.serialization.XmlParser;
import domain.api.url.UrlFetcher;

public class WeatherAPI
{
	ApiHandler handler;
	
	
	public WeatherAPI() {
		handler = new ApiHandler(new UrlFetcher(), new XmlParser());
	}
	
	public List<Time> getTimes(String fromDate, String toDate) 
	{
		List<Pair> fetchDates = checkConsecDates(fromDate, toDate);
		AstroDateCombine times = new AstroDateCombine();
		for(Pair p : fetchDates)
		{
			Astrodata astrodata = handler.get("http://api.met.no/weatherapi/sunrise/1.0/?lat=60.67;lon=17.12;from="+ p.getStartDate() + ";to=" + p.getEndDate(), Astrodata.class);
			times.addTime(astrodata.getTimes());
		}
		return times.getTimes();
	}

	private List<Pair> checkConsecDates(String fromDate, String toDate)
	{
		
		LocalDate startDate = LocalDate.parse(fromDate);
		LocalDate foo = startDate.plusDays(1);
		int amountDate = 1;
		LocalDate endDate = LocalDate.parse(toDate);
		List<Pair> dates = new LinkedList<Pair>();
	
		while(foo.isBefore(endDate))
		{
			
			if(amountDate == 29)
			{
				dates.add(new Pair(startDate, foo));
				startDate = foo.plusDays(1);
				foo = startDate.plusDays(1);
				amountDate = 1;
			}else{
				
			
			foo = foo.plusDays(1);
			amountDate++;
			}
		}
		if(amountDate != 0)
		{
			dates.add(new Pair(startDate, foo));
		}
		
	
		return dates;
	}
	
}

class Pair
{
	private LocalDate startDate;
	private LocalDate endDate;
	public Pair(LocalDate startDate, LocalDate endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public String getStartDate()
	{
		return startDate.toString();
	}
	
	public String getEndDate()
	{
		return endDate.toString();
	}
	
	public String toString()
	{
		return getStartDate() +"==" + getEndDate();
	}
}