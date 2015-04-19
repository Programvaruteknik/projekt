package domain.datasources;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.junit.Before;
import org.junit.Test;

public class DataSourceMergerTest
{

	private DataSource dataSource1, dataSource2;
	
	@Before
	public void setUp() throws Exception
	{
		dataSource1 = mock(DataSource.class);
		dataSource2 = mock(DataSource.class);
		
		TreeMap<LocalDate, Double> deafultTestMap = new TreeMap<>();
		
		deafultTestMap.put(LocalDate.parse("2001-02-01"), 1d);
		deafultTestMap.put(LocalDate.parse("2001-02-03"), 2d);
		deafultTestMap.put(LocalDate.parse("2001-02-05"), 3d);
		
		when(dataSource1.getData()).thenReturn(deafultTestMap);
		
	}

	@Test
	public void testMerge()
	{
		TreeMap<LocalDate, Double> map = new TreeMap<>();

		map.put(LocalDate.parse("2001-02-03"), 4d);
		map.put(LocalDate.parse("2001-02-04"), 5d);
		map.put(LocalDate.parse("2001-02-05"), 6d);
		
		when(dataSource2.getData()).thenReturn(map);
		
		TreeMap<LocalDate, ArrayList<Double>> expectedMap = new TreeMap<>();
		
		ArrayList<Double> a1 = new ArrayList<>();
		a1.add(1d);
		a1.add(null);
		ArrayList<Double> aNull = new ArrayList<>();
		aNull.add(null);
		aNull.add(null);
		ArrayList<Double> a2 = new ArrayList<>();
		a2.add(2d);
		a2.add(4d);
		ArrayList<Double> a3 = new ArrayList<>();
		a3.add(null);
		a3.add(5d);
		ArrayList<Double> a4 = new ArrayList<>();
		a4.add(3d);
		a4.add(6d);
		
		expectedMap.put(LocalDate.parse("2001-02-01"), a1);
		expectedMap.put(LocalDate.parse("2001-02-02"), aNull);
		expectedMap.put(LocalDate.parse("2001-02-03"), a2);
		expectedMap.put(LocalDate.parse("2001-02-04"), a3);
		expectedMap.put(LocalDate.parse("2001-02-05"), a4);

		TreeMap<LocalDate, ArrayList<Double>> actualMap = new DataSourceMerger(dataSource1, dataSource2).merge();	
		
		assertEquals(expectedMap.size(), actualMap.size());
		assertEquals(expectedMap, actualMap);

	}

}
