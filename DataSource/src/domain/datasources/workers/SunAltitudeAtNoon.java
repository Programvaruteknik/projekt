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

	public SunAltitudeAtNoon() {
		data = new TreeMap<LocalDate, Double>();
		loadData();
	}

	private void loadData() {
		List<Time> times = new WeatherAPI().getTimes("2001-01-01","2010-01-01");
		for (Time time : times) {
			data.put(time.getDate(), time.getLocation().getSun().getNoon()
					.getAltitude());
		}
	}


	@Override
	public TreeMap<LocalDate, Double> getData(String fromDate, String toDate) {

		TreeMap<LocalDate, Double> output = new TreeMap<LocalDate, Double>();
		List<Time> times = new WeatherAPI().getTimes(fromDate, toDate);

		for (Time time : times) {
			output.put(time.getDate(), time.getLocation().getSun().getNoon().getAltitude());
		}
		data = output;
		return output;
	}

	@Override
	public MetaData getMetaData() {
		MetaData meta =new MetaData();
		meta.setLicense("");
		meta.setOwner("met.no");
		meta.setUrl("http://met.no");
		meta.setTitle("Solens altitud vid 12");
		meta.setUnit("Grader");
		//meta.setHasData(!data.isEmpty());
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
