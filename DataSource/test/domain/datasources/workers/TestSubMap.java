/**
 * 
 */
package domain.datasources.workers;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.SortedMap;
import java.util.TreeMap;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * See documentation, SortedMap<k,v> i TreeMap<k,v>
 * But method subMap(key1, key2) - returns subMap inclusive key1, exclusive key2
 * Overloaded subMap(key1, boolean inclusive, key2, boolean inclusive) - your choice
 * @author calculator
 *
 */
public class TestSubMap {

	/**
	 * @throws java.lang.Exception
	 */
	private TreeMap<LocalDate, Double> testMap;
	@Before
	public void setUp() throws Exception 
	{
		testMap = new TreeMap<>();
	}

	@Test
	public void testNotNullMap()
	{
		assertNotNull("testMap not null", testMap);
	}
	
	@Test
	public void testSubMapFull()
	{
		LocalDate firstDate = LocalDate.of(2012, 12, 24);
		LocalDate lastDate = LocalDate.of(2016, 12, 24);
		LocalDate middleDate = LocalDate.of(2014, 12, 24); 
		testMap.put(firstDate, 10_000d);
		testMap.put(LocalDate.of(2013, 12, 24), 20_000d);
		testMap.put(middleDate, 30_000d);
		testMap.put(LocalDate.of(2015, 12, 24), 40_000d);
		testMap.put(lastDate, 50_000d);
		
		SortedMap<LocalDate, Double> subMap = testMap.subMap(firstDate, true, lastDate, true);
		assertEquals("FirstDate = first In subMap",firstDate, subMap.firstKey());
		assertEquals("Size equal", testMap.size(),subMap.size());
		 
	}
	
	@Test
	public void testMiddleToLastDate()
	{
		LocalDate lastDate = LocalDate.of(2016, 12, 24);
		LocalDate middleDate = LocalDate.of(2014, 12, 24); 
		testMap.put(LocalDate.of(2012, 12, 24), 10_000d);
		testMap.put(LocalDate.of(2013, 12, 24), 20_000d);
		testMap.put(middleDate, 30_000d);
		testMap.put(LocalDate.of(2015, 12, 24), 40_000d);
		testMap.put(lastDate, 50_000d);
		
		SortedMap<LocalDate, Double> subMap = testMap.subMap(middleDate,true, lastDate, true);
		
		assertEquals("Middle = firstEntry",middleDate, subMap.firstKey());
		assertEquals("Size equal", testMap.size()-2, subMap.size());
	}

}
