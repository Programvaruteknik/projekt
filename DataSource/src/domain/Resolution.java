package domain;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Locale;

public enum Resolution
{
	DAY((date) ->
	{
		return date.toString();
	}), WEEK((date) ->
	{
		WeekFields weekFields = WeekFields.of(Locale.UK);
		int weekNumber = date.get(weekFields.weekOfYear());
		int year = date.getYear();

		if (weekNumber == 0)
		{
			year--;
			weekNumber = 53;
		}

		return String.format("%d V.%d", year, weekNumber);
	}), MONTH((date) ->
	{
		String month = date.getMonth().name();
		int year = date.getYear();

		return String.format("%d %s", year, month);
	}), QUARTER((date) ->
	{
		int quater = date.getMonthValue() / 4 + 1;
		int year = date.getYear();
		return String.format("%d K.%d", year, quater);
	}), YEAR((date) ->
	{
		return String.valueOf(date.getYear());
	});

	private Label label;

	private Resolution(Label label)
	{
		this.label = label;
	}

	public String getLabel(LocalDate date)
	{
		return label.getLabel(date);
	}
}

interface Label
{
	public String getLabel(LocalDate date);
}
