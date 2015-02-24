package domain.api.url;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;


public class UrlFetcher {

	private URL url; 
		
	public String getContent(String urlString)
	{
		try 
		{
			url = new URL(urlString);
		} 
		catch (MalformedURLException e) 
		{
			throw new RuntimeException(e);
		}
		
		String result = "", temp = "";
		try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream())))
		{
			
			while(null != (temp = bufferedReader.readLine()))
			{
				result += temp;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
			
		
	}
	
	
}
