package domain.jersey.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.TreeMap;

public class DataSourcePackage
{
	private ArrayList<String> header;
	private TreeMap<LocalDate, ArrayList<Double>> data;
	
	public DataSourcePackage(ArrayList<String> header, TreeMap<LocalDate, ArrayList<Double>> data)
	{
		super();
		this.header = header;
		this.data = data;
	}
	
}
