package domain.datasources;

import java.time.LocalDate;
import java.util.Map;

/**
 * This is an interface which is used to create objects that can retrieve Data
 * from a specific source. E.g: The web, or why not a file on the computer.
 * 
 * @author Rickard Engström
 *
 */
public interface DataSource {
	/**
	 * Returns the name of the Source.
	 * 
	 * @return String The name.
	 */
	public String getName();

	/**
	 * Returns the type of unit which this soure is measured in.
	 * 
	 * @return String The unit.
	 */
	public String getUnit();

	/**
	 * Returns a map representation of the source.
	 * 
	 * @return Map<LocalDate,Double> The map.
	 */
	public Map<LocalDate, Double> getData();

}
