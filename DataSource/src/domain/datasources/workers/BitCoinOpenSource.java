package domain.datasources.workers;

import java.time.LocalDate;

import domain.api.ApiHandler;
import domain.api.models.bitcoin.Open;
import domain.api.models.bitcoin.OpenList;
import domain.datasources.workers.general.GenericBitCoinSource;

public class BitCoinOpenSource extends GenericBitCoinSource {
	
	public BitCoinOpenSource(ApiHandler handler) {
		super(handler);
		setTitle("Open values In Canadian Bitcoin Index");
	}

	public BitCoinOpenSource() {
		super();
		setTitle("Open values In Canadian Bitcoin Index");
	}

	@Override
	protected void loadData(){
		OpenList list = handler.get(url, OpenList.class);
		for(Open o : list.getOpeningValues()){
			data.put(LocalDate.parse(o.getDate()),o.getOpenValue());
		}
	}
}
