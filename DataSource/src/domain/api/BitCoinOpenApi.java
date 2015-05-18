package domain.api;

import java.util.List;

import domain.api.models.bitcoin.ChangeList;
import domain.api.models.bitcoin.Open;
import domain.api.models.bitcoin.OpenList;

public class BitCoinOpenApi {
	private ApiHandler handler;

	public BitCoinOpenApi(ApiHandler handlerParam) {
		handler = handlerParam;
	}

	public List<Open> getChanges() {
		OpenList openList = handler.get(
				"http://api.cbix.ca/v1/history?limit=100", OpenList.class);
		return openList.getOpeningValues();
	}
}
