package domain.datasources.workers;

import java.time.LocalDate;
import java.util.List;
import java.util.TreeMap;

import domain.api.ApiHandler;
import domain.api.BitCoinChangeApi;
import domain.api.models.bitcoin.Change;
import domain.api.serialization.JsonParser;
import domain.api.url.UrlFetcher;
import domain.datasources.DataSource;
import domain.datasources.model.MetaData;

public class BitCoinChangeSource implements DataSource {
	private TreeMap<LocalDate, Double> data;
	ApiHandler handler;

	public BitCoinChangeSource() {
		data = new TreeMap<LocalDate, Double>();
		handler = new ApiHandler(new UrlFetcher(), new JsonParser());
		List<Change> list = new BitCoinChangeApi(handler).getChanges();
		insertInMap(list);
	}

	protected BitCoinChangeSource(ApiHandler handlerParam) {
		data = new TreeMap<LocalDate, Double>();
		handler = handlerParam;
		List<Change> list = new BitCoinChangeApi(handler).getChanges();
		insertInMap(list);
	}

	private void insertInMap(List<Change> list) {
		for (Change change : list) {
			data.put(LocalDate.parse(change.getDate()), change.getChange());
		}
	}

	@Override
	public TreeMap<LocalDate, Double> getData() {
		return data;
	}

	@Override
	public MetaData getMetaData() {
		MetaData metaData;
		metaData = new MetaData();
		metaData.setLicense("");
		metaData.setOwner("cbix.ca");
		metaData.setUrl("https://www.cbix.ca");
		metaData.setTitle("Change In Canadian Bitcoin Index");
		metaData.setUnit("BTC");
		metaData.setHasData(!data.isEmpty());
		return metaData;
	}

	@Override
	public TreeMap<LocalDate, Double> getData(String fromDate, String toDate) {
		// TODO Auto-generated method stub
		return null;
	}

}
