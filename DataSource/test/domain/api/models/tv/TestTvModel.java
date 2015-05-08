package domain.api.models.tv;

import static org.junit.Assert.*;

import org.junit.Test;

import domain.api.serialization.JsonParser;

public class TestTvModel {
	@Test
	public void testModel() {
		String json ="{\"airdate\":\"2001-01-02\"}";
		SendingDay sendingDay = new JsonParser().deserialize(json, SendingDay.class);
		int expectedDay = 2; 
		int realSendingDay = sendingDay.getDayOfMonth();
		assertEquals(expectedDay,realSendingDay);
	}
}
