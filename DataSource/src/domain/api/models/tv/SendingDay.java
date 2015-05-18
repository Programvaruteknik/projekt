package domain.api.models.tv;

import java.time.LocalDate;

public class SendingDay {
	private String airdate;

	public int getDayOfMonth() {
		return LocalDate.parse(airdate).getDayOfMonth();
	}

	public LocalDate getAirDate() {
		return LocalDate.parse(airdate);
	}
}
