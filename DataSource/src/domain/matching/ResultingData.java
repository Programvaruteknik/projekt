package domain.matching;

import java.util.Map;
/**
 * Stores a map that consist of the resulting data from #DataMatcher.
 * 
 * @author rasmus
 *
 */
public class ResultingData
{

	private Map<String, DataPair> resultData;
	private String xAxis,yAxis;
/**
 * Creates result from the giving map.
 * 
 * @param resultData The map.
 */
	public ResultingData(Map<String, DataPair> resultData)
	{
		this.resultData = resultData;
	}
/**
 * Returns the result in the form of an Map.
 * @return Map<String,DataPair> The map.
 */
	public Map<String, DataPair> getData()
	{
		return resultData;
	}
	
	public void setXAxis(String axisName){
		this.xAxis = axisName;
	}
	
	public void setYAxis(String axisName){
		this.yAxis = axisName;
	}
	
}
