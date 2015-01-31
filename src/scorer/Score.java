package scorer;

import java.io.Serializable;

public class Score implements Serializable{
	private static final long serialVersionUID = 1L;
	private String game;
	private long score;
	
	/**
	 * Constructor with arguments
	 * @param game the game this score has been obtained
	 * @param score the value of the score
	 */
	public Score(String game, long score) {
		this.game = game;
		this.score = score;
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
}
