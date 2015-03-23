package domain.matching;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import domain.datasources.DataSource;

public class DataMatcher
{
	private final DataSource dataSourceX, dataSourceY;
	private final Resolution resolution;

	public DataMatcher(DataSource dataSourceX, DataSource dataSourceY, Resolution resolution)
	{
		super();
		this.dataSourceX = dataSourceX;
		this.dataSourceY = dataSourceY;
		this.resolution = resolution;
	}

	public ResultingData match()
	{
		Map<String, Double> normalizedX = normalizeMap(dataSourceX.getData());
		Map<String, Double> normalizedY = normalizeMap(dataSourceY.getData());

		Map<String, DataPair> resultMap = new HashMap<String, DataPair>();

		for (Entry<String, Double> entry : normalizedX.entrySet())
		{
			Double tmp = normalizedY.get(entry.getKey());
			if (tmp != null)
			{
				resultMap.put(entry.getKey().toString(), new DataPair(entry.getValue(), tmp));
			}
		}

		return new ResultingData(resultMap);
	}

	private Map<String, Double> normalizeMap(Map<LocalDate, Double> input)
	{
		Map<String, Double> output = new HashMap<String, Double>();
		Map<String, Map<LocalDate, Double>> tmp = new HashMap<String, Map<LocalDate, Double>>();

		for (Entry<LocalDate, Double> entry : input.entrySet())
		{
			String tmpKey = resolution.getLabel(entry.getKey());

			if (tmp.get(tmpKey) == null)
			{
				tmp.put(tmpKey, new HashMap<LocalDate, Double>());
			}
			tmp.get(tmpKey).put(entry.getKey(), entry.getValue());

		}

		for (Entry<String, Map<LocalDate, Double>> entry : tmp.entrySet())
		{
			Double sum = 0d;
			for (Entry<LocalDate, Double> entry2 : entry.getValue().entrySet())
			{
				sum += entry2.getValue();
			}

			output.put(entry.getKey(), sum / entry.getValue().size());
		}

		return output;

	}

}
