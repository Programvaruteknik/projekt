package domain.datasources;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.TreeMap;

import org.junit.Before;
import org.junit.Test;

public class DataSourceFormatterTest
{

	private DataSource dataSource;
	
	@Before
	public void setUp() throws Exception
	{
		dataSource = mock(DataSource.class);
		
		TreeMap<LocalDate, Double> deafultTestMap = new TreeMap<>();
		
		deafultTestMap.put(LocalDate.parse("2001-02-01"), 1d);
		deafultTestMap.put(LocalDate.parse("2001-02-03"), 2d);
		
		when(dataSource.getData()).thenReturn(deafultTestMap);
	}

	@Test
	public void testToMergeableFormat()
	{
		TreeMap<LocalDate, ArrayList<Double>> expected = new TreeMap<>();
		
		ArrayList<Double> a1 = new ArrayList<>();
		a1.add(1d);
		ArrayList<Double> a2 = new ArrayList<>();
		a2.add(2d);

		
		expected.put(LocalDate.parse("2001-02-01"), a1);
		expected.put(LocalDate.parse("2001-02-03"), a2);
		
		assertEquals(expected, new DataSourceFormatter(dataSource).toMergeableFormat());
	}

}
