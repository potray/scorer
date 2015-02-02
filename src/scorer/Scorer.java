package scorer;


import java.util.Iterator;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;


@Path("/scorer")
public class Scorer {	
	/**
	 * Add a score to a player.
	 * @param json a JSON string with parameters
	 * @return a response to the client
	 */
	@SuppressWarnings("unchecked")//This removes the JSONObject.put warning
	@POST
	@Path("/addScore")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public String addScore(String json){
		//Parse JSON
		JSONObject obj = (JSONObject) JSONValue.parse(json);
		String userName = (String) obj.get("userName");				
		String game = (String) obj.get("game");
		long score = (long) obj.get("score");
		
		//Create score
		Score newScore = new Score(game, score);
		DBManager.insertScore(userName, newScore);
		
		//Create response
		JSONObject response = new JSONObject();
		response.put("response", "Ok");
		
		return response.toJSONString();
	}
	
	/**
	 * @param game
	 * @param userName
	 * @return
	 */
	@GET
	@Path("/scores")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public String scores (@QueryParam("game") String game, @QueryParam("userName") String userName){		
		//If no username is provided, get a list with all the users
		System.out.println("Username = " + userName);
		if (userName == "" || userName.isEmpty()){
			//Get a list with the users that play the game
			List <ScorerUser> users = DBManager.getUsersByGame(game);
			
			//Create response			
			return (JSONValue.toJSONString(users));		
		}
		//Else, get all scores for the game only of the user.
		else{
			ScorerUser user = DBManager.getUserByName(userName);
			
			//Remove all scores from other games.
			for (Iterator <Score> it = user.getScores().iterator(); it.hasNext();){
				Score s = it.next();
				
				if (!s.getGame().equals(game)){
					it.remove();
				}
			}
			
			//Create response
			System.out.println(JSONValue.toJSONString(user));
			return (JSONValue.toJSONString(user));
		}
	}
}