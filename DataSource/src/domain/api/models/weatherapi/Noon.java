package domain.api.models.weatherapi;

import com.google.gson.annotations.SerializedName;

public class Noon {

	@SerializedName("@altitude")
	private Double altitude;

	public Double getAltitude() {
		return altitude;
	}
}
