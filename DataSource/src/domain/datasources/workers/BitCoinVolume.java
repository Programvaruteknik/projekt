package domain.datasources.workers;

import java.time.LocalDate;
import java.util.TreeMap;

import domain.api.ApiHandler;
import domain.api.models.bitcoin.Volume;
import domain.api.models.bitcoin.VolumeList;
import domain.api.serialization.JsonParser;
import domain.api.url.UrlFetcher;
import domain.datasources.DataSource;
import domain.datasources.model.MetaData;

public class BitCoinVolume implements DataSource {
	private ApiHandler handler;
	private TreeMap<LocalDate,Double> map = new TreeMap<LocalDate,Double>();
	
	public BitCoinVolume(){
		this.handler = new ApiHandler(new UrlFetcher(), new JsonParser());
		
	}
	
	protected BitCoinVolume(ApiHandler hand) {
		this.handler = hand;
		loadData();
	}
	
	
	@Override
	public TreeMap<LocalDate, Double> getData() {
		return map;
	}

	@Override
	public MetaData getMetaData() {
		MetaData meta = new MetaData();
		meta.setHasData(!map.isEmpty());
		meta.setLicense("");
		meta.setOwner("cbix");
		meta.setTitle("Bitcoin volume");
		meta.setUrl("https://www.cbix.ca");
		meta.setUnit("BTC");
		return meta;
	}

	@Override
	public void downLoadDataSource(String fromDate, String toDate) {
		loadData();
		
	}

	private void loadData(){
		VolumeList list = handler.get("http://api.cbix.ca/v1/history?limit=5000", VolumeList.class);
		if(list != null)
		{
			for(Volume v : list.getChanges()){
				map.put(LocalDate.parse(v.getDate()), v.getVolume());
			}
		}
	}
	
	
}
