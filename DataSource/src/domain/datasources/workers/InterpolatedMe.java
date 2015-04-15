package domain.datasources.workers;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import domain.datasources.DataSource;

public class InterpolatedMe implements DataSource {

	@Override
	public String getName() {
		return "InterpolateMe";
	}

	@Override
	public String getUnit() {
		return "Undefined";
	}

	@Override
	public Map<LocalDate, Double> getData() {
		Map<LocalDate,Double> map = new TreeMap<>();
		map.put(LocalDate.parse("2001-01-01"), 10.0);
		map.put(LocalDate.parse("2001-01-10"), 20.0);
		
		map.put(LocalDate.parse("2001-02-01"), 10.0);
		map.put(LocalDate.parse("2001-02-10"), 20.0);
		
		
		return map;
	}

}
