package domain.jersey;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import javax.servlet.Servlet;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.google.gson.reflect.TypeToken;

import domain.api.serialization.JsonParser;
import domain.datasources.DataSource;
import domain.datasources.DataSourceFactory;
import domain.datasources.DataSourceFormatter;
import domain.datasources.DataSourceMerger;
import domain.datasources.Interpolator;
import domain.datasources.model.MetaData;
import domain.datasources.modulateing.DateModelator;
import domain.jersey.model.DataSourcePackage;
import domain.jersey.model.Modification;
import domain.matching.DataMatcher;
import domain.matching.Resolution;
import domain.matching.ResultingData;

/**
 * This is a single {@link Servlet} which is routing the paths specified above
 * each method. And each method will return a response {@link Response}
 * depending on the the contents of the method.
 * 
 * @author Rasmus, Rickard
 *
 */
@Path("/dataSource")
public class DataSourceAPI {

	public DataSourceAPI() {
		factory = new DataSourceFactory();
	}

	@GET
	@Path("/correlationData")
	public Response getCorrelationData(@QueryParam("dataSource1") String ds1,
			@QueryParam("dataSource2") String ds2,
			@QueryParam("resolution") String res,
			@QueryParam("modification") String mod, @QueryParam("startDate") String startDate, @QueryParam("endDate") String endDate) {

		Resolution resolution = res != null ? Resolution.valueOf(res)
				: Resolution.DAY;

		DataSource dataSource1 = factory.getDataSource(ds1);
		DataSource dataSource2 = factory.getDataSource(ds2);
		
		if(mod != null)
		{
			List<Modification> modList = new JsonParser().deserialize(mod, new TypeToken<List<Modification>>(){}.getType());
			
			for (Modification modification : modList)
			{
				if(dataSource1.getMetaData().getName().equals(modification.getDataSourceName()))
				{
					dataSource1 = modulateDataSource(dataSource1, modification);
				}
				else if(dataSource2.getMetaData().getName().equals(modification.getDataSourceName()))
				{
					dataSource2 = modulateDataSource(dataSource2, modification);
				}
			}
		}

		if (dataSource1 == null || dataSource2 == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}

		ResultingData resultingData = new DataMatcher(dataSource1, dataSource2,
				resolution, startDate, endDate).match();

		resultingData.setXMeta(dataSource1.getMetaData());
		resultingData.setYMeta(dataSource2.getMetaData());

		return Response.status(200)
				.entity(new JsonParser().serializeNulls(resultingData)).build();
	}

	DataSourceFactory factory;

	protected void setFactory(DataSourceFactory factory) {
		this.factory = factory;
	}

	@GET
	@Path("/{dataSource}")
	public Response getSources(@PathParam("dataSource") String ds , @QueryParam("fromDate") String fDate ,@QueryParam("toDate") String tDate) {

		ArrayList<String> input = new JsonParser().deserialize(ds,
				new TypeToken<ArrayList<String>>() {
				}.getType());

		TreeMap<LocalDate, ArrayList<Double>> data = null;

		for (int i = 0; i < input.size(); i++) {
			if (i == 0) {
				DataSource tmpSource = factory.getDataSource(input.get(i));
if(tmpSource == null){
	return Response.status(Response.Status.BAD_REQUEST).build();
}
				tmpSource = new Interpolator().fillOutMissingDays(tmpSource,
						Resolution.DAY, fDate, tDate);

				data = new DataSourceFormatter(tmpSource, fDate, tDate).toMergeableFormat();
			} else {
				data = new DataSourceMerger(data,
						new DataSourceFormatter(factory.getDataSource(input
										.get(i)), fDate, tDate).toMergeableFormat())
						.merge(Resolution.DAY);

			}
		}
		ArrayList<MetaData> metaList = new ArrayList<MetaData>();
		for(String sourceName : input){
			DataSource src = factory.getDataSource(sourceName);
			metaList.add(src.getMetaData());
		}
		input.add(0, "Date");

		DataSourcePackage sourcePackage = new DataSourcePackage(input, metaList,data);
		String json = new JsonParser().serializeNulls(sourcePackage);
		return okRequest(json);
	}

	@GET
	@Path("/resolutions")
	public Response getResolutions() {
		return okRequest(new JsonParser().serialize(Resolution.values()));
	}

	@GET
	@Path("/list")
	public Response getListOfDataSources() {
		return okRequest(new JsonParser().serialize(new DataSourceFactory()
				.getNameAllDataSources()));
	}

	@GET
	@Path("/foo/test")
	public Response getFoo(@QueryParam("foo") String s) {
		return Response.status(200).entity("Yaay" + s).build();
	}

	/**
	 * Returns an json list of each data source. The sources are separated with
	 * ','.
	 * 
	 * @param string
	 *            The json list.
	 * @return
	 */
	@GET
	@Path("/metaData")
	public Response getMeta(@QueryParam("list") String string) {
		String[] list = string.split(",");
		ArrayList<MetaData> array = new ArrayList<>();
		for (String e : list) {
			array.add(getMetaData(e));
		}
		String s = string;
		String json = new JsonParser().serialize(array);
		return Response.status(200).entity(json).build();
	}
	
	private DataSource modulateDataSource(DataSource dataSource , Modification modification)
	{
		return new DateModelator(dataSource, modification.getYear(), modification.getMonth(), modification.getDays()).execute();
	}

	protected MetaData getMetaData(String string) {
		MetaData meta = factory.getDataSource(string).getMetaData();
		return meta;
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
