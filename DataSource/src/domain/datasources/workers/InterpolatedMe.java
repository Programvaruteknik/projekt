package domain.datasources.workers;

import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;

import domain.datasources.DataSource;

/**
 * << === Used for testing purposes only. === >> This source is used for
 * 
 * 
 * comparing with other sources. This can be used when there is no connection to
 * the network.
 * 
 * @author rasmus
 *
 */
public class InterpolatedMe implements DataSource {
	/**
	 * Returns the name of the source.
	 * 
	 * @return String The name.
	 */
	@Override
	public String getName() {
		return "InterpolateMe";
	}

	/**
	 * Returns the unit of this test source.
	 * 
	 * @return String the unit.
	 */
	@Override
	public String getUnit() {
		return "Undefined";
	}

	/**
	 * Returns a map with four values.
	 * 
	 * @return Map<LocalDate,Double> The map.
	 */
	@Override

	public TreeMap<LocalDate, Double> getData() {
		TreeMap<LocalDate,Double> map = new TreeMap<>();

		map.put(LocalDate.parse("2001-01-01"), 10.0);
		map.put(LocalDate.parse("2001-01-10"), 20.0);

		map.put(LocalDate.parse("2001-02-01"), 10.0);
		map.put(LocalDate.parse("2001-02-10"), 20.0);

		return map;
	}

}
