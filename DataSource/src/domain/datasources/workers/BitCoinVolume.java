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

	protected void loadData(){
		VolumeList list = handler.get("http://api.cbix.ca/v1/history?limit=5000", VolumeList.class);
		if(list != null)
		{
			for(Volume v : list.getChanges()){
				data.put(LocalDate.parse(v.getDate()), v.getVolume());
			}
		}
	}
	
	
}
