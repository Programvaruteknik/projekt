package domain.utility;

import java.time.LocalDate;
import java.util.TreeMap;

import domain.datasources.DataSource;

/**
 * An utility that can calculate both the sum and the average value of an
 * DataSource.
 * 
 * @author rasmus
 *
 */
public class DataSourceCalculator {
	/**
	 * Calculates the average of a data source.
	 * 
	 * @param source
	 * @return double the averege.
	 */
	public static double getAverage(DataSource source) {
		TreeMap<LocalDate, Double> map = source.getData();
		if (map.isEmpty()) {
			return 0;
		}
		double avarage = 0;

		for (Double d : map.values()) {
			avarage += d;
		}

		return avarage / map.size();
	}

	/**
	 * Calculates the accumulated value of all the values on the sources.
	 * 
	 * @param source
	 * @return double The sum of all values.
	 */
	public static double getSum(DataSource source) {
		TreeMap<LocalDate, Double> map = source.getData();
		double sum = 0;

		for (Double d : map.values()) {
			sum += d;
		}

		return sum;
	}

}
