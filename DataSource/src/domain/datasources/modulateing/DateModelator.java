package domain.datasources.modulateing;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

import domain.datasources.DataSource;
import domain.datasources.model.MetaData;

public class DateModelator implements ModelatingComand
{
	private DataSource dataSource;
	private int years, months, days;

	
	

	public DateModelator(DataSource dataSource, int years, int months, int days)
	{
		this.dataSource = dataSource;
		this.years = years;
		this.months = months;
		this.days = days;
	}




	@Override
	public DataSource execute()
	{
		DataSource output = new DataSource()
		{
			
			@Override
			public TreeMap<LocalDate, Double> getData()
			{
				TreeMap<LocalDate, Double> output = new TreeMap<>();
				
				for (Entry<LocalDate, Double> entry : dataSource.getData().entrySet())
				{
					LocalDate key = entry.getKey();
					
					key = manipulateDate(key);
					
					output.put(key, entry.getValue());
				}
				
				return output;
			}

			@Override
			public MetaData getMetaData()
			{
				MetaData output = dataSource.getMetaData();
				
				List<ModelatingComand> list = output.getModList();
				
				if(list == null)
				{
					list = new ArrayList<ModelatingComand>();
				}
				
				list.add(DateModelator.this);
				
				output.setModList(list); 
				
				
				return output;
			}
		};
		
		return output;
	}
	
	private LocalDate manipulateDate(LocalDate date)
	{
		date = date.plusDays(days);
		date = date.plusMonths(months);
		date = date.plusYears(years);
		
		return date;
	}
	
}
