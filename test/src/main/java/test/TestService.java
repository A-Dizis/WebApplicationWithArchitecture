package test;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import agdi.PersistenceWorkers.PersistenceWorker;
import agdi.PersistenceWorkers.impl.PwUserImpl;
import agdi.infrastructure.factories.impl.Factory;
import agdi.po.User;

/**
 * @author dizisa
 *
 */
@ApplicationPath("rest")
@Path("/testservice")
public class TestService extends Application{

	/**
	 * @return
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getTestService() {
		return "Hello World! This is coming from webservice!!";
	}

	/**
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@GET
	@Path("/user/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUser(@PathParam("id") int id) throws Exception {

		User user = Factory.create(User.class);

		PersistenceWorker<User> pwUser = Factory.createPw(User.class);

		Set<User> users = new HashSet<>();

		user.setUserId(id);

		user = pwUser.read(user);

		System.out.println(user);
		return Response.ok(user).build();
	}

}