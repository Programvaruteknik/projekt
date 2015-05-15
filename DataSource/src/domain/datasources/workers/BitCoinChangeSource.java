package domain.datasources.workers;

import java.time.LocalDate;

import domain.api.ApiHandler;
import domain.api.models.bitcoin.Change;
import domain.api.models.bitcoin.ChangeList;
import domain.datasources.workers.general.GenericBitCoinSource;

public class BitCoinChangeSource extends GenericBitCoinSource {

	public BitCoinChangeSource(ApiHandler handler) {
		super(handler);
		setTitle("Change In Canadian Bitcoin Index");
	}

	public BitCoinChangeSource() {
		super();
		setTitle("Change In Canadian Bitcoin Index");
	}

	@Override
	protected void loadData() {
		ChangeList list = handler.get(url, ChangeList.class);
		for(Change c : list.getChanges()){
			data.put(LocalDate.parse(c.getDate()),c.getChange());

		}
	}

}
