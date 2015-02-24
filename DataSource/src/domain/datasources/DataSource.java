package domain.datasources;

import java.time.LocalDate;
import java.util.Map;

/**
 * 
 * @author Rickard Engström
 *
 */
public interface DataSource
{
	public String getName();

	public String getUnit();

	public Map<LocalDate, Double> getData();

}
