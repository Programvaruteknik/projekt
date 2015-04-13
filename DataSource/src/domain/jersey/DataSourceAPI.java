package domain.jersey;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
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
	public Response getCorrelationData(@QueryParam("dataSource1") String ds1, @QueryParam("dataSource2") String ds2 )
	{
		DataSource dataSource1 = new DataSourceFactory().getDataSource(ds1);
		DataSource dataSource2 = new DataSourceFactory().getDataSource(ds2);
		
		ResultingData resultingData= new DataMatcher(dataSource1, dataSource2, Resolution.DAY).match();
		
		return Response.status(200).entity(new JsonParser().serialize(resultingData)).build();
	}
}
