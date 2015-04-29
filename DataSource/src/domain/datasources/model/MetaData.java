package domain.datasources.model;

public class MetaData {
public String title, url, owner, license;

	public void setUrl(String url) {
		this.url = url;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public String getUrl() {
		return null;
	}

	public Object getOwner() {
		return null;
	}

	public Object getLicense() {
		return null;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getName() {
		return title;
	}

}
