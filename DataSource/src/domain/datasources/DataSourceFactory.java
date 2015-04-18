package domain.datasources;

import java.util.List;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import domain.datasources.workers.InterpolatedMe;
import domain.datasources.workers.SunAltitudeAtNoon;
import domain.datasources.workers.TotalFotballGoals;

public class DataSourceFactory
{
	private final Map<String, DataSource> sourceMap;
	
	public DataSourceFactory() 
	{
		this.sourceMap = new HashMap<>();
		prePopulateSourceMap();
	}
	
	private void prePopulateSourceMap() 
	{
		sourceMap.put("Total Fotball Goals", new TotalFotballGoals());
		sourceMap.put("Sun Altitude At Noon", new SunAltitudeAtNoon());
		sourceMap.put("Test", new InterpolatedMe());
	}
	public DataSource getDataSource(String id)
	{
		if(sourceMap.containsKey(id))
			return sourceMap.get(id);
		else 
			return null;
	}
	
	public List<String> getNameAllDataSources()
	{
		List<String> nameList = new LinkedList<>();
		
		for(String name : sourceMap.keySet())
			nameList.add(name);
		
		return nameList;
	}
}
