package domain.datasources;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import domain.datasources.workers.BitCoinChangeSource;
import domain.datasources.workers.BitCoinOpenSource;
import domain.datasources.workers.SunAltitudeAtNoon;
import domain.datasources.workers.TotalFotballGoals;
import domain.datasources.workers.TvSourceCommunity;
import domain.datasources.workers.TvSourceDoctorWho;

public class DataSourceFactory
{
	private final Map<String, DataSource> sourceMap;
	
	public DataSourceFactory() 
	{
		this.sourceMap = new HashMap<>();
		prePopulateSourceMap();
	}
	
	private void addToMap(DataSource dataSource, String name)
	{
		sourceMap.put(name, dataSource);
		
	}
	
	private void prePopulateSourceMap() 
	{
		
		addToMap(new TotalFotballGoals(), "FootBall");
		addToMap(new SunAltitudeAtNoon(), "SunAltitude");
		addToMap(new BitCoinOpenSource(), "BitCoinOpenValue");
		addToMap(new BitCoinChangeSource(), "BitCoinChange");
		addToMap(new TvSourceCommunity(), "TvCommunity");
		addToMap(new TvSourceDoctorWho(), "TvDoctorWho");
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
