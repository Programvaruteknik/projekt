package domain.datasources;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import domain.api.EverysportApi;
import domain.datasources.workers.BitCoinChangeSource;
import domain.datasources.workers.BitCoinOpenSource;
import domain.datasources.workers.BitCoinVolume;
import domain.datasources.workers.BowlingSource;
import domain.datasources.workers.DifferenceInFootballScore;
import domain.datasources.workers.FootballSpectatorSource;
import domain.datasources.workers.SunAltitudeAtNoon;
import domain.datasources.workers.TvFriendsSource;
import domain.datasources.workers.TvSourceCommunity;
import domain.datasources.workers.TvSourceDoctorWho;
import domain.datasources.workers.downLoadURL;
import domain.datasources.workers.general.TotalGoalDataSource;

public class DataSourceFactory
{
	private final Map<String, DataSource> sourceMap;
	
	public DataSourceFactory() 
	{
		this.sourceMap = new HashMap<>();
		prePopulateSourceMap();
	}
	
	private void addToMap(DataSource loader, String name)
	{
		sourceMap.put(name, loader);
	}
	
	private void prePopulateSourceMap() 
	{
	
		addToMap(new TotalGoalDataSource("Totala mål per dag i Basketligan Grundserien", new EverysportApi().getBasketliganGrundserien()),"Basket Grundserie");
		addToMap(new TotalGoalDataSource("Totala mål per dag i Superserien Amrekansk fotboll", new EverysportApi().getSuperSerienAmrekanskFotboll()),"Amerikans fotbll superserie");
		addToMap(new TotalGoalDataSource("Totala mål per dag i Divition1 Södra för Bandy 2014", new EverysportApi().getBandyDivition1Södra()),"Bandy 2014 Södra");
		addToMap(new TotalGoalDataSource("Totala mål per dag i Allsvenskan 2014", new EverysportApi().getAllsvenskan()),"Allsvenskan");
		addToMap(new TotalGoalDataSource("Totala mål per dag i SHL 2014", new EverysportApi().getSHL()),"SHL 2014");
		
		addToMap(new SunAltitudeAtNoon(),"Solens altitude");
//		
		addToMap(new BitCoinOpenSource(),"Bitcoin Open Values");
		addToMap(new BitCoinChangeSource(),"Bitcoin Change values");
		addToMap(new BitCoinVolume(),"Bitcoin Volume values");
//		
		addToMap(new TvSourceCommunity(),"Tv Community");
		addToMap(new TvSourceDoctorWho(),"Tv DoctorWho ");
		addToMap(new TvFriendsSource(),"Tv Friends");
//		

		addToMap(new FootballSpectatorSource(),"Football specator");
		addToMap(new DifferenceInFootballScore(),"Football difference in score");
		addToMap(new BowlingSource(),"Bowling");

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
