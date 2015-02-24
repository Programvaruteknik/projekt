package domain.api.serialization;

import java.lang.reflect.Type;

public interface SerializationTool
{
	<T>T deserialize(String json, Type calssType);
}
