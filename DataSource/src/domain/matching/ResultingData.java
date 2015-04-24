package domain.matching;

import java.util.Map;

import domain.datasources.MetaData;

/**
 * Stores a map that consist of the resulting data from #DataMatcher.
 * 
 * @author rasmus
 *
 */
public class ResultingData {

	private Map<String, DataPair> resultData;
	private MetaData metaData;

	/**
	 * Creates result from the giving map.
	 * 
	 * @param resultData
	 *            The map.
	 */
	public ResultingData(Map<String, DataPair> resultData) {
		metaData = new MetaData();
		this.resultData = resultData;
	}

	/**
	 * Returns the result in the form of an Map.
	 * 
	 * @return Map<String,DataPair> The map.
	 */
	public Map<String, DataPair> getData() {
		return resultData;
	}

	public MetaData getMetaData() {
		return metaData;
	}

	public void setXAxis(String axisName) {
		metaData.setXLabel(axisName);
	}

	public void setYAxis(String axisName) {

		metaData.setYLabel(axisName);
	}

}
