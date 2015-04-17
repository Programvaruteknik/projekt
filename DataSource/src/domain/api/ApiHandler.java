package domain.api;

import java.lang.reflect.Type;

import domain.api.serialization.SerializationTool;
import domain.api.url.UrlFetcher;
/**
 * This handler is used to handle different sources. The sources is retrieved by the {@link UrlFetcher}.
 * 
 * @author rasmus
 *
 */
public class ApiHandler
{
	private UrlFetcher fetcher;
	private SerializationTool tool;
	/**
	 * Creates an Api-handler which is using an url-fetcher and a serializtion tool.
	 * @param fetcher The fetcher.
	 * @param tool The tool used for serialization.
	 */
	public ApiHandler(UrlFetcher fetcher, SerializationTool tool)
	{
		this.fetcher = fetcher;
		this.tool = tool;
	}
	/**
	 * Returns the content which is found on the URL.
	 * The content is then serialized to the specific class type.
	 * 
	 * @param url The URL
	 * @param classType The class type.
	 * @return <T> Returns the type.
	 */
	public <T>T get(String url, Type classType)
	{
		return tool.deserialize(fetcher.getContent(url), classType);
	}

}
