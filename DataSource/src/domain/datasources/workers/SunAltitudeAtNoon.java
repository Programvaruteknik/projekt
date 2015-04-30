package domain.datasources.workers;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import domain.api.WeatherAPI;
import domain.api.models.weatherapi.Time;
import domain.datasources.DataSource;
import domain.datasources.model.MetaData;

public class SunAltitudeAtNoon implements DataSource {

	private TreeMap<LocalDate, Double> data;

	
	
	public SunAltitudeAtNoon()
	{
		data = new TreeMap<LocalDate, Double>();
		List<Time> times = new WeatherAPI().getTimes();

		for (Time time : times) {
			data.put(time.getDate(), time.getLocation().getSun().getNoon().getAltitude());
		}
	}

	@Override
	public TreeMap<LocalDate, Double> getData() {

		return data;
	}

	@Override
	public MetaData getMetaData() {
		MetaData meta =new MetaData();
		meta.setLicense("");
		meta.setOwner("met.no");
		meta.setUrl("http://met.no");
		meta.setTitle("Solens altitud vid 12");
		meta.setUnit("Grader");
		return meta;
	}

}
