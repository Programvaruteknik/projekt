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


	@Override
	public String getUnit() {

		return "Grader";
	}

	@Override
	public TreeMap<LocalDate, Double> getData() {

		TreeMap<LocalDate, Double> output = new TreeMap<LocalDate, Double>();
		List<Time> times = new WeatherAPI().getTimes();

		for (Time time : times) {
			output.put(time.getDate(), time.getLocation().getSun().getNoon().getAltitude());
		}
		return output;
	}

	@Override
	public MetaData getMetaData() {
		MetaData meta =new MetaData();
		meta.setLicense("");
		meta.setOwner("met.no");
		meta.setUrl("http://met.no");
		meta.setTitle("Solens altitud vid 12");
		return meta;
	}

}
