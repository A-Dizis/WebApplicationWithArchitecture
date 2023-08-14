package agdiWeb;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import agdi.op.def.question.GetNextIdQuestion;
import agdi.po.def.User;
import cfg.infrastructure.PersistenceWorkers.def.PersistenceWorker;
import cfg.infrastructure.factories.impl.Factory;

/**
 * @author dizisa
 *
 */
@ApplicationPath("rest")
@Path("/testservice")
public class TestService extends Application {

	public TestService() {
		try {
			Factory.getInstance();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
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
		Factory.getInstance();
		
		User user = Factory.create(User.class);

		PersistenceWorker<User> pwUser = Factory.createPw(User.class);
		
		user.setId(id);

		user = pwUser.read(user);

		System.out.println(user);
		
		return Response.ok(user).build();
	}
	
	public static void main(String[] args) throws Exception {
	Factory.getInstance();
		
		User user = Factory.create(User.class);

		PersistenceWorker<User> pwUser = Factory.createPw(User.class);

		
		user.setId(100);

		user = pwUser.read(user);

		System.out.println(user);
		
	}
}