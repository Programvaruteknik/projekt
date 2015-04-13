package domain.jersey;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("test")
public class TestApi {

	@GET
	@Path("/foo")
	public Response getResp() {
		return Response.status(200).entity("Hello world").build();
	}

	@GET
	@Path("/{param}")
	public Response getWithParam(@PathParam("param") String foo) {
		return Response.status(200).entity(foo).build();
	}
}
