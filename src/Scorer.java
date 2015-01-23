

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/scorer")
public class Scorer {	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<User> getUserXML(){
		List<User> l = new ArrayList <User>();
		
		Map<String, User> contentProvider = new HashMap<String, User>();
		
		User u = new User();
		u.setName("Juan");
		contentProvider.put("1", u);
		
		l.addAll(contentProvider.values());
		return l;
	}
}
