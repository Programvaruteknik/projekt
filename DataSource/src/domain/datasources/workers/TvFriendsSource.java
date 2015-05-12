package domain.datasources.workers;

import domain.api.ApiHandler;
import domain.datasources.workers.general.TvSource;

public class TvFriendsSource extends TvSource {
	
	public TvFriendsSource() {
		loadData("431");
		setName("Airdate for Friends");
	}
	
	protected TvFriendsSource(ApiHandler handler) {
		super(handler);
		loadData("431");
		setName("Airdate for Friends");
	}

}