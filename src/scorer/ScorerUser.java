package scorer;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class ScorerUser {
	@Id
	private String name;
	private List <Score> scores;
	
	public ScorerUser (String name){
		this.name = name;
		scores = new ArrayList <Score> ();
	}
	
	public ScorerUser (){
		
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
	 * Add a new score to the user
	 * @param s the score to add
	 */
	public void addScore (Score s){
		scores.add(s);
	}
}

