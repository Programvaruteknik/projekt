package utilities;

import java.util.ArrayList;
import java.util.Map;

import org.apache.commons.math3.stat.regression.SimpleRegression;

import domain.matching.DataPair;
import domain.matching.ResultingData;

/**
 * Finds the highest correlation in a list of ResultingData's. It is also
 * possible to determine the index of the highest correlation.
 * 
 * @author rasmus
 *
 */
public class CorrelationFinder {
	/**
	 * Returns the R-Squared value of the result.
	 * 
	 * @param result
	 *            The result.
	 * @return double The R-Squared value.
	 */
	protected double getR2(ResultingData result) {
		Map<String, DataPair> map = result.getData();
		SimpleRegression regression = new SimpleRegression();

		for (String date : map.keySet()) {
			DataPair pair = map.get(date);
			regression.addData(pair.getX(), pair.getY());
		}

		return regression.getRSquare();
	}

	/**
	 * Returns the data with the highest correlation factor.
	 * 
	 * @param result
	 *            One of the two results.
	 * @param secondResult
	 *            The other one of the results.
	 * @return {@link ResultingData} The data with the highest correlation
	 *         factor.
	 */
	protected ResultingData getHighestCorrelation(ResultingData result,
			ResultingData secondResult) {
		return getR2(result) > getR2(secondResult) ? result : secondResult;
	}

	/**
	 * Returns the Resulting Data which has the highest correlation factor.
	 * 
	 * @param resultList
	 *            The list with all the results.
	 * @return {@link ResultingData} The data with the highest correlation
	 *         factor.
	 */
	public ResultingData getHighestCorrelation(
			ArrayList<ResultingData> resultList) {
		ResultingData current = resultList.get(0);
		ResultingData highest = current;

		for (int index = 1; index < resultList.size(); index++) {
			current = resultList.get(index);
			highest = getHighestCorrelation(current, highest);
		}
		return highest;
	}

	/**
	 * Returns the index of the highest correlation.
	 * 
	 * @param resultList
	 *            The list of all the results.
	 * @return int The index of the highest correlation.
	 */
	public int indexOfHighestCorrelation(ArrayList<ResultingData> resultList) {
		ResultingData highest = getHighestCorrelation(resultList);
		return resultList.indexOf(highest);
	}

}
