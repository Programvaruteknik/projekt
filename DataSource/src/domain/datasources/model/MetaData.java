package domain.datasources.model;

public class MetaData
{
	private String title, url, owner, license, unit;
	private boolean hasData;

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
		return null;
	}

	public Object getOwner()
	{
		return null;
	}

	public Object getLicense()
	{
		return null;
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

	public boolean isHasData()
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
	
	

}
