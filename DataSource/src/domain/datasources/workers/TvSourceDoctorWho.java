package domain.datasources.workers;

import domain.api.ApiHandler;
import domain.datasources.workers.general.TvSource;


public class TvSourceDoctorWho extends TvSource {

	public TvSourceDoctorWho() {
		loadData("210");
		setName("Airdate for Doctor Who");
	}
	protected TvSourceDoctorWho(ApiHandler handler) {
		super(handler);
		loadData("210");
		setName("Airdate for Doctor Who");
	}

}
