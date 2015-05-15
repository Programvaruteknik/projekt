package domain.datasources.workers;

import java.time.LocalDate;
import java.util.TreeMap;

import domain.api.ApiHandler;
import domain.api.models.bitcoin.Volume;
import domain.api.models.bitcoin.VolumeList;
import domain.datasources.workers.general.GenericBitCoinSource;

public class BitCoinVolume extends GenericBitCoinSource {
	
	public BitCoinVolume(ApiHandler handler) {
		super(handler);
		setTitle("Bitcoin volume");
	}

	public BitCoinVolume() {
		super();
		setTitle("Bitcoin volume");
	}

	@Override
	protected void loadData(){
		VolumeList list = handler.get(url, VolumeList.class);
		for(Volume v : list.getChanges()){
			data.put(LocalDate.parse(v.getDate()), v.getVolume());
		}
	}
	
	@Override
	public TreeMap<LocalDate, Double> getData() {
		return data;
	}

}
