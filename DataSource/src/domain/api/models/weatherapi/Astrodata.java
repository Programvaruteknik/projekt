package domain.api.models.weatherapi;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Astrodata {

	@SerializedName("time")
	List<Time> times;
	
	public List<Time> getTimes() {
		return times;
	}
}
