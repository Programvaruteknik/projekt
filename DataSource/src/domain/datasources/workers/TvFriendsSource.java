package domain.datasources.workers;

import domain.api.ApiHandler;
import domain.datasources.workers.general.TvSource;

public class TvFriendsSource extends TvSource {
	
	public TvFriendsSource() {
		super("431");
		setName("Airdate for Friends");
	}
	
	protected TvFriendsSource(ApiHandler handler) {
		super(handler, "431");
		setName("Airdate for Friends");
	}

}