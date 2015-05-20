package domain.api.models.weatherapi;

import java.util.LinkedList;
import java.util.List;

public class AstroDateCombine
{
	private List<Time> times = new LinkedList<Time>();
	public AstroDateCombine() 
	{
		// TODO Auto-generated constructor stub
	}
	
	public List<Time> getTimes()
	{
		return times;
	}
	
	public void addTime(List<Time> addTime)
	{
		for(Time t : addTime)
		{
			times.add(t);
		}
	}
}
