package domain.datasources;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Before;
import org.junit.Test;

public class TestInterpolatedDates {
	// Hur många datum som ska sättas in
	// Färdig interpolerad lista.

	TreeMap<LocalDate, Double> map = new TreeMap<>();
	Interpolator interpolator;
	String expectedDate;
	DataSource sc = mock(DataSource.class);
	
	@Before
	public void setup() {
		when(sc.getData()).thenReturn(map);
		map.put(LocalDate.parse("2001-01-02"), 2.0);
		map.put(LocalDate.parse("2001-01-01"), 1.0);

		interpolator = new Interpolator();
	}

	@Test
	public void tesGetFirst() {
		expectedDate = "2001-01-01";
		assertEquals(LocalDate.parse(expectedDate), interpolator.getFirst(sc));
	}

	@Test
	public void testGetLast() {
		expectedDate = "2001-01-02";
		assertEquals(LocalDate.parse(expectedDate), interpolator.getLast(sc));
	}

	@Test
	public void testGetDistanceInDays() {
		int expectedDistance = 6;
		map.put(LocalDate.parse("2001-01-07"), 7.0);
		
		int distance = interpolator.getPeriod(sc);
		assertEquals(expectedDistance, distance);
	}

	@Test
	public void testFillOutMissingDays() {
		LocalDate nullDate = LocalDate.parse("2001-01-03");
		LocalDate endDate = LocalDate.parse("2001-01-04");
		map.put(endDate, 4.0);
		
		DataSource newSrc = interpolator.fillOutMissingDays(sc);
		
		Map<LocalDate,Double> mapInSource = newSrc.getData();
		assertEquals(null, mapInSource.get(nullDate));
		assertEquals(4, mapInSource.size());
	}
	
}
