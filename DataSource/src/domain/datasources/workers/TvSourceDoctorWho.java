package domain.datasources.workers;

import domain.api.ApiHandler;
import domain.datasources.workers.general.TvSource;


public class TvSourceDoctorWho extends TvSource {

	public TvSourceDoctorWho() {
		super("210");
		setName("Airdate for Doctor Who");
	}
	protected TvSourceDoctorWho(ApiHandler handler) {
		super(handler, "210");
		setName("Airdate for Doctor Who");
	}

}
