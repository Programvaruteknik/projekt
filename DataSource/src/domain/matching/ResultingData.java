package domain.matching;

import java.util.Map;

public class ResultingData
{

	private Map<String, DataPair> resultData;

	public ResultingData(Map<String, DataPair> resultData)
	{
		this.resultData = resultData;
	}

	public Map<String, DataPair> getData()
	{
		return resultData;

	}
}
