package domain.jersey.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.TreeMap;

import domain.datasources.model.MetaData;

public class DataSourcePackage
{
	private ArrayList<String> header;
	private ArrayList<MetaData> metaDataList;
	
	private TreeMap<LocalDate, ArrayList<Double>> data;
	
	
	public DataSourcePackage(ArrayList<String> header,ArrayList<MetaData> metaList, TreeMap<LocalDate, ArrayList<Double>> data)
	{
		super();

		this.metaDataList = metaList;
		this.header = header;
		this.data = data;
	}
	
}
