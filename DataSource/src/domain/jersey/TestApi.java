package domain.jersey;

import javax.annotation.PostConstruct;
import javax.websocket.server.PathParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("test")
public class TestApi {
	public TestApi() {
	}
	@PostConstruct
	public void init(){		
		System.out.println("Starting");
	}
	@GET
	@Path("/foo")
	public Response getResp() {
//		System.out.println("Running");
		return Response.status(200).entity("Hello world").build();
	}
	@GET
	@Path("/{param}")
	public Response getWithParam(@PathParam("param") String foo){
		return Response.status(200).entity("HEJSAN").build();
	}
}
