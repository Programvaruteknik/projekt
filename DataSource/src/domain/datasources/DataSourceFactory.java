package domain.datasources;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import domain.datasources.workers.BitCoin;
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
	
	private void addToMap(DataSource dataSource)
	{
		sourceMap.put(dataSource.getMetaData().getName(), dataSource);
		
	}
	
	private void prePopulateSourceMap() 
	{
		
		addToMap(new TotalFotballGoals());
		addToMap(new SunAltitudeAtNoon());
		addToMap(new BitCoin());
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
