package domain.datasources;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class DataSourceMerger
{
	DataSource dataSource1, dataSource2;

	public DataSourceMerger(DataSource dataSource1, DataSource dataSource2)
	{
		super();
		this.dataSource1 = dataSource1;
		this.dataSource2 = dataSource2;
	}
	
	public TreeMap<LocalDate, ArrayList<Double>> merge()
	{
		LocalDate currentdate = getFirst(dataSource1.getData(), dataSource2.getData());
		LocalDate lastdate = getLast(dataSource1.getData(), dataSource2.getData()).plusDays(1);
		
		TreeMap<LocalDate, ArrayList<Double>> output = new TreeMap<LocalDate, ArrayList<Double>>();
		
		while(!currentdate.equals(lastdate))
		{
			ArrayList<Double> tmpList = new ArrayList<>();
			tmpList.add(dataSource1.getData().get(currentdate));
			tmpList.add(dataSource2.getData().get(currentdate));
			output.put(currentdate, tmpList);
			
			currentdate = currentdate.plusDays(1);
		}
		
		
		
		return output;
	}
	
	private LocalDate getFirst(TreeMap<LocalDate, Double> map1, TreeMap<LocalDate, Double> map2) 
	{
		if(map1.firstKey().isBefore(map2.firstKey()))
		{
			return map1.firstKey();
		}
		return map2.firstKey();
	}
	
	private LocalDate getLast(TreeMap<LocalDate, Double> map1, TreeMap<LocalDate, Double> map2) 
	{
		if(map1.lastKey().isAfter(map2.lastKey()))
		{
			return map1.lastKey();
		}
		return map2.lastKey();
	}
}
