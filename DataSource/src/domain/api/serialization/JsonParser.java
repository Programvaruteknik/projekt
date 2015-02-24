package domain.api.serialization;

import java.lang.reflect.Type;

import com.google.gson.Gson;

public class JsonParser implements SerializationTool
{
	private Gson gson;
	
	public JsonParser()
	{
		gson  = new Gson();
	}

	@Override
	public <T>T deserialize(String json, Type calssType)
	{
		return gson.fromJson(json, calssType);
	}

}
