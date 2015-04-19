package domain.datasources;

import java.time.LocalDate;
import java.util.TreeMap;

/**
 * 
 * @author Rickard Engström
 *
 */
public interface DataSource
{
	public String getName();

	public String getUnit();

	public TreeMap<LocalDate, Double> getData();

}
