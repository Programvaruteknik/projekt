package domain.api;

import static org.junit.Assert.*;

import java.util.Map;
import java.util.TreeMap;

import org.junit.After;
import org.junit.Test;

import domain.api.LeagueRegister;

public class TestRegister {
	LeagueRegister register;

	@Test
	public void testGetLasttId() {
		register = new LeagueRegister();
		Map<String, Integer> map = new TreeMap<String, Integer>();
		map.put("2001", 1);
		map.put("2002", 2);
		String realId = register.getIds(map);
		String expectedId = "1,2";
		assertEquals(expectedId, realId);
	}

}
