package domain.datasources.workers;

import java.time.LocalDate;
import java.util.List;
import java.util.TreeMap;

import domain.api.ApiHandler;
import domain.api.BitCoinOpenApi;
import domain.api.models.bitcoin.Change;
import domain.api.models.bitcoin.Open;
import domain.api.serialization.JsonParser;
import domain.api.url.UrlFetcher;
import domain.datasources.DataSource;
import domain.datasources.model.MetaData;

public class BitCoinOpenSource implements DataSource {
	private TreeMap<LocalDate, Double> data;
	private ApiHandler handler;

	public BitCoinOpenSource() {
		data = new TreeMap<LocalDate, Double>();
		handler = new ApiHandler(new UrlFetcher(), new JsonParser());
		loadData();
	}

	protected BitCoinOpenSource(ApiHandler handler) {
		data = new TreeMap<LocalDate, Double>();
		this.handler = handler;
		List<Open> list = new BitCoinOpenApi(handler).getChanges();
		loadData();
	}

	private void loadData() {
		List<Open> list = new BitCoinOpenApi(handler).getChanges();
		for (Open change : list) {
			data.put(LocalDate.parse(change.getDate()), change.getOpenValue());
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
		metaData.setTitle("Open values In Canadian Bitcoin Index");
		metaData.setUnit("BTC");
		metaData.setHasData(!data.isEmpty());
		return metaData;
	}

}
