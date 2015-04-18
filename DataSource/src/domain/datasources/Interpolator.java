package domain.datasources;

import java.time.LocalDate;
import java.time.Period;
import java.util.Map;

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
	 * @param sorted
	 *            The Map.
	 * @return LocalDate The first date.
	 */
	protected LocalDate getFirst(Map<LocalDate, Double> sorted) {
		int counter = 0;
		for (LocalDate date : sorted.keySet()) {
			if (counter == 0) {
				return date;
			}
		}
		return null;
	}

	/**
	 * Returns the date which occurs last.
	 * 
	 * @param sorted
	 *            The map.
	 * @return LocalDate The Date.
	 */
	protected LocalDate getLast(Map<LocalDate, Double> sorted) {
		int lastIndex = sorted.size() - 1;
		int counter = 0;
		for (LocalDate date : sorted.keySet()) {
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
	 * @param map
	 *            The map.
	 * @return int The length (measuered in days) of the period.
	 */
	protected int getPeriod(Map<LocalDate, Double> map) {
		LocalDate first = getFirst(map);
		LocalDate last = getLast(map);
		Period p = first.until(last);

		int distanceInDays = p.getDays();

		return distanceInDays;
	}

	/**
	 * The map are filled with dates which are considered as missing.
	 * @param map 
	 *            The map which will be filled with the missing dates.
	 */
	public void fillOutMissingDays(Map<LocalDate, Double> map) {
		int distance = getPeriod(map);
		LocalDate first = getFirst(map);

		for (int i = 0; i < distance; i++) {
			LocalDate tmp = first.plusDays(i);
			if (!map.containsKey(tmp)) {
				map.put(tmp, null);
			}
		}
	}
}
