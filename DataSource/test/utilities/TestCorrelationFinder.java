package utilities;

import static org.junit.Assert.assertEquals;

import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;

import domain.matching.DataPair;

public class TestCorrelationFinder {

	@Test
	public void testGetRSquare() {
		CorrelationFinder finder = new CorrelationFinder();
		Map<String, DataPair> map = new TreeMap();
		map.put("2001-01-01", new DataPair(1.3, 2.2));
		map.put("2001-01-02", new DataPair(2.1, 5.8));
		map.put("2001-01-03", new DataPair(3.7, 10.2));
		double expectedR2 = 0.982558;
		assertEquals(expectedR2, finder.getR2(map),0.001);
	}

	@Test
	public void testGetHighestCorrelation(){
		CorrelationFinder finder = new CorrelationFinder();
		
		Map<String,DataPair> map = new TreeMap<String, DataPair>();
		map.put("2001-01-01", new DataPair(1.3, 2.2));
		map.put("2001-01-02", new DataPair(2.1, 5.8));
		map.put("2001-01-03", new DataPair(3.7, 10.2));
		
		Map<String,DataPair> mapTwo = new TreeMap<String, DataPair>();
		map.put("2001-01-01", new DataPair(1.3, 2.2));
		map.put("2001-01-02", new DataPair(2.1, 5.8));
		map.put("2001-01-03", new DataPair(3.7, 10.2));
		map.put("2001-01-04", new DataPair(4.2, 11.8));
		
		Map<String,DataPair> best = finder.getHighestCorrelation(map,mapTwo);
		assertEquals(mapTwo,best);
	}
	
}
