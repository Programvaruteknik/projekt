package domain.api;

import java.util.List;

import domain.api.models.bitcoin.Change;
import domain.api.models.bitcoin.ChangeList;

public class BitCoinChangeApi {
	private ApiHandler handler;

	public BitCoinChangeApi(ApiHandler handlerParam) {
		handler = handlerParam;
	}

	public List<Change> getChanges() {
		ChangeList changes = handler.get(
				"http://api.cbix.ca/v1/history?limit=100", ChangeList.class);
		return changes.getChanges();
	}

}
