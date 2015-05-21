package domain.utility;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.crypto.Data;
import javax.xml.transform.Source;

import org.junit.Before;
import org.junit.Test;

import com.sun.javafx.css.CalculatedValue;

import domain.datasources.DataSource;

public class TestDataSourceCalculator {
	TreeMap<LocalDate, Double> map;
	DataSource source;
	double expectedAverage;
	double expectedSum;

	@Before
	public void setup() {
		map = new TreeMap<LocalDate, Double>();
	}

	@Test
	public void testGetAverage() {
		populateMap();
		source = mock(DataSource.class);
		when(source.getData()).thenReturn(map);
		expectedAverage = 1.66666;
		assertEquals(expectedAverage, DataSourceCalculator.getAverage(source), 0.01);
	}

	private void populateMap() {
		map.put(LocalDate.parse("2001-01-01"), 1d);
		map.put(LocalDate.parse("2001-01-02"), 1d);
		map.put(LocalDate.parse("2001-01-03"), 3d);
	}

	@Test
	public void testGetAverageNoValues() {
		source = mock(DataSource.class);
		when(source.getData()).thenReturn(map);
		expectedAverage = 0;
		assertEquals(expectedAverage, DataSourceCalculator.getAverage(source), 0.01);
	}

	@Test
	public void testGetSum() {
		populateMap();
		source = mock(DataSource.class);
		when(source.getData()).thenReturn(map);
		expectedSum = 5;
		assertEquals(expectedSum, DataSourceCalculator.getSum(source), 0.01);
	}

	@Test
	public void testGetSumNoValues() {
		source = mock(DataSource.class);
		when(source.getData()).thenReturn(map);
		expectedSum = 0;
		assertEquals(expectedSum, DataSourceCalculator.getSum(source), 0.01);
	}

}
