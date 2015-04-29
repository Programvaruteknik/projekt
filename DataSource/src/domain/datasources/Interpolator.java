package domain.datasources;

import java.time.LocalDate;
import java.time.Period;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import domain.datasources.model.MetaData;
import domain.matching.Resolution;

/**
 * Interpolates the missing dates which are not found in a {@link Map}. The new entries will
 * have their values set to NULL.
 * 
 * @author rasmus
 *
 */

public class Interpolator {
	/**
	 * Returns the date which occurs first in the map.
	 * 
	 * @param sc
	 *            The Map.
	 * @return LocalDate The first date.
	 */
	protected LocalDate getFirst(DataSource sc) {
		Map<LocalDate, Double> map = sc.getData();
		int counter = 0;
		for (LocalDate date : map.keySet()) {
			if (counter == 0) {
				return date;
			}
		}
		return null;
	}

	/**
	 * Returns the date which occurs last.
	 * 
	 * @param sc
	 *            The map.
	 * @return LocalDate The Date.
	 */
	protected LocalDate getLast(DataSource sc) {
		Map<LocalDate, Double> map = sc.getData();
		int lastIndex = map.size() - 1;
		int counter = 0;
		for (LocalDate date : map.keySet()) {
			if (counter == (lastIndex)) {
				return date;
			}
			counter++;
		}
		return null;
	}

	/**
	 * Returns the period where all the dates in the map are occurring.
	 * 
	 * @param sc
	 *            The map.
	 * @return int The length (measuered in days) of the period.
	 */
	protected int getPeriod(DataSource sc, Resolution resolution) {
		
		LocalDate last = getLast(sc);
		LocalDate current = getFirst(sc);
		int counter = 0;
		
		while (!current.equals(last)) {
			counter++;
			current = resolution.next(current);
		}

		return counter;
	}

	/**
	 * The map are filled with dates which are considered as missing.
	 * 
	 * @param sc
	 *            The map which will be filled with the missing dates.
	 */
	public DataSource fillOutMissingDays(DataSource sc, Resolution resolution) {
		TreeMap<LocalDate, Double> map = sc.getData();
		TreeMap<LocalDate, Double> copyMap = new TreeMap<>();
		LocalDate first = getFirst(sc);
		LocalDate current = first;
		int days = getPeriod(sc, resolution);

		for (int i = 0; i < days + 1; i++) {
			if (map.containsKey(current)) {
				copyMap.put(current, map.get(current));
			} else {
				copyMap.put(current, null);
			}
			current = resolution.next(current);
		}

		DataSource copySource = new DataSource() {

			@Override
			public String getUnit() {
				return sc.getUnit();
			}

			@Override
			public TreeMap<LocalDate, Double> getData() {
				return copyMap;
			}

			@Override
			public MetaData getMetaData() {
				return null;
			}
		};
		return copySource;
	}
}
