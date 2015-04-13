package domain.jersey;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import domain.api.serialization.JsonParser;
import domain.datasources.DataSource;
import domain.datasources.DataSourceFactory;
import domain.matching.DataMatcher;
import domain.matching.Resolution;
import domain.matching.ResultingData;

@Path("/dataSource")
public class DataSourceAPI
{
	@GET
	@Path("/correlationData")
	public Response getCorrelationData()
	{
		DataSource dataSource1 = new DataSourceFactory().getDataSource("TotalFotballGoals");
		DataSource dataSource2 = new DataSourceFactory().getDataSource("SunAltitudeAtNoon");
		
		ResultingData resultingData= new DataMatcher(dataSource1, dataSource2, Resolution.DAY).match();
		
		return Response.status(200).entity(new JsonParser().serialize(resultingData)).build();
	}
}
