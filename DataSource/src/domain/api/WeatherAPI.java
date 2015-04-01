package domain.api;

import java.util.List;

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
	
	public List<Time> getTimes() {
		Astrodata astrodata = handler.get("http://api.met.no/weatherapi/sunrise/1.0/?lat=60.67;lon=17.12;from=2014-04-01;to=2014-04-30", Astrodata.class);
		return astrodata.getTimes();
	}
	
}
