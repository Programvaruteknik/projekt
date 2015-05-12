package domain.datasources;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.TreeMap;

public class DataSourceFormatter
{
	private DataSource dataSource;

	public DataSourceFormatter(DataSource dataSource, String fromDate, String toDate)
	{
		super();
		this.dataSource = dataSource;
		this.dataSource.getData(fromDate, toDate);
	}
	
	public TreeMap<LocalDate, ArrayList<Double>> toMergeableFormat()
	{
		TreeMap<LocalDate, ArrayList<Double>> output = new TreeMap<>();
		
		for(Entry<LocalDate, Double> entry :dataSource.getData().entrySet())
		{
			ArrayList<Double> tmp = new ArrayList<>();
			tmp.add(entry.getValue());
			
			output.put(entry.getKey(), tmp);
		}
		
		
		
		return output;
		
	}
}
