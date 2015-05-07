package domain.datasources.workers;

import java.time.LocalDate;
import java.util.List;
import java.util.TreeMap;

import domain.api.BitCoinApi;
import domain.api.models.bitcoin.Change;
import domain.datasources.DataSource;
import domain.datasources.model.MetaData;

public class BitCoin implements DataSource {
	private TreeMap<LocalDate, Double> data;

	public BitCoin() {
		data = new TreeMap<LocalDate, Double>();
		List<Change> list = new BitCoinApi().getChanges();
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
		metaData.setTitle("Change In Canadian BitCoin Index");
		metaData.setUnit("BTC");
		metaData.setHasData(!data.isEmpty());
		return metaData;
	}

}
