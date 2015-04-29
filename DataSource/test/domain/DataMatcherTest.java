package domain;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import domain.datasources.DataSource;
import domain.datasources.model.MetaData;
import domain.matching.DataMatcher;
import domain.matching.DataPair;
import domain.matching.Resolution;
import domain.matching.ResultingData;

public class DataMatcherTest {

	private DataSource sourceX = new DataSource() {

		@Override
		public String getUnit() {
			return "mm";
		}

		@Override
		public TreeMap<LocalDate, Double> getData() {

			TreeMap<LocalDate, Double> tmpMap = new TreeMap<>();
			tmpMap.put(LocalDate.parse("2015-01-01"), 3d);
			tmpMap.put(LocalDate.parse("2015-01-03"), 10d);
			tmpMap.put(LocalDate.parse("2015-01-05"), 0d);
			tmpMap.put(LocalDate.parse("2015-01-07"), 0.3);
			tmpMap.put(LocalDate.parse("2015-01-09"), 6d);

			return tmpMap;
		}

		@Override
		public MetaData getMetaData() {
			MetaData meta = new MetaData();
			
			meta.setTitle("Nederbörd");
			return meta;
		}
	};

	private DataSource sourceY = new DataSource() {

		@Override
		public String getUnit() {
			return "Kelvin";
		}

		@Override
		public TreeMap<LocalDate, Double> getData() {
			TreeMap<LocalDate, Double> tmpMap = new TreeMap<>();
			tmpMap.put(LocalDate.parse("2015-01-01"), 255d);
			tmpMap.put(LocalDate.parse("2015-01-04"), 10d);
			tmpMap.put(LocalDate.parse("2015-01-07"), 272d);

			return tmpMap;
		}

		@Override
		public MetaData getMetaData() {
			MetaData meta = new MetaData();
			meta.setTitle("Temperatur");
			return meta;
		}
	};

	@Test
	public void testMatch() {
		ResultingData result = new DataMatcher(sourceX, sourceY, Resolution.DAY)
				.match();

		Map<String, DataPair> resoultingData = result.getData();

		assertEquals(2, resoultingData.size());
		assertEquals(new Double(3d), resoultingData.get("2015-01-01").getX());
		assertEquals(new Double(255d), resoultingData.get("2015-01-01").getY());

		resoultingData = new DataMatcher(sourceX, sourceY, Resolution.WEEK)
				.match().getData();

		assertEquals(2, resoultingData.size());
		assertEquals(new Double(6.5), resoultingData.get("2015 V.1").getX());
		assertEquals(new Double(132.5), resoultingData.get("2015 V.1").getY());
		assertEquals(new Double(2.1), resoultingData.get("2015 V.2").getX());
		assertEquals(new Double(272d), resoultingData.get("2015 V.2").getY());
		
		String expectedSrc1Name = "Nederbörd";
		String expectedSrc2Name = "Temperatur";
		//
		
		assertEquals(expectedSrc1Name, result.getXMeta().getName());
		assertEquals(expectedSrc2Name, result.getYMeta().getName());

	}

}
