package domain.api.serialization;

import java.lang.reflect.Type;
/**
 * This is an interface which is used to create different Serialization tools.
 * 
 * @author rasmus
 *
 */
public interface SerializationTool
{
	/**
	 * 
	 * @param json
	 * @param classType
	 * @return
	 */
	<T>T deserialize(String json, Type classType);
	String serialize(Object o);
}
