package domain.datasources.workers.general;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import domain.api.ApiHandler;
import domain.api.BitCoinChangeApi;
import domain.api.models.bitcoin.Change;
import domain.api.serialization.JsonParser;
import domain.api.url.UrlFetcher;
import domain.datasources.DataSource;
import domain.datasources.model.MetaData;

public class GenericBitCoinSource implements DataSource {
	protected TreeMap<LocalDate, Double> data = new TreeMap<LocalDate, Double>();
	protected ApiHandler handler;
	private String title;
	protected String url = "http://api.cbix.ca/v1/history?limit=100";

	protected TreeMap<LocalDate, Double> getInternalData() {
		return data;
	}

	public GenericBitCoinSource() {
		data = new TreeMap<LocalDate, Double>();
		handler = new ApiHandler(new UrlFetcher(), new JsonParser());
	}

	protected GenericBitCoinSource(ApiHandler handlerParam) {
		data = new TreeMap<LocalDate, Double>();
		handler = handlerParam;
	}

	@Override
	public TreeMap<LocalDate, Double> getData() {
		return data;
	}

	@Override
	public void downLoadDataSource(String fromDate, String toDate) {
		loadData();
		filterOnDates(fromDate, toDate);
	}

	@Override
	public MetaData getMetaData() {
		MetaData metaData;
		metaData = new MetaData();
		metaData.setLicense("");
		metaData.setOwner("cbix");
		metaData.setUrl("https://www.cbix.ca");
		metaData.setTitle(title);
		metaData.setUnit("BTC");
		metaData.setHasData(!data.isEmpty());
		return metaData;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	protected void loadData() {
		List<Change> list = new BitCoinChangeApi(handler).getChanges();

		for (Change change : list) {
			data.put(LocalDate.parse(change.getDate()), change.getChange());
		}
	}

	protected void filterOnDates(String fromDate, String toDate) {
		data = (TreeMap<LocalDate, Double>) filterOnDates(data, fromDate,
				toDate);
	}

	protected Map<LocalDate, Double> filterOnDates(Map<LocalDate, Double> map,
			String fromDate, String toDate) {

		SortedMap<LocalDate, Double> subMap = ((TreeMap<LocalDate, Double>) map)
				.subMap(LocalDate.parse(fromDate), true,
						LocalDate.parse(toDate), true);
		
		Map<LocalDate, Double> filteredMap = toTreeMap(subMap);

		return filteredMap;
	}

	private Map<LocalDate, Double> toTreeMap(SortedMap<LocalDate, Double> subMap) {
		Map<LocalDate, Double> filteredMap = new TreeMap<LocalDate, Double>();
		
		for (Map.Entry<LocalDate, Double> entry : subMap.entrySet()) {
			filteredMap.put(entry.getKey(), entry.getValue());
		}
		return filteredMap;
	}

}
