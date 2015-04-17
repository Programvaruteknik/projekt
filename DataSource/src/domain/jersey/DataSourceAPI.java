package domain.jersey;

import java.time.LocalDate;
import java.util.TreeMap;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.google.gson.GsonBuilder;

import domain.api.serialization.JsonParser;
import domain.datasources.DataSource;
import domain.datasources.DataSourceFactory;
import domain.datasources.Interpolator;
import domain.matching.DataMatcher;
import domain.matching.Resolution;
import domain.matching.ResultingData;

@Path("/dataSource")
public class DataSourceAPI {

	public DataSourceAPI() {
		factory = new DataSourceFactory();
	}

	@GET
	@Path("/correlationData/{dataSource1}/{dataSource2}")
	public Response getCorrelationData(@PathParam("dataSource1") String ds1,
			@PathParam("dataSource2") String ds2) {
		DataSource dataSource1 = new DataSourceFactory().getDataSource(ds1);
		DataSource dataSource2 = new DataSourceFactory().getDataSource(ds2);

		if (dataSource1 == null || dataSource2 == null)
			return Response.status(Response.Status.BAD_REQUEST).build();

		ResultingData resultingData = new DataMatcher(dataSource1, dataSource2,
				Resolution.DAY).match();

		return Response.status(200)
				.entity(new JsonParser().serialize(resultingData)).build();
	}

	DataSourceFactory factory;

	protected void setFactory(DataSourceFactory factory) {
		this.factory = factory;
	}

	@GET
	@Path("/{dataSource}")
	public Response getSingleSource(@PathParam("dataSource") String ds1) {
		DataSource source = factory.getDataSource(ds1);
		if (source == null) {
			return badRequestResponse();
		}
		Interpolator interpolator = new Interpolator();
		TreeMap<LocalDate, Double> mappen = (TreeMap<LocalDate, Double>) source
				.getData();
		interpolator.fillOutMissingDays(mappen);

		String json = new JsonParser().serializeNulls(mappen);
		return okRequest(json);
	}

	/**
	 * Returns an Response that is configuration to return BAD_REQUEST.
	 * 
	 * Used to send an bad_request to the client.
	 * 
	 * @return Response The Response.
	 */
	protected Response badRequestResponse() {
		return Response.status(Response.Status.BAD_REQUEST).build();
	}

	/**
	 * Returns the json of a given data source {@link #DataSource}.
	 * 
	 * @param source
	 *            The source.
	 * @return String The json.
	 */
	protected String getJson(DataSource source) {
		return new JsonParser().serialize(source.getData());
	}

	/**
	 * Returns an OK response which contains the given object.
	 * 
	 * @param obj
	 *            The given object.
	 * @return Response The response.
	 */
	protected Response okRequest(Object obj) {
		return Response.status(Response.Status.OK).entity(obj).build();
	}

}
