package domain.api.serialization;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonParser implements SerializationTool
{
	private Gson gson;
	
	public JsonParser()
	{
		gson  = new GsonBuilder().setPrettyPrinting().create();
	}

	@Override
	public <T>T deserialize(String json, Type calssType)
	{
		return gson.fromJson(json, calssType);
	}

	@Override
	public String serialize(Object o)
	{
		return gson.toJson(o);
	}

}
