package domain.datasources;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import domain.datasources.workers.BitCoinChangeSource;
import domain.datasources.workers.BitCoinOpenSource;
import domain.datasources.workers.DifferenceInFootballScore;
import domain.datasources.workers.FootballSpectatorSource;
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
	
	private void addToMap(DataSource dataSource)
	{
		sourceMap.put(dataSource.getMetaData().getName(), dataSource);
		
	}
	
	private void prePopulateSourceMap() 
	{
		
		addToMap(new TotalFotballGoals());
		addToMap(new SunAltitudeAtNoon());
		addToMap(new BitCoinOpenSource());
		addToMap(new BitCoinChangeSource());
		addToMap(new TvSourceCommunity());
		addToMap(new TvSourceDoctorWho());
		addToMap(new FootballSpectatorSource());
		addToMap(new DifferenceInFootballScore());
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
