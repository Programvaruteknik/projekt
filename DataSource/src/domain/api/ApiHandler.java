package domain.api;

import java.lang.reflect.Type;

import domain.api.serialization.SerializationTool;
import domain.api.url.UrlFetcher;

public class ApiHandler
{
	private UrlFetcher fetcher;
	private SerializationTool tool;
	
	public ApiHandler(UrlFetcher fetcher, SerializationTool tool)
	{
		this.fetcher = fetcher;
		this.tool = tool;
	}
	
	public <T>T get(String url, Type classType)
	{
		return tool.deserialize(fetcher.getContent(url), classType);
	}

}
