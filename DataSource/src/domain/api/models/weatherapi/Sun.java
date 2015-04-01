package domain.api.models.weatherapi;

import com.google.gson.annotations.SerializedName;

public class Sun {

	
	@SerializedName("noon")
	private Noon noon;

	public Noon getNoon() {
		return noon;
	}
}
