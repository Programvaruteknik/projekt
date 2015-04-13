package domain.jersey;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
	@Path("/correlationData/{dataSource1}/{dataSource2}")
	public Response getCorrelationData(@PathParam("dataSource1") String ds1, @PathParam("dataSource2") String ds2 )
	{
		DataSource dataSource1 = new DataSourceFactory().getDataSource(ds1);
		DataSource dataSource2 = new DataSourceFactory().getDataSource(ds2);
		
		if(dataSource1 == null || dataSource2 == null)
			return Response.status(Response.Status.BAD_REQUEST).build();
		
		ResultingData resultingData= new DataMatcher(dataSource1, dataSource2, Resolution.DAY).match();
		
		return Response.status(200).entity(new JsonParser().serialize(resultingData)).build();
	}
}
