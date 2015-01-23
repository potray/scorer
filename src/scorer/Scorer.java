package scorer;


import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;


@Path("/scorer")
public class Scorer {	
	/**
	 * Add a score to a player
	 * @param json a JSON string with parameters
	 * @return a response to the client
	 */
	@POST
	@Path("/addScore")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public String addScore(String json){
		//Parse json
		System.out.println(json);
		JSONObject obj = (JSONObject) JSONValue.parse(json);
		String userName = (String) obj.get("userName");
		String game = (String) obj.get("game");
		long score = (long) obj.get("score");
		
		//Create score
		Score newScore = new Score(game, score);
		DBManager.insertScore(userName, newScore);
		
		String response = "ok!";
		
		System.out.println(json);
		
		return response;
	}
	
	/**
	 * Gets all scores.
	 * @return a JSON string with all the scores
	 */
	@GET
	@Path("/scores")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public String scores (){
		//Get a list with the users
		List <User> users = DBManager.getAllusers();
		
		//Put all users in a JSON
		return (JSONValue.toJSONString(users));	
	}
}
