package domain;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import domain.matching.Resolution;

public class ResolutionTest
{

	@Test
	public void testGetLabel()
	{
		assertEquals("2015-01-01", Resolution.DAY.getLabel(LocalDate.parse("2015-01-01")));
		assertEquals("2015 V.1", Resolution.WEEK.getLabel(LocalDate.parse("2015-01-01")));
		assertEquals("2015 V.53", Resolution.WEEK.getLabel(LocalDate.parse("2015-12-31")));
		assertEquals("2016 V.53", Resolution.WEEK.getLabel(LocalDate.parse("2017-01-01")));
		assertEquals("2015 JANUARY", Resolution.MONTH.getLabel(LocalDate.parse("2015-01-01")));
		assertEquals("2015 K.1", Resolution.QUARTER.getLabel(LocalDate.parse("2015-01-01")));
		assertEquals("2015", Resolution.YEAR.getLabel(LocalDate.parse("2015-01-01")));

	}
	
	@Test
	public void testNext()
	{
		assertEquals(LocalDate.parse("2015-01-02"), Resolution.DAY.next(LocalDate.parse("2015-01-01")));
		assertEquals(LocalDate.parse("2015-01-08"), Resolution.WEEK.next(LocalDate.parse("2015-01-01")));
		assertEquals(LocalDate.parse("2015-02-01"), Resolution.MONTH.next(LocalDate.parse("2015-01-01")));
		assertEquals(LocalDate.parse("2015-04-01"), Resolution.QUARTER.next(LocalDate.parse("2015-01-01")));
		assertEquals(LocalDate.parse("2016-01-01"), Resolution.YEAR.next(LocalDate.parse("2015-01-01")));
	}
	

}
