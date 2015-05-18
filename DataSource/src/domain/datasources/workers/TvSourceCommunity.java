package domain.datasources.workers;

import domain.api.ApiHandler;
import domain.datasources.workers.general.TvSource;

public class TvSourceCommunity extends TvSource {

	public TvSourceCommunity() {
		super("318");
		setName("Airdate for Community");
	}
	
	protected TvSourceCommunity(ApiHandler handler) {
		super(handler, "318");
		setName("Airdate for Community");
	}

	

}
