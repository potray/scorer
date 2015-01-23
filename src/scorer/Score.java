package scorer;


public class Score {
	private String game;
	private long score;
	
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
