package domain.datasources.model;

import java.util.List;

import domain.datasources.modulateing.ModelatingComand;

public class MetaData
{
	private String title, url, owner, license, unit,firstDate,lastDate;
	private double meanValue,sum;
	private boolean hasData;
	
	private List<ModelatingComand> modList;



	public double getMeanValue() {
		return meanValue;
	}

	public void setMeanValue(double meanValue) {
		this.meanValue = meanValue;
	}

	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}

	public String getFirstDate() {
		return firstDate;
	}

	public void setFirstDate(String firstDate) {
		this.firstDate = firstDate;
	}

	public String getLastDate() {
		return lastDate;
	}

	public void setLastDate(String lastDate) {
		this.lastDate = lastDate;
	}

	public String getTitle()
	{
		return title;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public void setOwner(String owner)
	{
		this.owner = owner;
	}

	public void setLicense(String license)
	{
		this.license = license;
	}

	public String getUrl()
	{
		return url;
	}

	public Object getOwner()
	{
		return owner;
	}

	public Object getLicense()
	{
		return license;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getName()
	{
		return title;
	}

	public void setUnit(String unit)
	{
		this.unit = unit;
	}

	public boolean containsData()
	{
		return hasData;
	}

	public void setHasData(boolean hasData)
	{
		this.hasData = hasData;
	}

	public String getUnit()
	{
		return unit;
	}

	public List<ModelatingComand> getModList()
	{
		return modList;
	}

	public void setModList(List<ModelatingComand> modList)
	{
		this.modList = modList;
	}
	

}
