package domain.datasources.model;

import java.util.List;

import domain.datasources.modulateing.ModelatingComand;

public class MetaData
{
	private String title, url, owner, license, unit;
	private boolean hasData;
	
	private List<ModelatingComand> modList;

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
