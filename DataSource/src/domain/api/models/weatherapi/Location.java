package domain.api.models.weatherapi;

import com.google.gson.annotations.SerializedName;

public class Location {

	@SerializedName("sun")
	private Sun sun;

	public Sun getSun() {
		return sun;
	}
}
