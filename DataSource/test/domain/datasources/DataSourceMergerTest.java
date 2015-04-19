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

	private TreeMap<LocalDate, ArrayList<Double>> dataSource1, dataSource2;
	
	@Before
	public void setUp() throws Exception
	{
		dataSource1 = new TreeMap<LocalDate, ArrayList<Double>>();
		dataSource2 = new TreeMap<LocalDate, ArrayList<Double>>();

		ArrayList<Double> a1 = new ArrayList<>();
		a1.add(1d);
		dataSource1.put(LocalDate.parse("2001-02-01"), a1);
		ArrayList<Double> a2 = new ArrayList<>();
		a2.add(2d);
		dataSource1.put(LocalDate.parse("2001-02-03"), a2);
		ArrayList<Double> a3 = new ArrayList<>();
		a3.add(3d);
		dataSource1.put(LocalDate.parse("2001-02-05"), a3);
		
		
	}

	@Test
	public void testMerge()
	{
		TreeMap<LocalDate, Double> map = new TreeMap<>();

		ArrayList<Double> b1 = new ArrayList<>();
		b1.add(4d);
		dataSource2.put(LocalDate.parse("2001-02-03"), b1);
		ArrayList<Double> b2 = new ArrayList<>();
		b2.add(5d);
		dataSource2.put(LocalDate.parse("2001-02-04"), b2);
		ArrayList<Double> b3 = new ArrayList<>();
		b3.add(6d);
		dataSource2.put(LocalDate.parse("2001-02-05"), b3);
		
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
