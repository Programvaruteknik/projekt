package collections;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataChart {
	private List<List<Object>> table;
	private Map<LocalDate, Double> map;

	public DataChart(Map<LocalDate, Double> data) {
		this.map = data;
	}

	public List<List<Object>> getTable() {
		return toChart(map);
	}

	protected List<List<Object>> toChart(Map<LocalDate, Double> map) {
		List<List<Object>> table = new ArrayList<>();
		for (LocalDate date : map.keySet()) {
			List<Object> row = new ArrayList<Object>();
			row.add(date.toString());
			row.add(map.get(date));
			table.add(row);
		}
		return table;
	}

	protected List<Object> getRow(int i) {
		return (List<Object>) table.get(i);
	}

}