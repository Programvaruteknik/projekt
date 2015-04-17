package domain.api.serialization;

import java.lang.reflect.Type;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import com.stanfy.gsonxml.GsonXml;
import com.stanfy.gsonxml.GsonXmlBuilder;
import com.stanfy.gsonxml.XmlParserCreator;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * This is a tool which is used to deserialze Xml files. The data that can be found
 * in each XML file can then be collected as a specific given type.
 * 
 * @author rasmus
 * @deprecated
 */
public class XmlParser implements SerializationTool {

	@Override
	public <T> T deserialize(String xml, Type classType) {
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
		GsonXmlBuilder gsonXmlBuilder = new GsonXmlBuilder();
		gsonXmlBuilder.setSameNameLists(true);
		GsonXml gsonXml = gsonXmlBuilder.setXmlParserCreator(parserCreator)
				.create();

		return gsonXml.fromXml(xml, classType);

	}

	@Override
	@Deprecated
	public String serialize(Object o) {
		throw new NotImplementedException();
	}

}
