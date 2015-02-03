package scorer;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

import org.json.simple.JSONAware;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

@Entity
@XmlRootElement
public class ScorerUser implements JSONAware{
	@Id
	private String name;
	private String password;
	private List <Score> scores;
	private List <String> games;
	
	public ScorerUser (String name, String password){
		this.name = name;
		this.password = password;
		scores = new ArrayList <Score> ();
		games = new ArrayList <String> ();
	}
	
	public ScorerUser (){
		scores = new ArrayList <Score> ();
		games = new ArrayList <String> ();		
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @return the games
	 */
	public List<String> getGames() {
		return games;
	}

	/**
	 * @return the scores
	 */
	public List<Score> getScores() {
		return scores;
	}

	/**
	 * Add a new score to the user
	 * @param s the score to add
	 */
	public void addScore (Score s){
		scores.add(s);
	}
	
	/**
	 * Add a new game to the user
	 * @param game the game to add.
	 */
	public void addGame (String game){
		if (!playsGame(game))
			games.add(game);
	}
	
	/**
	 * Tells if the user plays a game
	 * @param game the game
	 * @return if the player plays game or not
	 */
	public boolean playsGame (String game){
		boolean playsIt = false;
		
		for (int i = 0; i < games.size() && !playsIt; i++){
			if (games.get(i).equals(game))
				playsIt = true;
		}
		
		return playsIt;
	}

	@SuppressWarnings("unchecked")//This removes the JSONObject.put warning
	@Override
	public String toJSONString() {
		JSONObject obj = new JSONObject();
		obj.put("name", name);
		obj.put("scores", JSONValue.toJSONString(scores));
		obj.put("games", JSONValue.toJSONString(games));
		
		return obj.toString();		
	}
}