package domain.datasources;

import java.time.LocalDate;
import java.util.TreeMap;

import domain.datasources.model.MetaData;

/**
 * This is an interface which is used to create objects that can retrieve Data
 * from a specific source. E.g: The web, or why not a file on the computer.
 * 
 * @author Rickard Engström
 *
 */
public interface DataSource {
	/**
	 * Returns a map representation of the source.
	 * 
	 * @return Map<LocalDate,Double> The map.
	 */
	public TreeMap<LocalDate, Double> getData();
	public void downLoadDataSource(String fromDate, String toDate);

	public MetaData getMetaData();


}
