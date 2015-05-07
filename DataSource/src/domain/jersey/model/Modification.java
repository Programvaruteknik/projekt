package domain.jersey.model;

public class Modification
{
	private String dataSourceName;
	private int year, month,days;
	
	
	
	public Modification(String dataSourceName, int year, int month, int days)
	{
		super();
		this.dataSourceName = dataSourceName;
		this.year = year;
		this.month = month;
		this.days = days;
	}
	
	
	
	public String getDataSourceName()
	{
		return dataSourceName;
	}
	public int getYear()
	{
		return year;
	}
	public int getMonth()
	{
		return month;
	}
	public int getDays()
	{
		return days;
	}
	
	
}
