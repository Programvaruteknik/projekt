package domain.api;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.reflect.TypeToken;

import domain.api.models.tv.SendingDay;

public class TvApiCommunity {
	private ApiHandler handler;

	public TvApiCommunity(ApiHandler theHandler){
		handler = theHandler;
	}
	
	public List<SendingDay> getAirDates(){
		Type type = new TypeToken<List<SendingDay>>(){}.getType();
		List<SendingDay> list = handler.get("http://api.tvmaze.com/shows/318/episodes", type);
		return list;
	}

	
}
