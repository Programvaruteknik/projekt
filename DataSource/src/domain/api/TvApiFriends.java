package domain.api;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.reflect.TypeToken;

import domain.api.models.tv.SendingDay;

public class TvApiFriends {
	private ApiHandler handler;
	
	public TvApiFriends(ApiHandler theHandler){
		handler = theHandler;
	}
	
	public List<SendingDay> getList() {
		Type type = new TypeToken<List<SendingDay>>(){}.getType();
		List<SendingDay> list = handler.get("http://api.tvmaze.com/shows/431/episodes", type);
		return list;
	}

}
