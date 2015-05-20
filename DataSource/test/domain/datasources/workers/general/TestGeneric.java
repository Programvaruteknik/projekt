package domain.datasources.workers.general;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;

public class TestGeneric {
	
	@Test
	public void testFilterOndates(){
		Map<LocalDate,Double> map = new TreeMap(); 
		map.put(LocalDate.parse("2001-01-01"), 1d);
		map.put(LocalDate.parse("2001-01-02"), 2d);
		map.put(LocalDate.parse("2001-01-03"), 3d);
		
		GenericBitCoinSource source = new GenericBitCoinSource();
		String fromDate ="2001-01-02";
		String toDate = "2001-01-03";
		Map<LocalDate,Double> newMap = source.filterOnDates(map,fromDate,toDate);
		assertEquals(2, newMap.size());
	}

}
