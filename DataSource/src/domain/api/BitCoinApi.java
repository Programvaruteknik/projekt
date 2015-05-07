package domain.api;

import java.util.List;

import domain.api.models.bitcoin.Change;
import domain.api.models.bitcoin.ChangeList;
import domain.api.serialization.JsonParser;
import domain.api.url.UrlFetcher;

public class BitCoinApi {
	ApiHandler handler;

	public BitCoinApi() {
		handler = new ApiHandler(new UrlFetcher(), new JsonParser());
	}

	public List<Change> getChanges() {
		ChangeList changes = handler.get(
				"http://api.cbix.ca/v1/history?limit=100", ChangeList.class);
		return changes.getChanges();
	}



	public static void main(String[] args) {
		List<Change> list = new BitCoinApi().getChanges();
		for (Change c : list) {
			System.out.print(c.getDate() + "\t");
			System.out.println(c.getChange());
		}
	}
}
