package domain.api.models.everysport;

import java.time.LocalDate;

public class Event {
	private int homeTeamScore, visitingTeamScore;
	private String startDate;

	public int getHomeTeamScore() {
		return homeTeamScore;
	}

	public int getVisitingTeamScore() {
		return visitingTeamScore;
	}

	public LocalDate getStartDate() {
		LocalDate date = LocalDate.parse(startDate.substring(0, 10));
		return date;

	}

}
