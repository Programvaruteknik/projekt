package domain.api;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.reflect.TypeToken;

import domain.api.models.tv.SendingDay;

public class TvApi {
	private String id;
	private ApiHandler handler;
	
	public void setId(String id){
		this.id = id;
	}

	public TvApi(ApiHandler handler) {
		this.handler = handler;
	}

	public List<SendingDay> getAirDates() {
		Type type = new TypeToken<List<SendingDay>>() {
		}.getType();
		List<SendingDay> list = handler.get("http://api.tvmaze.com/shows/" + id
				+ "/episodes", type);
		return list;
	}

}
