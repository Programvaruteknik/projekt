package domain.api.serialization;

import java.lang.reflect.Type;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import com.stanfy.gsonxml.GsonXml;
import com.stanfy.gsonxml.GsonXmlBuilder;
import com.stanfy.gsonxml.XmlParserCreator;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;


public class XmlParser implements SerializationTool
{

	@Override
	public <T> T deserialize(String xml, Type classType)
	{
		XmlParserCreator parserCreator = new XmlParserCreator() {
		    @Override
		    public XmlPullParser createParser() {
		      try {
		        return XmlPullParserFactory.newInstance().newPullParser();
		      } catch (Exception e) {
		        throw new RuntimeException(e);
		      }
		    }
		  };
		 GsonXml gsonXml = new GsonXmlBuilder().setXmlParserCreator(parserCreator).create();

	   return gsonXml.fromXml(xml, classType);
		
	}

	@Override
	public String serialize(Object o)
	{
		throw new NotImplementedException();
	}

}
