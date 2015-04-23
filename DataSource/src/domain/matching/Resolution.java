package domain.matching;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Locale;

import domain.datasources.DataSource;

/**
 * The resolution is used in {@link DataMatcher} to select different
 * resolutions. 
 * 
 * Different resolutions is used to decide whether two
 * {@link DataSource}'s is matching or not. The name of each resolution
 * corresponds to the respective precision.
 * 
 * @author rasmus
 *
 */
public enum Resolution {
	DAY((date) -> {
		return date.toString();
	},
	(date) ->{
		return date.plusDays(1);
	}), WEEK((date) -> {
		WeekFields weekFields = WeekFields.of(Locale.UK);
		int weekNumber = date.get(weekFields.weekOfYear());
		int year = date.getYear();

		if (weekNumber == 0) {
			year--;
			weekNumber = 53;
		}

		return String.format("%d V.%d", year, weekNumber);
	},
	(date) ->{
		return date.plusWeeks(1);
	}), MONTH((date) -> {
		String month = date.getMonth().name();
		int year = date.getYear();

		return String.format("%d %s", year, month);
	},
	(date) ->{
		return date.plusMonths(1);
	}), QUARTER((date) -> {
		int quater = date.getMonthValue() / 4 + 1;
		int year = date.getYear();
		return String.format("%d K.%d", year, quater);
	},
	(date) ->{
		return date.plusMonths(3);
	}), HALF((date) -> {
		int half = date.getMonthValue() / 6 + 1;
		int year = date.getYear();
		return String.format("%d H.%d", year, half);
	},
	(date) ->{
		return date.plusMonths(6);
	}),YEAR((date) -> {
		return String.valueOf(date.getYear());
	},
	(date) ->{
		return date.plusYears(1);
	});

	private Label label;
	private Next next;

	private Resolution(Label label, Next next) {
		this.label = label;
		this.next = next;
	}

	public String getLabel(LocalDate date) {
		return label.getLabel(date);
	}
	
	public LocalDate next(LocalDate date)
	{
		return next.next(date);
	}
}

interface Label {
	public String getLabel(LocalDate date);
}
interface Next {
	public LocalDate next(LocalDate date);
}
