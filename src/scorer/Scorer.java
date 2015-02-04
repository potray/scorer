package scorer;

import java.util.Iterator;

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
	 * 
	 * @param json
	 *            a JSON string with parameters
	 * @return a response to the client
	 */
	@SuppressWarnings("unchecked")
	// This removes the JSONObject.put warning
	@POST
	@Path("/addScore")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String addScore(String json) {
		// Parse JSON
		JSONObject obj = (JSONObject) JSONValue.parse(json);
		String userName = (String) obj.get("userName");
		String game = (String) obj.get("game");
		long score = (long) obj.get("score");

		System.out.println("Adding score " + score + " to user " + userName
				+ " and game " + game);

		// Create score
		Score newScore = new Score(game, score);
		DBManager.insertScore(userName, newScore);

		// Create response
		JSONObject response = new JSONObject();
		response.put("response", "Ok");

		return response.toJSONString();
	}

	@SuppressWarnings("unchecked")
	// This removes the JSONObject.put warning
	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String register(String json) {
		// Parse JSON
		JSONObject obj = (JSONObject) JSONValue.parse(json);
		String userName = (String) obj.get("userName");
		String password = (String) obj.get("password");

		// Create user
		boolean userCreated = DBManager.createUser(userName, password);

		// Create response
		JSONObject response = new JSONObject();
		response.put("response", userCreated);
		
		System.out.println(userName + " registereds");

		return response.toJSONString();
	}

	@SuppressWarnings("unchecked")
	// This removes the JSONObject.put warning
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String login(String json) {
		// Parse JSON
		JSONObject obj = (JSONObject) JSONValue.parse(json);
		String userName = (String) obj.get("userName");
		String password = (String) obj.get("password");
		
		System.out.println("Logging " + userName);

		// Validate password
		String code = DBManager.validatePassword(userName, password);

		// Create response
		JSONObject response = new JSONObject();
		response.put("response", code);

		return response.toJSONString();
	}

	/**
	 * Gets all the scores from a user obtained in a game.
	 * 
	 * @param game
	 *            the game
	 * @param userName
	 *            the name of the user
	 * @return if {@code userName} is not specified returns all the scores of
	 *         {@code game}. Else, it gets all the scores from {code userName}
	 *         obtained in {@code game}
	 */
	@GET
	@Path("/scores")
	@Produces(MediaType.APPLICATION_JSON)
	public String scores(@QueryParam("game") String game,
			@QueryParam("userName") String userName) {
		System.out.println("Getting scores for user " + userName + " at game "
				+ game);
		ScorerUser user = DBManager.getUserByName(userName);

		// Remove all scores from other games.
		for (Iterator<Score> it = user.getScores().iterator(); it.hasNext();) {
			Score s = it.next();

			if (!s.getGame().equals(game)) {
				it.remove();
			}
		}

		// Create response
		System.out.println(JSONValue.toJSONString(user.getScores()));
		return (JSONValue.toJSONString(user));
	}

	/**
	 * Gets a list of the games {@code userName} has ever played.
	 * 
	 * @param userName
	 *            the name of the user
	 * @return a list of games {@code userName} has ever played
	 */
	@GET
	@Path("/games")
	@Produces(MediaType.APPLICATION_JSON)
	public String games(@QueryParam("userName") String userName) {
		System.out.println("Getting games " + userName + " has ever played");

		ScorerUser user = DBManager.getUserByName(userName);

		// Create response
		return (JSONValue.toJSONString(user.getGames()));
	}

}