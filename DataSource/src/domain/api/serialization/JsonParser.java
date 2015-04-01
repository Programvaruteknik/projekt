package domain.api.serialization;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonParser implements SerializationTool {
	private Gson gson;

	public JsonParser() {
		gson = new Gson();
	}

	public JsonParser(boolean pretty) {
		if (pretty) {
			gson = new GsonBuilder().setPrettyPrinting().create();
		} else {
			gson = new Gson();
		}
	}

	@Override
	public <T> T deserialize(String json, Type classType) {
		return gson.fromJson(json, classType);
	}

	@Override
	public String serialize(Object o) {
		return gson.toJson(o);
	}

}
