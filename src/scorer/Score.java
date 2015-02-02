package scorer;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.simple.JSONAware;
import org.json.simple.JSONObject;

public class Score implements Serializable, JSONAware{
	private static final long serialVersionUID = 1L;
	private String game;
	private long score;
	private Date date;
	
	/**
	 * Constructor with arguments
	 * @param game the game this score has been obtained
	 * @param score the value of the score
	 */
	public Score(String game, long score) {
		this.game = game;
		this.score = score;
		this.date = new Date();
	}
	/**
	 * @return the game
	 */
	public String getGame() {
		return game;
	}
	/**
	 * @param game the game to set
	 */
	public void setGame(String game) {
		this.game = game;
	}
	/**
	 * @return the score
	 */
	public float getScore() {
		return score;
	}
	/**
	 * @param score the score to set
	 */
	public void setScore(long score) {
		this.score = score;
	}
	@SuppressWarnings("unchecked")//This removes the JSONObject.put warning
	@Override
	public String toJSONString() {
		JSONObject obj = new JSONObject();
		obj.put("game", game);
		obj.put("score", score);
		obj.put("date", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
		
		return obj.toString();
	}
}
