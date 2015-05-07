package utilities;

import java.util.Map;

import org.apache.commons.math3.stat.regression.SimpleRegression;

import domain.matching.DataPair;

public class CorrelationFinder {

	public double getR2(Map<String, DataPair> map) {
		SimpleRegression regression = new SimpleRegression();
		
		for(String date: map.keySet()){
			DataPair pair = map.get(date);
			regression.addData(pair.getX(),pair.getY());
		}
		
		return regression.getRSquare();
	}

	public Map<String, DataPair> getHighestCorrelation(Map<String, DataPair> map,
			Map<String, DataPair> mapTwo) {	
		return  getR2(map) > getR2(mapTwo) ? map : mapTwo;
	}

}
