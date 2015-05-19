package utilities;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Before;
import org.junit.Test;

import domain.datasources.DataSource;
import domain.datasources.model.MetaData;
import domain.datasources.modulateing.DateModelator;
import domain.matching.ResultingData;

public class TestPhaseShifter {
	TreeMap<LocalDate, Double> map;
	DataSource src;
	PhaseShifter shifter;
	ArrayList<DataSource> list;

	@Before
	public void setup() {
		list = new ArrayList<DataSource>();
		map = new TreeMap<>();
		map.put(LocalDate.parse("2001-01-05"), 2d);
		src = mock(DataSource.class);
		shifter = new PhaseShifter();
		when(src.getData()).thenReturn(map);
		when(src.getMetaData()).thenReturn(new MetaData());
	}

	@Test
	public void testShiftDays() {
		list = shifter.shiftDays(src, 2);
		assertEquals(2, list.size());

		assertEquals(list.get(0).getData().get(LocalDate.parse("2001-01-06"))
				.doubleValue(), 2d, 0.1);
		assertEquals(list.get(1).getData().get(LocalDate.parse("2001-01-07"))
				.doubleValue(), 2d, 0.1);
	}

	@Test
	public void testShiftNegativeDays() {
		list = shifter.shiftDays(src, -2);
		assertEquals(2, list.size());

		assertEquals(list.get(0).getData().get(LocalDate.parse("2001-01-04"))
				.doubleValue(), 2d, 0.1);
		assertEquals(list.get(1).getData().get(LocalDate.parse("2001-01-03"))
				.doubleValue(), 2d, 0.1);
	}

	@Test
	public void testShiftGetResultList() {
		DataSource compareSource = mock(DataSource.class);
		when(compareSource.getMetaData()).thenReturn(new MetaData());
		ArrayList<ResultingData> list = shifter.shiftDaysResultList(compareSource,src, 2);
		int expectedSize = 2;
		assertEquals(expectedSize, list.size());
	}
}
