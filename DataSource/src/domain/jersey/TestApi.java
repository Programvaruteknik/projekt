package domain.jersey;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("test")
public class TestApi {
	@GET
	@Path("/")
	public Response getResp() {
		return Response.status(200).entity("Hello world").build();
	}
}
