package domain.datasources.modulateing;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.TreeMap;

import org.junit.Before;
import org.junit.Test;

import domain.datasources.DataSource;
import domain.datasources.model.MetaData;

public class DateModelatorTest
{
	private DataSource testDatasource;

	@Before
	public void setUp() throws Exception
	{
		testDatasource = new DataSource()
		{
		
			@Override
			public TreeMap<LocalDate, Double> getData()
			{
				TreeMap<LocalDate, Double> output = new TreeMap<>();
				
				output.put(LocalDate.parse("2015-02-01"), 10d);
				output.put(LocalDate.parse("2015-02-02"), 10d);
				
				return output;
			}

			@Override
			public MetaData getMetaData()
			{
				return new MetaData();
			}

			@Override
			public void downLoadDataSource(String fromDate, String toDate) {
				// TODO Auto-generated method stub
				
			}
		};
	}

	@Test
	public void testExecuteWithPositiveValues()
	{
		DateModelator dateModelator = new DateModelator(testDatasource, 1, 1, 1);
		
		TreeMap<LocalDate, Double> expected = new TreeMap<>();
		
		expected.put(LocalDate.parse("2016-03-02"), 10d);
		expected.put(LocalDate.parse("2016-03-03"), 10d);
		
		
		
		assertEquals(expected.keySet(), dateModelator.execute().getData().keySet());
	}
	
	@Test
	public void testExecuteWithNegativeValues()
	{
		DateModelator dateModelator = new DateModelator(testDatasource, -1, -1, -1);
		
		TreeMap<LocalDate, Double> expected = new TreeMap<>();
		
		expected.put(LocalDate.parse("2013-12-31"), 10d);
		expected.put(LocalDate.parse("2014-01-01"), 10d);

		assertEquals(expected.keySet(), dateModelator.execute().getData().keySet());
	}
	
	@Test
	public void testMetaData()
	{
		assertNull(testDatasource.getMetaData().getModList());
		DateModelator dateModelator = new DateModelator(testDatasource, 1, 1, 1);
		testDatasource = dateModelator.execute();
		assertEquals(1, testDatasource.getMetaData().getModList().size());
		dateModelator = new DateModelator(testDatasource, 1, 1, 1);
		testDatasource = dateModelator.execute();
		assertEquals(2, testDatasource.getMetaData().getModList().size());
		
	}

}
