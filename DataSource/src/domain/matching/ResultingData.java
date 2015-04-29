package domain.matching;

import java.util.Map;

import domain.datasources.model.MetaData;

/**
 * Stores a map that consist of the resulting data from #DataMatcher.
 * 
 * @author rasmus
 *
 */
public class ResultingData {

	private Map<String, DataPair> resultData;
	private MetaData xMeta, yMeta;

	/**
	 * Creates result from the giving map.
	 * 
	 * @param resultData
	 *            The map.
	 */
	public ResultingData(Map<String, DataPair> resultData) {
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

	public void setXMeta(MetaData meta) {
		xMeta = meta;
	}

	public void setYMeta(MetaData meta) {
		yMeta = meta;
	}

	public MetaData getXMeta() {
		return xMeta;
	}

	public MetaData getYMeta() {
		return yMeta;
	}

}
