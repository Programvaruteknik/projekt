package domain.api;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.reflect.TypeToken;

import domain.api.models.tv.SendingDay;

public class TvApiDoctorWho {
	private ApiHandler handler;

	public TvApiDoctorWho(ApiHandler theHandler){
		handler = theHandler;
	}
	
	public List<SendingDay> getAirDates(){
		Type type = new TypeToken<List<SendingDay>>(){}.getType();
		List<SendingDay> list = handler.get("http://api.tvmaze.com/shows/210/episodes", type);
		return list;
	}
}
