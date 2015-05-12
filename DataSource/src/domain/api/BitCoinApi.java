package domain.api;

import java.util.List;

import domain.api.models.bitcoin.Change;
import domain.api.models.bitcoin.ChangeList;

public class BitCoinApi {
	private ApiHandler handler;

	public BitCoinApi(ApiHandler handlerParam) {
		handler = handlerParam;
	}

	public List<Change> getChanges() {
		ChangeList changes = handler.get(
				"http://api.cbix.ca/v1/history?limit=100", ChangeList.class);
		return changes.getChanges();
	}

}
