package domain.datasources.modulateing;

import java.time.LocalDate;
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
			public String getUnit()
			{
				// TODO Auto-generated method stub
				return null;
			}
			
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
				return null;
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
