package domain.datasources;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import domain.matching.Resolution;

public class DataSourceMerger
{
	TreeMap<LocalDate, ArrayList<Double>> dataSource1, dataSource2;

	public DataSourceMerger(TreeMap<LocalDate, ArrayList<Double>> dataSource1, TreeMap<LocalDate, ArrayList<Double>> dataSource2)
	{
		super();
		this.dataSource1 = dataSource1;
		this.dataSource2 = dataSource2;
	}
	
	public TreeMap<LocalDate, ArrayList<Double>> merge(Resolution resolution)
	{
		LocalDate currentdate = getFirst(dataSource1, dataSource2);
		LocalDate lastdate = resolution.next(getLast(dataSource1, dataSource2));
		
		TreeMap<LocalDate, ArrayList<Double>> output = new TreeMap<LocalDate, ArrayList<Double>>();
		
		while(!currentdate.equals(lastdate))
		{
			ArrayList<Double> tmpList = dataSource1.get(currentdate);
			if(tmpList == null)
			{
				tmpList = new ArrayList<>();
				tmpList.add(null);
			}
			
			
			ArrayList<Double> tmp = dataSource2.get(currentdate);
			if(tmp != null)
			{
				tmpList.add(tmp.get(0));
			}
			else
			{
				tmpList.add(null);
			}

			
			output.put(currentdate, tmpList);
			
			currentdate = resolution.next(currentdate);
		}
		
		
		
		return output;
	}
	
	private LocalDate getFirst(TreeMap<LocalDate, ArrayList<Double>> map1, TreeMap<LocalDate, ArrayList<Double>> map2) 
	{
		if(map1.firstKey().isBefore(map2.firstKey()))
		{
			return map1.firstKey();
		}
		return map2.firstKey();
	}
	
	private LocalDate getLast(TreeMap<LocalDate, ArrayList<Double>> map1, TreeMap<LocalDate, ArrayList<Double>> map2) 
	{
		if(map1.lastKey().isAfter(map2.lastKey()))
		{
			return map1.lastKey();
		}
		return map2.lastKey();
	}
}
