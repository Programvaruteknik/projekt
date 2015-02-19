package domain;

import java.util.Map;

public class ResoultingData
{

	private Map<String, DataPair> resultData;

	public ResoultingData(Map<String, DataPair> resultData)
	{
		this.resultData = resultData;
	}

	public Map<String, DataPair> getData()
	{
		return resultData;

	}
}
