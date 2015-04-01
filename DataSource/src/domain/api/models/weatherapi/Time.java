package domain.api.models.weatherapi;

import java.time.LocalDate;

import com.google.gson.annotations.SerializedName;

public class Time
{
	@SerializedName("@date")
	private String date;
	
	@SerializedName("location")
	private Location location;
	
	public Location getLocation() {
		return location;
	}

	public LocalDate getDate() {
		return LocalDate.parse(date);
	}
}
