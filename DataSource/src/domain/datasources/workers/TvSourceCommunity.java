package domain.datasources.workers;

import domain.api.ApiHandler;
import domain.datasources.workers.general.TvSource;

public class TvSourceCommunity extends TvSource {

	public TvSourceCommunity() {
		loadData("318");
		setName("Airdate for Community");
	}
	
	protected TvSourceCommunity(ApiHandler handler) {
		super(handler);
		loadData("318");
		setName("Airdate for Community");
	}

	

}
