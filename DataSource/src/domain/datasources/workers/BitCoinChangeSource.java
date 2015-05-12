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
	private ApiHandler handler;

	public BitCoinChangeSource() {
		data = new TreeMap<LocalDate, Double>();
		handler = new ApiHandler(new UrlFetcher(), new JsonParser());
		loadData();
	}

	protected BitCoinChangeSource(ApiHandler handlerParam) {
		data = new TreeMap<LocalDate, Double>();
		handler = handlerParam;
		loadData();
	}

	private void loadData() {
		List<Change> list = new BitCoinChangeApi(handler).getChanges();
		
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
		metaData.setOwner("cbix");
		metaData.setUrl("https://www.cbix.ca");
		metaData.setTitle("Change In Canadian Bitcoin Index");
		metaData.setUnit("BTC");
		metaData.setHasData(!data.isEmpty());
		return metaData;
	}

}
