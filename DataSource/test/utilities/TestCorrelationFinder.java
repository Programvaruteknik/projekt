package utilities;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Before;
import org.junit.Test;

import domain.matching.DataPair;
import domain.matching.ResultingData;

public class TestCorrelationFinder {
	Map<String, DataPair> map, mapTwo;
	CorrelationFinder finder = new CorrelationFinder();
	ResultingData data, dataTwo;
	ResultingData best;

	@Before
	public void setup() {
		mapTwo = new TreeMap<String, DataPair>();
		map = new TreeMap<String, DataPair>();
		finder = new CorrelationFinder();
		setupFirstMap();
		setupSecondMap(mapTwo);
		setupMock();
		setupResultingData();
	}

	private void setupResultingData() {
		when(data.getData()).thenReturn(map);
		when(dataTwo.getData()).thenReturn(mapTwo);
	}

	private void setupMock() {
		data = mock(ResultingData.class);
		dataTwo = mock(ResultingData.class);
	}

	private void setupFirstMap() {
		map.put("2001-01-01", new DataPair(1.3, 2.2));
		map.put("2001-01-02", new DataPair(2.1, 5.8));
		map.put("2001-01-03", new DataPair(3.7, 10.2));
	}

	private void setupSecondMap(Map<String, DataPair> map) {
		map.put("2001-01-01", new DataPair(1.3, 2.2));
		map.put("2001-01-02", new DataPair(2.1, 5.8));
		map.put("2001-01-03", new DataPair(3.7, 10.2));
		map.put("2001-01-04", new DataPair(4.2, 11.8));
	}

	@Test
	public void testGetRSquare() {
		double expectedR2 = 0.982558;
		when(data.getData()).thenReturn(map);
		assertEquals(expectedR2, finder.getR2(data), 0.001);
	}

	@Test
	public void testGetHighestCorrelation() {
		best = finder.getHighestCorrelation(data, dataTwo);
		assertEquals(dataTwo, best);
	}

	@Test
	public void testGetHighestCorrelationList() {
		ArrayList<ResultingData> list = new ArrayList<>();
		list.add(data);
		list.add(dataTwo);
		best = finder.getHighestCorrelation(list);
		assertEquals(dataTwo, best);
	}
	@Test
	public void testGetHighestCorrelationIndex(){
		ArrayList<ResultingData> list = new ArrayList<>();
		list.add(data);
		list.add(dataTwo);
		int expectedIndex = 1;
		
		best = finder.getHighestCorrelation(list);
		assertEquals(expectedIndex,finder.indexOfHighestCorrelation(list));
	}

}
