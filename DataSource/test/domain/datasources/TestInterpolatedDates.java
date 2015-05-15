package domain.datasources;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.Period;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.junit.Before;
import org.junit.Test;

import domain.matching.Resolution;

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
		map.put(LocalDate.parse("2001-10-07"), 7.0);
		map.put(LocalDate.parse("2001-01-07"), 7.0);

		int distance = interpolator.getPeriod(sc, Resolution.DAY);
		assertEquals(true,distance > 200);
	}


	@Test
	public void testFilloutMissingDays() {
		map.clear();
		map.put(LocalDate.parse("2014-03-30"), 15.0);
		map.put(LocalDate.parse("2014-03-31"), 7.0);
		map.put(LocalDate.parse("2014-04-04"), 4.0);
		map.put(LocalDate.parse("2014-04-05"), 1.0);
		DataSource src = interpolator.fillOutMissingDays(sc, Resolution.DAY);
		TreeMap<LocalDate, Double> mappen = src.getData();
		
		assertEquals(7, mappen.size());
	}

}
