package domain.datasources;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import domain.api.serialization.JsonParser;

public class TestInterpolatedDates {
	// Hur många datum som ska sättas in
	// Färdig interpolerad lista.

	Map<LocalDate, Double> map = new TreeMap<>();
	Interpolator interpolator;
	String expectedDate;

	@Before
	public void setup() {
		map.put(LocalDate.parse("2001-01-02"), 2.0);
		map.put(LocalDate.parse("2001-01-01"), 1.0);

		interpolator = new Interpolator();
	}

	@Test
	public void tesGetFirst() {
		expectedDate = "2001-01-01";
		assertEquals(LocalDate.parse(expectedDate), interpolator.getFirst(map));
	}

	@Test
	public void testGetLast() {
		expectedDate = "2001-01-02";
		assertEquals(LocalDate.parse(expectedDate), interpolator.getLast(map));
	}

	@Test
	public void testGetDistanceInDays() {
		int expectedDistance = 6;
		map.put(LocalDate.parse("2001-01-07"), 7.0);
		int distance = interpolator.getPeriod(map);
		assertEquals(expectedDistance, distance);
	}

	@Test
	public void testFillOutMissingDays() {
		LocalDate nullDate = LocalDate.parse("2001-01-03");
		LocalDate endDate = LocalDate.parse("2001-01-04");
		map.put(endDate, 4.0);
		interpolator.fillOutMissingDays(map);
		assertEquals(null, map.get(nullDate));
		assertEquals(4, map.size());
	}
	
}
