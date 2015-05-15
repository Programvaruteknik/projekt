package domain.api;

import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;

public class LeagueRegister {

	public Map<String, Integer> getFootballLeagues() {
		Map<String, Integer> leagueYear = new TreeMap<>();
		leagueYear.put("2015", 69620);
		leagueYear.put("2014", 63925);
		leagueYear.put("2013", 57973);
		leagueYear.put("2012", 51603);
		leagueYear.put("2011", 44165);
		leagueYear.put("2010", 38686);
		leagueYear.put("2009", 32911);
		leagueYear.put("2008", 27773);
		leagueYear.put("2007", 21511);
		leagueYear.put("2006", 10581);
		leagueYear.put("2005", 9402);
		leagueYear.put("2004", 8051);
		leagueYear.put("2003", 6469);
		leagueYear.put("2002", 5307);
		leagueYear.put("2001", 3863);
		leagueYear.put("2000", 2752);
		return leagueYear;
	}

	public Map<String, Integer> getSHL() {
		Map<String, Integer> leagueYear = new TreeMap<>();
		leagueYear.put("2014-2015",66817 );
		leagueYear.put("2013-2014", 60243);
		
		return leagueYear;
	}

	public Map<String, Integer> getBandy() {
		Map<String, Integer> leagueYear = new TreeMap<>();
		leagueYear.put("2014-2015", 67536);
		leagueYear.put("2013-2014", 62038);
		leagueYear.put("2012-2013", 54745);
		leagueYear.put("2011-2012", 49260);
		leagueYear.put("2010-2011", 41720);
		leagueYear.put("2009-2010", 37135);
		leagueYear.put("2006-2007", 19315);
		leagueYear.put("2005-2006", 9925);
		leagueYear.put("2004-2005", 8673);
		leagueYear.put("2003-2004", 7466);
		leagueYear.put("2001-2002", 4719);
		leagueYear.put("2000-2001", 3754);
		return leagueYear;
	}

	public Map<String, Integer> getAmericanFootball() {
		Map<String, Integer> leagueYear = new TreeMap<>();
		leagueYear.put("2015", 595);
		leagueYear.put("2014-2015", 568);
		leagueYear.put("2013-2014", 485);
		leagueYear.put("2012-2013", 441);
		leagueYear.put("2011-2012", 399);
		leagueYear.put("2010-2011", 359);
		leagueYear.put("2009-2010", 310);
		leagueYear.put("2008-2009", 280);
		leagueYear.put("2007-2008", 207);
		leagueYear.put("2006-2007", 170);
		leagueYear.put("2005-2006", 108);
		leagueYear.put("2004-2005", 8673);
		leagueYear.put("2003-2004", 7466);
		leagueYear.put("2001-2002", 4719);
		leagueYear.put("2000-2001", 3754);
		return leagueYear;
	}

	public Map<String, Integer> getBasket() {
		Map<String, Integer> map = new TreeMap<String, Integer>();
		map.put("2014", 67844);
		map.put("2013", 62300);
		map.put("2012", 54764);
		return map;
	}

	public String getIds(Map<String, Integer> map) {
		StringBuffer buffer = new StringBuffer();
		for (Integer i : map.values()) {
			buffer.append(i.toString());
			buffer.append(",");
		}
		buffer.deleteCharAt(buffer.length() - 1);
		return buffer.toString();
	}
}
