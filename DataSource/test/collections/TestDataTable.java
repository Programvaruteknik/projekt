package collections;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import collections.DataChart;
import domain.api.serialization.JsonParser;

public class TestDataTable {
	DataChart source;
	Map<LocalDate, Double> data;

	@Before
	public void setup() {
		data = new HashMap<>();

		data.put(LocalDate.parse("2001-01-01"), new Double(1.0));
		data.put(LocalDate.parse("2001-01-02"), new Double(2.0));

		source = new DataChart(data);
	}

	@Test
	public void testCreateRows() {
		List<List<Object>> rows = source.toChart(data);
		List<Object> rowOne = rows.get(1);
		List<Object> rowTwo = rows.get(0);
		
		Object[] expected = { "2001-01-01", new Double(1.0) };
		Object[] expectedTwo = { "2001-01-02", new Double(2.0) };
		
		assertArrayEquals(expected, rowOne.toArray());
		assertArrayEquals(expectedTwo, rowTwo.toArray());
		
	}
	
	
}