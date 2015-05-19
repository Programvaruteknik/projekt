package utilities;

import java.util.ArrayList;

import domain.datasources.DataSource;
import domain.datasources.modulateing.DateModelator;
import domain.matching.DataMatcher;
import domain.matching.Resolution;
import domain.matching.ResultingData;

public class PhaseShifter {

	public ArrayList<DataSource> shiftDays(DataSource src, int days) {
		ArrayList<DataSource> list = new ArrayList<>();
		DateModelator modulator;

		for (int i = 1; i <= Math.abs(days); i++) {
			if (days > 0) {
				modulator = new DateModelator(src, 0, 0, i);
			} else {
				modulator = new DateModelator(src, 0, 0, -i);
			}
			list.add(modulator.execute());
		}
		return list;
	}

	/**
	 * Calculates resulting data, which is determinate by each number of shifts.
	 * 
	 * One of the sources are stationary while the other one is stationary.
	 * for each shift a new result will be a calculated between the stationary
	 * one and the new shifted source.
	 * 
	 * @param stationarySource	The stationary source-
	 * @param shiftingSourceo The source which will be shifted.
	 * @param i The number of days that will be shifted.
	 * @return ArrayList<ResultingData> The calculated of all results.
	 */
	public ArrayList<ResultingData> shiftDaysResultList(
			DataSource stationarySource, DataSource shiftingSourceo, int i) {
		ArrayList<DataSource> list = shiftDays(shiftingSourceo, i);
		ArrayList<ResultingData> resultList = new ArrayList<ResultingData>();

		for (DataSource elem : list) {
			DataMatcher matcher = new DataMatcher(stationarySource, elem,
					Resolution.DAY);
			resultList.add(matcher.match());
		}
		return resultList;
	}

}
